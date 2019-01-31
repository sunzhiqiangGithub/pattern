package cn.com.sunzhiqiang.data.structure.link;

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

        node.prev = head;
        node.next = head.next;

        if (head.next == null) {
            tail = node;
        } else {
            head.next.prev = node;
        }
        head.next = node;

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

        Node<T> tail = this.tail;
        tail.prev.next = null;
        tail.prev = null;

        this.tail = this.tail.prev;

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

    public synchronized MyLinkedList remove(T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(t)) {
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;

                temp.next = null;
                temp.prev = null;

                break;
            }

            temp = temp.next;
        }

        return this;
    }

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

    public synchronized MyLinkedList insertAfter(T position, T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(position)) {
                Node node = new Node(t);
                node.prev = temp;
                node.next = temp.next;
                temp.next.prev = node;
                temp.next = node;

                break;
            }

            temp = temp.next;
        }

        return this;
    }

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

    public synchronized T getFirst() {

        return head.next == null ? null : (T) head.next.data;
    }

    public synchronized T getLast() {

        return tail == null ? null : tail == head ? null : tail.data;
    }

    public synchronized int size() {

        return size;
    }

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

    public String toInvertString() {

        StringBuilder sb = new StringBuilder("");
        Node temp = tail;
        while (temp != null && temp != head) {
            sb.append(temp.data).append(" ");
            temp = temp.prev;
        }

        return sb.toString();
    }

    static class Node<T> {

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
