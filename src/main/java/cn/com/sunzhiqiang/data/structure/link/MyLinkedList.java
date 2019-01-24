package cn.com.sunzhiqiang.data.structure.link;

/**
 * 功能描述: 双向链表
 *
 * @author sunzhiqiang
 * @create 2019-01-23
 */
public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public MyLinkedList() {
        Node<T> node = new Node();
        head = tail = node;

        size = 0;
    }

    public synchronized MyLinkedList add(T t) {

        Node<T> node = new Node<>();
        node.data = t;

        node.pre = tail;
        tail.next = node;
        tail = tail.next;

        size++;

        return this;
    }

    public synchronized MyLinkedList remove(T t) {

        Node temp = head.next;

        while (temp != null) {
            if (temp.data.equals(t)) {
                temp.next.pre = temp.pre;
                temp.pre.next = temp.next;

                temp.next = null;
                temp.pre = null;

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
                node.pre = temp.pre;
                temp.pre.next = node;
                temp.pre = node;

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
                node.pre = temp;
                node.next = temp.next;
                temp.next.pre = node;
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
            temp = temp.pre;
        }

        return sb.toString();
    }

    static class Node<T> {

        private Node pre;
        private Node next;

        private T data;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }
}
