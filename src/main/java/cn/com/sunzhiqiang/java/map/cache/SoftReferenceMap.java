package cn.com.sunzhiqiang.java.map.cache;

import java.lang.ref.SoftReference;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: 使用软引用改进Map缓存
 *
 * @author sunzhiqiang
 * @create 2018-08-25
 */
public class SoftReferenceMap<K, V> {

    private final ConcurrentHashMap<SoftReference<K>, SoftReference<V>> cache = new ConcurrentHashMap<>();

    public V get(K k) {

        Objects.requireNonNull(k);

        SoftReference<K> key = new SoftReference<>(k);
        SoftReference<V> value = cache.get(key);

        return value == null ? null : value.get();
    }

    public V put(K k, V v) {

        Objects.requireNonNull(k);
        Objects.requireNonNull(v);

        SoftReference<K> key = new SoftReference<>(k);
        SoftReference<V> value = new SoftReference<>(v);

        SoftReference<V> oldValue = cache.put(key, value);

        return oldValue == null ? null : oldValue.get();
    }

    public V remove(K k) {

        Objects.requireNonNull(k);

        SoftReference<K> key = new SoftReference<>(k);
        SoftReference<V> oldValue = cache.remove(key);

        return oldValue == null ? null : oldValue.get();
    }
}
