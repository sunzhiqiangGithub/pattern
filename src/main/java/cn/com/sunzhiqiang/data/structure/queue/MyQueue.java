package cn.com.sunzhiqiang.data.structure.queue;

import java.util.Arrays;

/**
 * 功能描述: 队列实现
 *
 * @author sunzhiqiang
 * @create 2019-01-16
 */
public class MyQueue<T> {

    private T[] innerArray;

    private int head;
    private int tail;

    private int count;

    public MyQueue(int size) {
        innerArray = (T[]) new Object[size];
        head = tail = 0;
        count = 0;
    }

    public synchronized boolean add(T o) {

        if (count == innerArray.length) {
            return false;
        }

        innerArray[tail] = o;
        if (tail == innerArray.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
        count++;

        return true;
    }

    public synchronized T remove() {

        if (count == 0) {
            return null;
        }

        T t = innerArray[head];
        innerArray[head] = null;
        if (head == innerArray.length - 1) {
            head = 0;
        } else {
            head++;
        }
        count--;

        return t;
    }

    public String toString() {
        return Arrays.toString(innerArray);
    }
}
