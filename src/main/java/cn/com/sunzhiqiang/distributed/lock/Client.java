package cn.com.sunzhiqiang.distributed.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-05
 */
public class Client {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                DistributedLock distributedLock = new RedisDistributedLock(5000);
                distributedLock.lock();
                try {
                    System.out.println(String.format("线程%s执行开始", Thread.currentThread().getId()));
                    System.out.println(String.format("线程%s执行结束", Thread.currentThread().getId()));
                } finally {
                    distributedLock.unlock();
                }
            });
        }
    }
}
