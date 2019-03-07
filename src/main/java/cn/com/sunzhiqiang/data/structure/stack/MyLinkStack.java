package cn.com.sunzhiqiang.data.structure.stack;

import java.util.LinkedList;

/**
 * 功能描述: 用链表实现栈
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class MyLinkStack<E> {

    private LinkedList<E> innerList;
    private int top;
    private int count;

    public MyLinkStack() {
        innerList = new LinkedList<>();
        count = 0;
    }

    public E push(E item) {

        innerList.addFirst(item);
        count++;

        return item;
    }

    public E pop() {

        E e = innerList.getFirst();
        innerList.removeFirst();
        count--;

        return e;
    }

    public E peek() {

        E e = innerList.getFirst();

        return e;
    }

    public boolean empty() {

        if (count == 0) {
            return true;
        }

        return false;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (E e : innerList) {
            sb.append(e).append("\t");
        }

        return sb.toString();
    }
}
