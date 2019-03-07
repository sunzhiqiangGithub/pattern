package cn.com.sunzhiqiang.data.structure.queue;

import java.util.LinkedList;

/**
 * 功能描述: 通过链表实现队列
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class MyLinkQueue<E> {

    private LinkedList<E> innerLink;

    private int count;
    private final int size;

    public MyLinkQueue(int size) {
        innerLink = new LinkedList<>();
        this.size = size;
    }

    public synchronized boolean offer(E o) {

        if (count == size) {
            return false;
        }

        innerLink.addLast(o);
        count++;

        return true;
    }

    public synchronized E pop() {

        if (count == 0) {
            return null;
        }

        E e = innerLink.removeFirst();
        count--;

        return e;
    }

    public synchronized boolean isFull() {

        if (count == size) {
            return true;
        }

        return false;
    }

    public synchronized boolean isEmpty() {

        if (count == 0) {
            return true;
        }

        return false;
    }

    public synchronized E peek() {

        return innerLink.getFirst();
    }

    public String toString() {

        return innerLink.toString();
    }
}
