package cn.com.sunzhiqiang.algorithm.link.reverse;

/**
 * 功能描述: 单链表反转
 *
 * @author sunzhiqiang
 * @create 2019-03-01
 */
public class ReverseList {

    public static void main(String[] args) {

        // 1.初始化单链表
        SingleList singleList = new SingleList();
        singleList.add(1).add(2).add(3).add(4).add(5);
        singleList.print();

        // 反转单链表
        Node p = singleList.head.next;
        if (p.next != null) {
            Node q = p.next;
            p.next = null;
            while (q != null) {
                Node r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            singleList.head.next = p;
        }
        singleList.print();
    }

    static class SingleList<T> {

        private Node<T> head;

        public SingleList() {
            head = new Node<>(null);
        }

        public SingleList add(T t) {
            Node<T> node = new Node<>(t);

            if (head.next == null) {
                head.next = node;
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
    }

    static class Node<T> {

        private Node next;
        private T data;

        public Node(T data) {
            this.data = data;
        }
    }
}
