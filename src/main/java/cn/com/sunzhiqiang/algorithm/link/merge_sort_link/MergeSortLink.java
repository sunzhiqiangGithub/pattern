package cn.com.sunzhiqiang.algorithm.link.merge_sort_link;

import cn.com.sunzhiqiang.algorithm.link.SingleList;

/**
 * 功能描述: 2个有序的单链表合并
 *
 * @author sunzhiqiang
 * @create 2019-03-02
 */
public class MergeSortLink {

    public static void main(String[] args) {

        // 单链表1
        SingleList<Integer> singleList = new SingleList<>();
        singleList.add(11).add(10).add(5).add(3).add(1);

        // 单链表2
        SingleList<Integer> singleList2 = new SingleList<>();
        singleList2.add(12).add(8).add(6).add(4).add(0);

        // 合并
        SingleList.Node one = singleList.head.next;
        SingleList.Node two = singleList2.head.next;
        SingleList newLink = new SingleList();
        if (one == null) {
            newLink.head.next = two;
        } else if (two == null) {
            newLink.head.next = one;
        } else if (one.data.compareTo(two.data) > 0) {
            newLink.head.next = two;
        } else {
            newLink.head.next = one;
        }
        SingleList.Node nextNode = null;
        SingleList.Node pre = null;
        while (one != null && two != null) {
            if (one.data.compareTo(two.data) > 0) {
                nextNode = two.next;
                two.next = one;
                if (pre != null) {
                    pre.next = two;
                }
                pre = two;
                two = nextNode;
            } else {
                nextNode = one.next;
                one.next = two;
                if (pre != null) {
                    pre.next = one;
                }
                pre = one;
                one = nextNode;
            }
        }

        newLink.print();
    }
}
