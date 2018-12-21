package cn.com.sunzhiqiang.java.lock.readwritelock;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能描述: 读写锁学习
 *
 * @author sunzhiqiang
 * @create 2018-11-10
 */
public class ReentrantReadWriteLockStudy {

    // 模拟的多线程需要共享的数据
    private int data = 1;
    private Random random = new Random();

    // 读写锁
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    public void load() throws InterruptedException {

        readLock.lock();
        try {
            System.out.println("读取data数据值为：" + data);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("读线程睡眠1秒");

        } finally {
            readLock.unlock();
        }
    }

    public void store() throws InterruptedException {

        writeLock.lock();
        try {
            data = random.nextInt(100);
            System.out.println("写入data数据的值为：" + data);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("写线程睡眠1秒");

        } finally {
            writeLock.unlock();
        }
    }

    @Test
    public void testReadBlockWrite() throws InterruptedException {

        ReentrantReadWriteLockStudy readWriteLockStudy = new ReentrantReadWriteLockStudy();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(() -> {
            readWriteLockStudy.load();
            countDownLatch.countDown();
            return null;
        });
        threadPool.submit(() -> {
            readWriteLockStudy.store();
            countDownLatch.countDown();
            return null;
        });

        countDownLatch.await();
        System.out.println("读锁会阻塞写锁的获取。");
    }

    @Test
    public void testWriteBlockRead() throws InterruptedException {

        ReentrantReadWriteLockStudy readWriteLockStudy = new ReentrantReadWriteLockStudy();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(() -> {
            readWriteLockStudy.store();
            countDownLatch.countDown();
            return null;
        });
        threadPool.submit(() -> {
            readWriteLockStudy.load();
            countDownLatch.countDown();
            return null;
        });

        countDownLatch.await();
        System.out.println("写锁会阻塞读锁的获取。");
    }

    @Test
    public void testDowngradeLock() {

        ReentrantReadWriteLockStudy readWriteLockStudy = new ReentrantReadWriteLockStudy();

        // 获取写锁
        readWriteLockStudy.writeLock.lock();
        try {
            // 修改数据
            readWriteLockStudy.data = 10;
            // 同一线程获取读锁
            readWriteLockStudy.readLock.lock();
        } finally {
            // 释放写锁
            readWriteLockStudy.writeLock.unlock();
        }

        try {
            // 此时该线程只持有读锁
            System.out.println("读取到data数据：" + readWriteLockStudy.data);
        } finally {
            readWriteLockStudy.readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        ReentrantReadWriteLockStudy readWriteLockStudy = new ReentrantReadWriteLockStudy();

        CyclicBarrier barrier = new CyclicBarrier(6);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 读锁可以共享访问的测试
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                readWriteLockStudy.load();
                barrier.await();
                return null;
            });
        }
        barrier.await();
        System.out.println("读锁可以共享访问");
        System.out.println("=========================");

        // 写锁必须互斥访问的测试
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                readWriteLockStudy.store();
                barrier.await();
                return null;
            });
        }
        barrier.await();
        System.out.println("写锁必须互斥访问");

        threadPool.shutdown();
    }
}
