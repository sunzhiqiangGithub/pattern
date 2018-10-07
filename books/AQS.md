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
   它的实现主要依赖一个volatile修饰int成员变量来表示同步状态，以及通过一个FIFO队列构成同步队列。  
   状态的更新使用getState,setState以及compareAndSetState这三个方法。  
   同步器既支持独占式获取同步状态，也可以支持共享式获取同步状态，这样就可以方便的实现不同类型的同步组件。  
   它的子类通过重写AQS的几个protected修饰的用来改变同步状态的方法，具体排队和阻塞机制由AQS实现，
   它简化了锁的实现方式，屏蔽了同步状态的管理，线程的排队，等待和唤醒等底层操作。锁和同步器很好的隔离了使用者和实现者所需关注的领域。  
   
   **子类需要重新的方法**
   ![重写的方法](https://github.com/sunzhiqiangGithub/pattern/blob/master/books/image/method.png)
#### 成员变量
```java
    private static final long serialVersionUID = 7373984972572414691L;

    // 同步队列的头指针和尾指针
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
head、tail分别是AQS维护的同步队列的头指针和尾指针。

#### 同步队列结构
![FIFO队列](https://github.com/sunzhiqiangGithub/pattern/blob/master/books/image/AQS队列.jpg)  
队列中的每个结点的结构为如下代码：
```java
    static final class Node {
        
        // 共享模式
        static final Node SHARED = new Node();
        // 独占模式
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

        // 等待队列中的下一个节点
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

#### 独占模式
独占模式相关的api  
1 不响应中断的获取  
public final void acquire(int arg);  
```java
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
```
先获取同步状态，该方法为protected修饰，需要子类重写，调用子类的获取锁的方法  
```java
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
```
获取同步状态失败，增加一个结点到等待队列里  
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
排队获取同步状态    
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
        
        // 如果前驱结点的状态为SIGNAL（表明当前驱结点释放锁时会恢复当前结点），
        // 返回true调用parkAndCheckInterrupt方法挂起当前线程
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
  流程如下:  
  ![独占模式流程图](https://github.com/sunzhiqiangGithub/pattern/blob/master/books/image/独占获取流程图.png)
  
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
获取同步状态,获取失败调用doAcquireInterruptibly方法.

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
方法与acquire流程差不多,唯一的区别是,acquire只设置中断标志,而这个方法会抛出中断异常.

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
获取同步状态失败,调用doAcquireNanos方法.

```java
    private boolean doAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        // 超时时间小于等于0直接返回
        if (nanosTimeout <= 0L)
            return false;
        // 计算什么时候超时
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
                // 超时直接返回
                if (nanosTimeout <= 0L)
                    return false;
                // 没超时时,挂起线程
                if (shouldParkAfterFailedAcquire(p, node) &&
                    nanosTimeout > spinForTimeoutThreshold)
                    LockSupport.parkNanos(this, nanosTimeout);
                // 响应中断
                if (Thread.interrupted())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
```
大致流程也和acquire差不多,增加了响应中断和超时返回.  

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
释放同步状态失败直接返回,释放成功判断头结点的等待状态,不等于0调用unparkSuccessor方法.

```java
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
```
```java
    private void unparkSuccessor(Node node) {
    
        // 更新头结点的状态为0
        int ws = node.waitStatus;
        if (ws < 0)
            compareAndSetWaitStatus(node, ws, 0);

        // 查找头结点后第一个状态不是取消的结点
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev)
                if (t.waitStatus <= 0)
                    s = t;
        }
        
        // 头结点后存在没有取消的结点,唤醒该结点里的线程.
        if (s != null)
            LockSupport.unpark(s.thread);
    }
```

#### 共享模式
共享模式相关的api  
1 不响应中断的获取  
public final void acquireShared(int arg);  
```java
    public final void acquireShared(int arg) {
        if (tryAcquireShared(arg) < 0)
            doAcquireShared(arg);
    }
```
获取同步状态,返回值大于等于0获取成功,获取失败调用doAcquireShared方法.  
```java
    private void doAcquireShared(int arg) {
        // 增加一个结点到等待队列中
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                // 获取先驱结点,先驱结点是头结点,并且获取同步状态成功
                final Node p = node.predecessor();
                if (p == head) {
                    int r = tryAcquireShared(arg);
                    if (r >= 0) {
                        setHeadAndPropagate(node, r); // 将head指向自己，还有剩余资源可以再唤醒之后的线程
                        p.next = null; // help GC
                        if (interrupted)
                            selfInterrupt();
                        failed = false;
                        return;
                    }
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
```java
    private void setHeadAndPropagate(Node node, int propagate) {
        Node h = head; // Record old head for check below
        setHead(node);
        // 如果还有剩余资源，继续唤醒后面的线程
        if (propagate > 0 || h == null || h.waitStatus < 0 ||
            (h = head) == null || h.waitStatus < 0) {
            Node s = node.next;
            if (s == null || s.isShared())
                doReleaseShared();
        }
    }
```
```java
    private void doReleaseShared() {
        for (;;) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cases
                    unparkSuccessor(h);  // 唤醒后继
                }
                else if (ws == 0 &&
                         !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;                // loop on failed CAS
            }
            if (h == head)                   // loop if head changed
                break;
        }
    }
```
2 响应中断的获取  
public final void acquireSharedInterruptibly(int arg) throws InterruptedException;  
相应中断的共享获取与acquireShared大致相同,只是由原来的修改中断状态改成抛出中断异常.  

3 响应中断的限时获取  
public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException;  
与独占模式的相应API差不多,这里省略.  

4 释放  
public final boolean releaseShared(int arg);  
```java
    public final boolean releaseShared(int arg) {
        if (tryReleaseShared(arg)) {
            doReleaseShared();
            return true;
        }
        return false;
    }
```
释放同步状态成功,调用doReleaseShared方法.  
 

