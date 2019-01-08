package cn.com.sunzhiqiang.concurrent.lock.condition;

/**
 * 功能描述: 学习notify/wait
 *
 * @author sunzhiqiang
 * @create 2018-10-27
 */
public class NotifyAndWaitStudy {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "获得锁");
                lock.notifyAll();
            }
        });

        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "获得锁");
            thread1.start();
            Thread.sleep(1000); //确保thread1已经开始执行，等待获取锁
            lock.wait();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        }
    }
}
