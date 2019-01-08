package cn.com.sunzhiqiang.concurrent.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author sunzhiqiang
 * @date 2018/10/31
 * @desc 线程池学习
 */
public class ThreadPoolStudy {

    @Test
    public void createThreadPoolByFactory() {

        // 创建大小固定的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        // 创建大小没限制的线程池
        ExecutorService threadPool2 = Executors.newCachedThreadPool();

        // 创建只有一个线程的线程池
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor();
    }

    @Test
    public void createThreadPoolByConstructor() {

        // 不指定创建线程的工厂和饱和策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                4,
                6,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10)
        );

        // 不指定饱和策略
        ThreadPoolExecutor threadPool2 = new ThreadPoolExecutor(
                4,
                6,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                new MyThreadFactory()
        );

        // 包含创建线程池的所有参数
        ThreadPoolExecutor threadPool3 = new ThreadPoolExecutor(
                4,//核心池大小
                6,//最大池的大小
                2,//线程空闲时，存活时间
                TimeUnit.SECONDS,//线程空闲时，存活时间单位
                new LinkedBlockingDeque<>(10),//任务队列
                new MyThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy()//线程池饱和时的策略
        );
    }

    @Test
    public void useThreadPool() throws Exception {

        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                4,
                6,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10)
        );

        try {
            // 测试线程数小于核心池大小
            testThreadPool(threadPool,
                    3,
                    "第一轮测试结束，测试线程数为3，小于核心池大小。");

            // 测试线程数等于核心池大小
            testThreadPool(threadPool,
                    4,
                    "第二轮测试结束，测试线程数为4，等于核心池大小。");

            // 测试线程数大于核心池大小，但小于核心池加任务队列大小
            testThreadPool(threadPool,
                    9,
                    "第三轮测试结束，测试线程数为9，大于核心池大小，没超过任务队列限制。");

            // 测试线程数大于核心池和任务队列的大小和，但小于最大池大小和任务队列大小和
            testThreadPool(threadPool,
                    16,
                    "第四轮测试结束，测试线程数为16，大于核心池加任务队列，小于最大池加任务队列。");

            // 测试线程数大于线程池最大容量，默认饱和策略
            testThreadPool(threadPool,
                    20,
                    "第五轮测试结束，测试线程数为20，大于线程池最大容量，默认饱和策略抛出异常。"
            );

        } finally {
            threadPool.shutdown();
        }
    }

    private void testThreadPool(ThreadPoolExecutor threadPool, int threadCount, String message) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                countDownLatch.countDown();
                return null;
            });
        }

        countDownLatch.await();
        System.out.println(message);
    }


}
