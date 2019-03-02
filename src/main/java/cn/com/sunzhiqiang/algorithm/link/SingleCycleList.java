package cn.com.sunzhiqiang.algorithm.link;

/**
 * 功能描述: 单向循环链表
 *
 * @author sunzhiqiang
 * @create 2019-03-02
 */
public class SingleCycleList<T> {

    public Node<T> head;

    public SingleCycleList() {
        head = new Node<>(null);
    }

    public SingleCycleList add(T t) {
        Node<T> node = new Node<>(t);

        if (head.next == null) {
            head.next = node;
            node.next = head.next;
        } else {
            node.next = head.next;
            head.next = node;
        }

        return this;
    }

    public void print() {
        Node<T> first = head.next;
        while (first != null) {
            System.out.print(first.data + "\t");
            first = first.next;
        }
        System.out.println();
    }

    public static class Node<T> {

        public Node next;
        public T data;

        public Node(T data) {
            this.data = data;
        }
    }
}
