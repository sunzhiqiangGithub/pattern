package cn.com.sunzhiqiang.data.structure.skiplist;

import lombok.Data;

import java.util.Arrays;
import java.util.Random;

/**
 * 功能描述: 跳表
 *
 * @author sunzhiqiang
 * @create 2019-01-27
 */
public class SkipList<T extends Comparable<T>> {

    private Node<T> first;
    private int maxLevel;

    private Random random;

    /**
     * 每隔几个结点生成索引
     */
    private int spacing;

    public SkipList(int spacing) {
        this.spacing = spacing;
        random = new Random();
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
        this.maxLevel = maxLevel;
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
                first = preNode;
            }

            if (first == null) {
                return null;
            }
            first = first.down;
        }

        return null;
    }

    /**
     * 增加一个结点
     *
     * @param t
     */
    public void insert(T t) {

        Node<T> first = this.first;
        int curLevel = this.maxLevel;

        int randomLevel = getRandomLevel();

        while (first != null) {
            Node<T> levelFirst = first;
            Node<T> preNode = null;
            Node<T> upNode = null;
            while (levelFirst != null) {
                // 如果插入的元素已经存在，直接返回
                if (t.compareTo(levelFirst.data) == 0) {
                    return;
                }
                if (t.compareTo(levelFirst.data) < 0) {
                    if (randomLevel >= curLevel) {
                        Node<T> waitingInsertNode = new Node<>(t);
                        waitingInsertNode.next = levelFirst;
                        preNode.next = waitingInsertNode;
                        if (upNode != null) {
                            upNode.down = waitingInsertNode;
                        }
                        upNode = waitingInsertNode;
                    }
                    first = preNode;
                    break;
                }
                preNode = levelFirst;
                levelFirst = levelFirst.next;
                first = preNode;
            }

            first = first.down;
            curLevel--;
        }
    }

    private int getRandomLevel() {

        int randomLevel = random.nextInt(maxLevel);
        return randomLevel;
    }

    /**
     * 删除一个结点
     *
     * @param t
     */
    public void delete(T t) {

        Node<T> first = this.first;

        while (first != null) {
            Node<T> levelFirst = first;
            Node<T> curNode = null;
            while (levelFirst != null) {
                if (t.compareTo(levelFirst.data) == 0) {
                    if (curNode != null) {
                        first = curNode;
                        curNode.next = levelFirst.next;
                    } else {
                        // 要删除的是头结点
                        // 把头结点的下一个结点删除
                        // 头结点的值更新为头结点的下一个结点的值
                        if (first.down == null) {
                            T newFirtstData = levelFirst.next == null
                                    ? null : levelFirst.next.data;
                            delete(newFirtstData);
                            updateFirst(newFirtstData);
                            return;
                        }
                        first = levelFirst;
                        break;
                    }
                    break;
                } else if (t.compareTo(levelFirst.data) < 0) {
                    break;
                }
                curNode = levelFirst;
                levelFirst = levelFirst.next;
                first = curNode;
            }

            first = first.down;
        }
    }

    private void updateFirst(T newFirtstData) {
        Node<T> first = this.first;
        while (first != null) {
            first.data = newFirtstData;
            first = first.down;
        }
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
