package cn.com.sunzhiqiang.data.structure.link;

import lombok.Data;

/**
 * 功能描述: 双向链表
 *
 * @author sunzhiqiang
 * @create 2019-01-23
 */
public class MyLinkedList<T> {

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 尾结点
     */
    private Node<T> tail;

    /**
     * 链表的大小
     */
    private int size;

    public MyLinkedList() {
        Node<T> node = new Node();
        head = tail = node;

        size = 0;
    }

    /**
     * 在尾部添加
     *
     * @param t
     * @return
     */
    public synchronized MyLinkedList addLast(T t) {

        Node<T> node = new Node<>(t);

        node.prev = tail;
        tail.next = node;
        tail = tail.next;

        size++;

        return this;
    }

    /**
     * 在头部添加
     *
     * @param t
     * @return
     */
    public synchronized MyLinkedList addFirst(T t) {

        Node<T> node = new Node<>(t);

        return addFirstNode(node);
    }

    public synchronized MyLinkedList addFirstNode(Node first) {

        first.prev = head;
        first.next = head.next;

        if (head.next == null) {
            tail = first;
        } else {
            head.next.prev = first;
        }
        head.next = first;

        return this;
    }

    /**
     * 在尾部删除
     *
     * @return
     */
    public synchronized MyLinkedList removeLast() {

        // 空链表直接返回
        if (head.next == null) {
            return this;
        }

        Node<T> deletedNode = tail;
        tail = tail.prev;

        deletedNode.prev.next = null;
        deletedNode.prev = null;

        return this;
    }

    /**
     * 在头部删除
     *
     * @return
     */
    public synchronized MyLinkedList removeFirst() {

        if (head.next == null) {
            return this;
        }

        Node<T> first = head.next;
        // 只有一个结点
        if (first.next == null) {
            head.next = null;
            first.prev = null;
            tail = head;

        } else {
            first.next.prev = head;
            head.next = first.next;
            first.next = null;
            first.prev = null;
        }

        return this;
    }

    /**
     * 删除元素t
     *
     * @param t
     * @return
     */
    public synchronized MyLinkedList remove(T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(t)) {
                if (temp == tail) {
                    removeLast();

                } else {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;

                    temp.next = null;
                    temp.prev = null;
                }

                break;
            }

            temp = temp.next;
        }

        return this;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index
     * @return
     */
    public synchronized MyLinkedList remove(int index) {

        // 不存在该元素，直接返回
        if (index < 0 || index > size - 1) {
            return this;
        }

        if (index == 0) {
            removeFirst();
            return this;
        }

        if (index == size - 1) {
            removeLast();
            return this;
        }

        Node temp = null;
        // 出现在后半部分，从tail开始遍历
        if (index > (size - 1) / 2) {
            temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }

        } else {
            temp = head.next;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }

        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        temp.next = null;
        temp.prev = null;

        return this;
    }

    /**
     * 在元素position前插入元素t
     *
     * @param position
     * @param t
     * @return
     */
    public synchronized MyLinkedList insertBefore(T position, T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(position)) {
                Node node = new Node(t);
                node.next = temp;
                node.prev = temp.prev;
                temp.prev.next = node;
                temp.prev = node;

                break;
            }

            temp = temp.next;
        }

        return this;
    }

    /**
     * 在元素position后面插入元素t
     *
     * @param position
     * @param t
     * @return
     */
    public synchronized MyLinkedList insertAfter(T position, T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(position)) {
                Node node = new Node(t);
                node.prev = temp;
                node.next = temp.next;

                // temp是尾结点
                if (temp.next == null) {
                    temp.next = node;
                    tail = node;
                } else {
                    temp.next.prev = node;
                    temp.next = node;
                }

                break;
            }

            temp = temp.next;
        }

        return this;
    }

    /**
     * 查询链表是否包含元素t
     *
     * @param t
     * @return
     */
    public synchronized boolean contain(T t) {

        Node temp = head.next;
        while (temp != null) {
            if (temp.data.equals(t)) {
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public synchronized T getFirst() {

        return head.next == null ? null : (T) head.next.data;
    }

    /**
     * 获取第一个结点
     *
     * @return
     */
    public synchronized Node<T> getFirstNode() {

        return head.next;
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public synchronized T getLast() {

        return tail == null ? null : tail == head ? null : tail.data;
    }

    /**
     * 获取最后一个结点
     *
     * @return
     */
    public synchronized Node<T> getLastNode() {

        return tail;
    }

    /**
     * 链表的大小
     *
     * @return
     */
    public synchronized int size() {

        return size;
    }

    /**
     * 链表字符串
     *
     * @return
     */
    public String toString() {

        Node temp = head.next;

        StringBuilder sb = new StringBuilder("");
        while (temp != null) {
            Node tempNode = temp;
            sb.append(tempNode.data).append(" ");

            temp = temp.next;
        }

        return sb.toString();
    }

    /**
     * 链表反向字符串
     *
     * @return
     */
    public String toInvertString() {

        StringBuilder sb = new StringBuilder("");
        Node temp = tail;
        while (temp != null && temp != head) {
            sb.append(temp.data).append(" ");
            temp = temp.prev;
        }

        return sb.toString();
    }

    /**
     * 链表结点
     *
     * @param <T>
     */
    @Data
    public static class Node<T> {

        private Node prev;
        private Node next;

        private T data;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
