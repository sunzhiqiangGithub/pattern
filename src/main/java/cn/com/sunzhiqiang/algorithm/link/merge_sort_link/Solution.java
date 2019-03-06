package cn.com.sunzhiqiang.algorithm.link.merge_sort_link;

/**
 * 功能描述: 合并K个有序链表
 *
 * @author sunzhiqiang
 * @create 2019-03-06
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        ListNode[] listNodes = new ListNode[3];
        ListNode one = new ListNode(1);
        one.next = new ListNode(4);
        one.next.next = new ListNode(5);
        ListNode two = new ListNode(1);
        two.next = new ListNode(3);
        two.next.next = new ListNode(4);
        ListNode three = new ListNode(2);
        three.next = new ListNode(6);

        listNodes[0] = one;
        listNodes[1] = two;
        listNodes[2] = three;

        ListNode result = mergeKLists(listNodes);
        while (result != null) {
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode newListNode = null;
        ListNode one = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode two = lists[i];

            if (one == null){
                newListNode = two;
            }else if (two == null) {
                newListNode = one;
            } else if (one.val > two.val) {
                newListNode = two;
            } else {
                newListNode = one;
            }

            ListNode newHead = null;
            ListNode preNode = null;
            while (one != null && two != null) {
                if (one.val > two.val) {
                    newHead = two.next;
                    if (preNode != null) {
                        preNode.next = two;
                    }
                    two.next = one;
                    preNode = two;
                    two = newHead;

                }else {
                    newHead = one.next;
                    if (preNode != null) {
                        preNode.next = one;
                    }
                    one.next = two;
                    preNode = one;
                    one = newHead;
                }
            }

            one = newListNode;
        }

        return one;
    }
}
