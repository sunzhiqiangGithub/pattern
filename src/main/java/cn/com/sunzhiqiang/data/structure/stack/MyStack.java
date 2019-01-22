package cn.com.sunzhiqiang.data.structure.stack;

import java.util.Arrays;

/**
 * 功能描述: 栈
 *
 * @author sunzhiqiang
 * @create 2019-01-22
 */
public class MyStack<E> {

    private E[] elements;
    private int top;

    private int count;

    public MyStack(int size) {
        elements = (E[]) new Object[size];
        top = 0;
        count = 0;
    }

    public E push(E item) {

        // 扩容
        if (count == elements.length) {
            expandArray();
        }

        elements[top] = item;
        top++;
        count++;

        return item;
    }

    public synchronized E pop() {

        if (count == 0) {
            return null;
        }

        top--;
        count--;
        E e = elements[top];
        elements[top] = null;

        return e;
    }

    public synchronized E peek() {

        if (count == 0) {
            return null;
        }

        E e = elements[top - 1];

        return e;
    }

    public boolean empty() {

        if (count == 0) {
            return true;
        }

        return false;
    }

    private void expandArray() {

        elements = Arrays.copyOf(elements, elements.length * 2);
    }
}
