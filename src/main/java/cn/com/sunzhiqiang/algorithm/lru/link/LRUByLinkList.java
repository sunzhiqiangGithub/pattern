package cn.com.sunzhiqiang.algorithm.lru.link;

import cn.com.sunzhiqiang.data.structure.link.MyLinkedList;

/**
 * 功能描述: 通过链表实现LRU算法
 *
 * @author sunzhiqiang
 * @create 2019-01-29
 */
public class LRUByLinkList<T> {

    private MyLinkedList<T> myLinkedList;
    private int maxSize;

    private int size;

    public LRUByLinkList(int maxSize) {
        this.maxSize = maxSize;
        myLinkedList = new MyLinkedList();

        size = 0;
    }

    public void insert(T t) {

        // 如果列表已经满了，淘汰最旧的
        if (size == maxSize) {
            myLinkedList.removeFirst();
        }

        //myLinkedList.add(t);
        size++;
    }
}
