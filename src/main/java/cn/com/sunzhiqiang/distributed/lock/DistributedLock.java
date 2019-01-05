package cn.com.sunzhiqiang.distributed.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 功能描述: 分布式锁接口
 *
 * @author sunzhiqiang
 * @create 2019-01-05
 */
public interface DistributedLock extends Lock {

    @Override
    default void lockInterruptibly() {
        throw new DistributedLockException("不支持的方法");
    }

    @Override
    default Condition newCondition() {
        throw new DistributedLockException("不支持的方法");
    }
}
