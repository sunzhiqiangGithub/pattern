package cn.com.sunzhiqiang.algorithm.hash.map;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 功能描述: 实现一个基于链表法解决冲突问题的散列表
 *
 * @author sunzhiqiang
 * @create 2019-03-11
 */
public class MyHashMap<K, V> {

    /**
     * 最大容量
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 默认初始容量
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 默认装载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node[] table;

    private int size;
    private float loadFactor;
    private int threshold;

    public MyHashMap(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 返回map有多少元素
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 返回map是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取元素
     *
     * @param key
     * @return
     */
    public V get(Object key) {

        Objects.requireNonNull(key);

        int hash = hash(key);

        Node<K, V> element = null;

        // table未初始化或者桶内不存在元素直接返回空
        if (table == null || table.length == 0
                || (element = table[hash & (table.length - 1)]) == null) {
            return null;
        }

        do {
            if (element.hash == hash
                    && (element.key == key || element.key.equals(key))) {
                return element.value;
            }
            element = element.next;
        } while (element != null);

        return null;
    }

    /**
     * 判断是否包含key
     *
     * @param key
     * @return
     */
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {

        Objects.requireNonNull(key);

        // table未初始化的话，先初始化
        if (table == null || table.length == 0) {
            table = new Node[threshold];
            threshold = (int) (threshold * loadFactor);
        }

        int hash = hash(key);
        int position;
        Node<K, V> element = null;
        // 桶里没有元素，直接插入一个新的结点
        if ((element = table[position = hash & (table.length - 1)]) == null) {
            Node<K, V> node = new Node<>(hash, key, value, null);
            table[position] = node;
            size++;
            return null;
        }

        // 桶里有key相同的元素，替换value
        do {
            if (hash == element.hash
                    && (key == element.key || key.equals(element.key))) {
                V oldValue = element.value;
                element.value = value;
                return oldValue;
            }
        } while (element != null);

        // 桶里没有key相同的元素，头插法插入一个新的结点
        Node<K, V> head = table[position];
        Node<K, V> newHead = new Node<>(hash, key, value, head);
        table[position] = newHead;
        size++;

        if (size > threshold) {
            table = resize();
        }

        return null;
    }

    /**
     * 删除元素
     *
     * @param key
     * @return
     */
    public V remove(Object key) {

        Objects.requireNonNull(key);

        int hash = hash(key);
        int position = hash & (table.length - 1);
        Node<K, V> deleteNode = table[position];
        if (deleteNode == null) {
            return null;
        }

        Node<K, V> preNode = null;
        do {
            if (hash == deleteNode.hash
                    && (key == deleteNode.key || key.equals(deleteNode.key))) {
                if (preNode == null) {
                    table[position] = deleteNode.next;
                    deleteNode.next = null;
                    size--;
                    return deleteNode.value;
                } else {
                    preNode.next = deleteNode.next;
                    deleteNode.next = null;
                    size--;
                    return deleteNode.value;
                }
            }
            preNode = deleteNode;
            deleteNode = deleteNode.next;
        } while (deleteNode != null);

        return null;
    }

    /**
     * 清空map
     */
    public void clear() {

        if (table == null || table.length == 0) {
            return;
        }

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    /**
     * 返回一个可遍历的set集合
     *
     * @return
     */
    public Set<Node<K, V>> entrySet() {

        if (table == null || table.length == 0) {
            return new HashSet<>(0);
        }

        Set<Node<K, V>> set = new HashSet<>(size);
        for (int i = 0; i < table.length; i++) {
            Node<K, V> temp = table[i];
            while (temp != null) {
                set.add(temp);
                temp = temp.next;
            }
        }

        return set;
    }

    /**
     * 扩容
     *
     * @return
     */
    final Node[] resize() {

        int oldLength = table.length;

        if (oldLength >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return table;
        }

        int newLength = oldLength < (MAXIMUM_CAPACITY >>> 1)
                ? oldLength << 1 : MAXIMUM_CAPACITY;
        Node[] newTable = new Node[newLength];

        Node[] oldTable = table;
        table = newTable;
        threshold = threshold << 1;

        // 重hash
        for (int i = 0; i < oldLength; i++) {
            Node<K, V> temp = oldTable[i];
            oldTable[i] = null;
            Node<K, V> unChangeHead = null;
            Node<K, V> unChangeTail = null;
            Node<K, V> changeHead = null;
            Node<K, V> changeTail = null;
            while (temp != null) {
                if ((temp.hash & oldLength) == 0) {
                    if (unChangeTail == null) {
                        unChangeHead = temp;
                    } else {
                        unChangeTail.next = temp;
                    }
                    unChangeTail = temp;

                } else {
                    if (changeTail == null) {
                        changeHead = temp;
                    } else {
                        changeTail.next = temp;
                    }
                    changeTail = temp;
                }
            }
            if (unChangeHead != null) {
                table[i] = unChangeHead;
            }
            if (changeHead != null) {
                table[i + oldLength] = changeHead;
            }
        }

        return table;
    }

    /**
     * 计算hash值
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 计算一个大于cap的最小的2的幂
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static class Node<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
