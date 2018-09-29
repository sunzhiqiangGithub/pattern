### 概述
   JUC包下提供了大量的同步工具类来帮助我们更好的使用多线程进行并发编程，  
   例如：ReentrantLock，ReentrantReadWriteLock，CountDownLatch，Semaphore等。  
   而这些方便的同步器工具类的基础框架就是AQS(AbstractQueuedSynchronizer),学习AQS对更好的理解常用的同步器工具类有很大帮助。
   
### 目标
   学习AQS的目标是两个：  
   1.更好的理解常用的同步器工具类原理（CountDownLatch等）（必须）  
   2.通过AQS实现自己的同步器类。（可选）
   
### 原理
   AQS是一个模板类，是用来构建锁和其他同步组件的基础框架。  
   它的实现主要依赖一个volatile修饰int成员变量来表示同步状态，以及通过一个FIFO队列构成等待队列。  
   状态的更新使用getState,setState以及compareAndSetState这三个方法。  
   同步器既支持独占式获取同步状态，也可以支持共享式获取同步状态，这样就可以方便的实现不同类型的同步组件。  
   它的子类通过重写AQS的几个protected修饰的用来改变同步状态的方法，具体排队和阻塞机制由AQS实现，
   它简化了锁的实现方式，屏蔽了同步状态的管理，线程的排队，等待和唤醒等底层操作。锁和同步器很好的隔离了使用者和实现者所需关注的领域。
   
#### 成员变量
```java
    private static final long serialVersionUID = 7373984972572414691L;

    // 等待队列的头指针和尾指针
    private transient volatile Node head;
    private transient volatile Node tail;
    
    // 同步状态
    private volatile int state;
    
    // 当使用限时版本的api时，如果时间小于该值，不进入等待状态
    static final long spinForTimeoutThreshold = 1000L;
       
    // Unsafe类对象，为了实现CAS使用
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                (AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                (AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                (AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                (Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                (Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }
```   
从上面的成员变量可以看出，核心的成员变量有3个：state、head、tail  
state用来表示同步状态  
head、tail分别是AQS维护的等待队列的头指针和尾指针。

#### 等待队列结构
![FIFO队列](https://github.com/sunzhiqiangGithub/pattern/blob/master/books/image/AQS队列.jpg)  
队列中的每个结点的结构为如下代码：
```java
    static final class Node {
        
        // 共享锁
        static final Node SHARED = new Node();
        // 独占锁
        static final Node EXCLUSIVE = null;

        // 线程已被取消
        static final int CANCELLED =  1;
        // 当前线程的后继线程需要被unpark(唤醒)
        static final int SIGNAL    = -1;
        // 线程(处在Condition休眠状态)在等待Condition唤醒
        static final int CONDITION = -2;
        // 共享模式下，确保向后面的结点传播
        static final int PROPAGATE = -3;

        // 等待状态
        volatile int waitStatus;

        // 前驱结点
        volatile Node prev;

        // 后继结点
        volatile Node next;

        // 等待获取锁的线程
        volatile Thread thread;

        // TODO
        Node nextWaiter;

        // 是不是共享锁
        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        // 获取前驱结点
        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }
```

#### 独占锁
独占锁相关的api  
1 不响应中断的获取  
public final void acquire(int arg);  
```java
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
```
先获取锁，该方法为protected修饰，需要子类重写，调用子类的获取锁的方法  
```java
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
```
获取锁失败，增加一个结点到等待队列里  
```java
    private Node addWaiter(Node mode) {
        // 根据mode的值决定生成的是独占结点还是共享结点，这里新加入的结点waitStatus应该为0
        Node node = new Node(Thread.currentThread(), mode);
        // 把上一步生成的结点插入队尾
        Node pred = tail;
        // 队尾不为空，利用CAS修改队尾为新加入的结点
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        // 队尾为空或者CAS插入失败
        enq(node);
        return node;
    }
```
将新加的结点插入队列  
```java
    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) { // 初始化等待队列
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {
                // 使用CAS将队尾更新为新结点
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }
```
排队获取锁  
```java
    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                // 获取当前结点的前驱结点
                final Node p = node.predecessor();
                // 如果当前结点是头结点的下一个结点，并且获取锁成功，设置当前结点为新的头结点，原来的头结点出队
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```
重新设置头结点  
```java
   private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }
```
判断是否应该挂起线程  
```java
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        
        // 如果前驱结点的状态为SIGNAL（表明当前驱结点释放锁时会恢复当前结点），返回true调用parkAndCheckInterrupt方法挂起当前线程
        if (ws == Node.SIGNAL)
            return true;
        
        // 等待状态大于0只有一种状态为取消状态
        if (ws > 0) {
            do {
                // 向前遍历，找前边第一个状态不是取消的结点
                node.prev = pred = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            // 将前驱结点的等待状态更新为SIGNAL，下一次循环的时候返回true,挂起当前线程
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        
        return false;
    }
```
挂起线程  
```java
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }
```
恢复中断状态  
```java
    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }
```
  
2 响应中断的获取  
public final void acquireInterruptibly(int arg) throws InterruptedException;  
```java
    public final void acquireInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (!tryAcquire(arg))
            doAcquireInterruptibly(arg);
    }
```
```java
    private void doAcquireInterruptibly(int arg)
        throws InterruptedException {
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```
3 响应中断的限时获取    
public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException;  
```java
    public final boolean tryAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryAcquire(arg) ||
            doAcquireNanos(arg, nanosTimeout);
    }
```
```java
    private boolean doAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (nanosTimeout <= 0L)
            return false;
        final long deadline = System.nanoTime() + nanosTimeout;
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return true;
                }
                nanosTimeout = deadline - System.nanoTime();
                if (nanosTimeout <= 0L)
                    return false;
                if (shouldParkAfterFailedAcquire(p, node) &&
                    nanosTimeout > spinForTimeoutThreshold)
                    LockSupport.parkNanos(this, nanosTimeout);
                if (Thread.interrupted())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```
4 释放  
public final boolean release(int arg);  
```java
    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
```
```java
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
```
```java
    private void unparkSuccessor(Node node) {
        /*
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling.  It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        /*
         * Thread to unpark is held in successor, which is normally
         * just the next node.  But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpark(s.thread);
    }
```

#### 共享锁
共享锁相关的api  
1 不响应中断的获取  
public final void acquireShared(int arg);  
2 响应中断的获取  
public final void acquireSharedInterruptibly(int arg) throws InterruptedException;  
3 响应中断的限时获取  
public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException;  
4 释放  
public final boolean releaseShared(int arg);  
 

