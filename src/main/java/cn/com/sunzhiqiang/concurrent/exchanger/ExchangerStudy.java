package cn.com.sunzhiqiang.concurrent.exchanger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述: Exchanger学习
 *
 * @author sunzhiqiang
 * @create 2018-11-11
 */
public class ExchangerStudy {

    private static Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            threadPool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                Long threadId = Thread.currentThread().getId();
                System.out.println(threadId + "我是线程：" + threadName);
                String dataFromOtherThread = exchanger.exchange(threadName);
                System.out.println(threadId + "它是线程：" + dataFromOtherThread);

                countDownLatch.countDown();
                return null;
            });
        }

        countDownLatch.await();
        System.out.println("Exchanger交换2个线程的数据。");
    }
}
