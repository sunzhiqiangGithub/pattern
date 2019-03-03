package cn.com.sunzhiqiang.algorithm.link.midpoint;

import cn.com.sunzhiqiang.algorithm.link.SingleList;

/**
 * 功能描述: 求链表的中点
 *
 * @author sunzhiqiang
 * @create 2019-03-03
 */
public class MidPoint {

    public static void main(String[] args) {

        // 创建一个单链表
        SingleList singleList = new SingleList();
        singleList.add(5).add(4).add(3).add(2).add(1).add(0);
        singleList.print();

        // 获取链表的中间结点
        SingleList.Node fast = singleList.head.next;
        SingleList.Node slow = singleList.head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;

            slow = slow.next;
        }
        System.out.println(slow.data);
    }
}
