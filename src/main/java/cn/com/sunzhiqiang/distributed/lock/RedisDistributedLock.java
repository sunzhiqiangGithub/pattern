package cn.com.sunzhiqiang.distributed.lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 基于redis的分布式锁
 *
 * @author sunzhiqiang
 * @create 2019-01-05
 */
public class RedisDistributedLock implements DistributedLock {

    private static final String DEFAULT_LOCK_KEY = "redis_lock_key";
    private static final int DEFAULT_TIMEOUT = 5000;

    private final Jedis jedis;

    private final String owner;
    private String lockKey = DEFAULT_LOCK_KEY;
    private int timeout = DEFAULT_TIMEOUT;

    public RedisDistributedLock() {
        jedis = JedisFactory.getJedis();
        owner = UUID.randomUUID().toString();
    }

    public RedisDistributedLock(int timeout) {
        this();
        this.timeout = timeout;
    }

    public RedisDistributedLock(String lockKey, int timeout) {
        this(timeout);
        this.lockKey = lockKey;
    }

    @Override
    public void lock() {

        while (true) {
            String result = jedis.set(lockKey, owner,
                    "nx", "px", timeout);
            if (result != null && result.equalsIgnoreCase("OK")) {
                return;
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new DistributedLockException("锁获取失败", e);
                }
            }
        }
    }

    @Override
    public boolean tryLock() {

        String result = jedis.set(lockKey, owner,
                "nx", "px", timeout);
        if (result != null && result.equalsIgnoreCase("OK")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {

        long end = System.currentTimeMillis() + unit.toMillis(time);
        while (System.currentTimeMillis() < end) {
            boolean success = tryLock();
            if (success) {
                return true;
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new DistributedLockException("锁获取失败", e);
                }
            }
        }
        return false;
    }

    @Override
    public void unlock() {

        jedis.eval("if redis.call('get', KEYS[1]) == ARGV[1] " +
                        "then return redis.call('del',KEYS[1]) else return 0 end",
                Collections.singletonList(lockKey),
                Collections.singletonList(owner));
    }

    private static class JedisFactory {

        private JedisPool jedisPool;

        private JedisFactory() {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(10);
            poolConfig.setMaxTotal(30);
            jedisPool = new JedisPool(poolConfig, "47.105.76.62", 6379);
        }

        static JedisFactory getInstance() {
            return JedisFactoryHolder.JEDIS_FACTORY;
        }

        static Jedis getJedis() {
            return getInstance().jedisPool.getResource();
        }

        static class JedisFactoryHolder {
            private static final JedisFactory JEDIS_FACTORY = new JedisFactory();
        }
    }
}
