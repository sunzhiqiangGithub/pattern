package cn.com.sunzhiqiang.java.map.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述: 最近最少使用Map
 *
 * @author sunzhiqiang
 * @create 2018-08-25
 */
public class LRUMap<K, V> extends LinkedHashMap<K, V> {

    private final int max;

    public LRUMap(int max) {
        super((int) (max * 1.4f), 0.75f, true);
        this.max = max;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > max;
    }

    public synchronized V getValue(K k) {
        return get(k);
    }

    public synchronized V putValue(K k, V v) {
        return put(k, v);
    }

    public synchronized boolean removeValue(K k, V v) {
        return remove(k, v);
    }
}
