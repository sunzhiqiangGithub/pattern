package cn.com.sunzhiqiang.distributed.lock;

/**
 * 功能描述: 分布式锁异常
 *
 * @author sunzhiqiang
 * @create 2019-01-05
 */
public class DistributedLockException extends RuntimeException {

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
