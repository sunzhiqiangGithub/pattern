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
     * 链表中元素个数
     */
    private int size;

    public MyLinkedList() {
        Node<T> node = new Node();
        head = node;

        size = 0;
    }

    /**
     * 在链表末尾添加一个结点
     *
     * @param t
     * @return
     */
    public synchronized MyLinkedList add(T t) {

        Node<T> node = new Node<>();
        node.data = t;

        Node<T> temp = head;
        if (temp.next == null) {
            temp.next = node;
            node.prev = temp;
        } else {
            node.next = temp.next.next;
            node.prev = temp;
            temp.next = node;
        }

        size++;

        return this;
    }

    /**
     * 删除链表中值为t的结点
     *
     * @param t
     * @return
     */
    public synchronized MyLinkedList remove(T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(t)) {
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
                temp.next = null;
                temp.prev = null;

                size--;
                break;
            }

            temp = temp.next;
        }

        return this;
    }

    /**
     * 删除链表的第一个结点
     *
     * @return
     */
    public synchronized MyLinkedList removeFirst() {

        if (head.next == null) {
            return this;
        }

        Node<T> temp = head.next;
        head.next = head.next.next;
        if (head.next != null) {
            head.next.prev = head;
        }
        temp.next = null;
        temp.prev = null;

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

        return null;
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

//        StringBuilder sb = new StringBuilder("");
//        Node temp = tail;
//        while (temp != null && temp != head) {
//            sb.append(temp.data).append(" ");
//            temp = temp.prev;
//        }
//
//        return sb.toString();
        return "";
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
    }
}
