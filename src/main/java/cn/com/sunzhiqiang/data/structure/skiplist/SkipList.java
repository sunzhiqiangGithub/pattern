package cn.com.sunzhiqiang.data.structure.skiplist;

import lombok.Data;

import java.util.Arrays;

/**
 * 功能描述: 跳表
 *
 * @author sunzhiqiang
 * @create 2019-01-27
 */
public class SkipList<T extends Comparable<T>> {

    private Node<T> first;

    /**
     * 每隔几个结点生成索引
     */
    private int spacing;

    public SkipList(int spacing) {
        this.spacing = spacing;
    }

    /**
     * 创建一个跳表
     *
     * @param data
     */
    public void create(T[] data) {

        if (data == null || data.length == 0) {
            first = null;
        }

        Arrays.sort(data);

        // 初始化最下层数据层链表
        Node<T> head = new Node(data[0]);
        Node<T> tempHead = head;
        for (int i = 1; i < data.length; i++) {
            Node<T> tempNode = new Node<>(data[i]);
            tempHead.next = tempNode;
            tempHead = tempNode;
        }

        // 生成索引层
        // 1.计算需要生成几级索引
        int totalOfNode = data.length;
        int maxLevel = (int) Math.ceil(Math.log(totalOfNode) / Math.log(spacing)) - 1;
        for (int i = 1; i <= maxLevel; i++) {
            // 下一层的头结点
            Node<T> downHead = head;
            // 当前层的头结点
            Node<T> curHead = new Node<>(downHead.data);
            Node<T> tempCurHead = curHead;
            tempCurHead.down = downHead;
            outer:
            while (downHead != null) {
                for (int j = 0; j < spacing; j++) {
                    downHead = downHead.next;
                    if (downHead == null) {
                        break outer;
                    }
                }
                Node<T> tempNode = new Node<>(downHead.data);
                tempNode.down = downHead;
                tempCurHead.next = tempNode;
                tempCurHead = tempNode;
            }
            head = curHead;
        }

        this.first = head;
    }

    /**
     * 查找某个结点，不存在返回空
     *
     * @param t
     * @return
     */
    public Node<T> find(T t) {

        Node<T> first = this.first;

        while (first != null) {
            Node<T> levelFirst = first;
            Node<T> preNode = null;
            while (levelFirst != null) {
                if (t.compareTo(levelFirst.data) < 0) {
                    first = preNode;
                    break;
                } else if (t.compareTo(levelFirst.data) == 0) {
                    if (first.down == null) {
                        return levelFirst;
                    }
                    first = levelFirst;
                    break;
                }
                preNode = levelFirst;
                levelFirst = levelFirst.next;
            }

            if (first == null) {
                return null;
            }
            first = first.down;
        }

        return null;
    }

    /**
     * 打印跳表
     */
    public void printAll() {

        Node<T> first = this.first;

        while (first != null) {
            Node<T> curLevelFirst = first;
            while (curLevelFirst != null) {
                System.out.print(curLevelFirst.data + "\t");
                curLevelFirst = curLevelFirst.next;
            }
            System.out.println();
            first = first.down;
        }
    }

    @Data
    static class Node<T> {

        private T data;

        private Node<T> next;
        private Node<T> down;

        public Node(T data) {
            this.data = data;
        }
    }
}
