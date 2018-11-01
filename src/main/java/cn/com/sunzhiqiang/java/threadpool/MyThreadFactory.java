package cn.com.sunzhiqiang.java.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunzhiqiang
 * @date 2018/11/1
 * @desc 自定义线程工厂的实现类
 */
public class MyThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {

        Thread thread = new Thread(r);
        count.getAndIncrement();

        return thread;
    }
}
