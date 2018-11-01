package cn.com.sunzhiqiang.java.threadpool;

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
}
