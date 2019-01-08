package cn.com.sunzhiqiang.concurrent.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: 可重入锁学习
 *
 * @author sunzhiqiang
 * @create 2018-10-25
 */
public class ReentrantLockStudy {

    private ReentrantLock lock = new ReentrantLock();
    private CountDownLatch countDownLatch = new CountDownLatch(100);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private int count;

    @Test
    public void testLock() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                lock.lock();
                try {
                    count++;
                    countDownLatch.countDown();
                } finally {
                    lock.unlock();
                }
            });
        }

        countDownLatch.await();
        System.out.println(String.format("计数器i的值:%d", count));
    }

    @Test
    public void testTryLock() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                boolean isLock = false;
                while (!isLock) {
                    isLock = lock.tryLock();
                }
                try {
                    count++;
                    countDownLatch.countDown();
                } finally {
                    lock.unlock();
                }
            });
        }

        countDownLatch.await();
        System.out.println(String.format("计数器i的值:%d", count));
    }

    @Test
    public void testLockInterruptibly() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("Thread1发生中断");
                Thread.currentThread().interrupt();
                return;
            }
            try {
                System.out.println("thread1获取到锁");
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread2获取到锁");
            } finally {
                lock.unlock();
            }
        });

        lock.lock(); //主线程先获取锁
        try {
            thread1.start();
            thread2.start();
            Thread.sleep(1000); //等待thread1和thread2阻塞在获取锁的步骤
            thread1.interrupt();
            thread2.interrupt();
        } finally {
            Thread.sleep(2000); //主线程等待2秒再释放锁
            lock.unlock();
        }
    }

    @Test
    public void testTryLockTime() throws Exception {

        threadPool.submit(() -> {
            cyclicBarrier.await();
            boolean isLock = false;
            while (!isLock) {
                long startTime = System.currentTimeMillis();
                isLock = lock.tryLock(1, TimeUnit.SECONDS);
                long waitTime = (System.currentTimeMillis() - startTime) / 1000L;
                System.out.println(isLock ? "获取到锁" : String.format("被中断或者超时，获取锁失败,等待时间为:%d秒", waitTime));
            }
            lock.unlock();
            return null;
        });

        lock.lock();
        try {
            cyclicBarrier.await(); // CyclicBarrier(2)，当有2个线程调用await方法，屏障被突破
            Thread.sleep(2000);
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testGetHoldCount() {

        lock.lock();
        System.out.println("当前线程持有的锁的次数：" + lock.getHoldCount());
        lock.lock();
        System.out.println("当前线程持有的锁的次数：" + lock.getHoldCount());
        lock.lock();
        System.out.println("当前线程持有的锁的次数：" + lock.getHoldCount());

        lock.unlock();
        lock.unlock();
        lock.unlock();
    }

    @Test
    public void testIsHeldByCurrentThread() throws Exception {

        threadPool.submit(() -> {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "线程是否持有锁：" + lock.isHeldByCurrentThread());
            return null;
        });

        lock.lock();
        try {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "线程是否持有锁：" + lock.isHeldByCurrentThread());
        } finally {
            lock.unlock();
        }
    }
}


