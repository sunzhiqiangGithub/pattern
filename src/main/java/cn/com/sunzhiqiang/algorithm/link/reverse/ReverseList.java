package cn.com.sunzhiqiang.algorithm.link.reverse;

import cn.com.sunzhiqiang.algorithm.link.SingleList;

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
        SingleList.Node p = singleList.head.next;
        if (p.next != null) {
            SingleList.Node q = p.next;
            p.next = null;
            while (q != null) {
                SingleList.Node r = q.next;
                q.next = p;
                p = q;
                q = r;
            }
            singleList.head.next = p;
        }
        singleList.print();
    }
}
