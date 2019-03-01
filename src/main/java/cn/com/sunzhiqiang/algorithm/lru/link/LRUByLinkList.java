package cn.com.sunzhiqiang.algorithm.lru.link;

import cn.com.sunzhiqiang.data.structure.link.MyLinkedList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: 通过链表实现LRU算法
 *
 * @author sunzhiqiang
 * @create 2019-01-29
 */
public class LRUByLinkList<T> {

    private Map<T, MyLinkedList.Node> map = Maps.newHashMap();

    private MyLinkedList<T> myLinkedList;
    private final int maxSize;

    private int size;

    public LRUByLinkList(int maxSize) {
        this.maxSize = maxSize;
        myLinkedList = new MyLinkedList();

        size = 0;
    }

    /**
     * LRU插入
     *
     * @param t
     */
    public void insert(T t) {

        // 查找指定元素是否存在
        MyLinkedList.Node<T> node = map.get(t);
        if (node == null) {
            MyLinkedList.Node first = new MyLinkedList.Node(t);
            if (size == maxSize) {
                myLinkedList.removeLast();
                myLinkedList.addFirstNode(first);
            } else {
                myLinkedList.addFirstNode(first);
            }

            map.put(t, first);
            size++;

        } else {
            if (node != myLinkedList.getFirstNode()) {
                if (node == myLinkedList.getLastNode()) {
                    myLinkedList.removeLast();
                    myLinkedList.addFirst(t);
                } else {
                    node.getPrev().setNext(node.getNext());
                    node.getNext().setPrev(node.getPrev());
                    node.setPrev(null);
                    node.setNext(null);
                    myLinkedList.addFirst(t);
                }
            }
        }
    }

    /**
     * 获取LRU数组
     *
     * @return
     */
    public List<T> getList() {

        List<T> arrayList = Lists.newArrayListWithCapacity(myLinkedList.size());

        MyLinkedList.Node<T> first = myLinkedList.getFirstNode();
        while (first != null) {
            arrayList.add(first.getData());
            first = first.getNext();
        }

        return arrayList;
    }

    /**
     * 打印LRU
     */
    public void printList() {

        System.out.println(myLinkedList.toString());
    }
}
