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

