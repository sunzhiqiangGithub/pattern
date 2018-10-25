package cn.com.sunzhiqiang.java.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: 可重入锁学习
 *
 * @author sunzhiqiang
 * @create 2018-10-25
 */
public class ReentrantLockStudy {

    private ReentrantLock lock = new ReentrantLock();
    private CountDownLatch countDownLatch = new CountDownLatch(4);
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private int count;

    @Test
    public void testLock() throws InterruptedException {

        for (int i = 0; i < 4; i++) {
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

        for (int i = 0; i < 4; i++) {
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

        for (int i = 0; i < 4; i++) {
            threadPool.submit(() -> {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {

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
}


