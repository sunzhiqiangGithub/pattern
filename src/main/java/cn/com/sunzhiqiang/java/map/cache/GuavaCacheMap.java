package cn.com.sunzhiqiang.java.map.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: 使用guava cache
 *
 * @author sunzhiqiang
 * @create 2018-08-25
 */
public class GuavaCacheMap<K, V> {

    private LoadingCache<K, V> cache = null;

    public GuavaCacheMap() {
        cacheInit();
    }

    private void cacheInit() {

        cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30L, TimeUnit.SECONDS)
                .expireAfterAccess(30L, TimeUnit.SECONDS)
                .refreshAfterWrite(20L, TimeUnit.SECONDS)
                .weakKeys()
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K k) throws Exception {
                        return null;
                    }
                });
    }

    public V get(K k) throws ExecutionException {

        return cache.get(k);
    }

    public void put(K k, V v) {

        cache.put(k, v);
    }
}
