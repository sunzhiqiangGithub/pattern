package cn.com.sunzhiqiang.algorithm.link.delete_position;

import cn.com.sunzhiqiang.algorithm.link.SingleList;

/**
 * 功能描述: 删除链表倒数第n个位置的元素
 *
 * @author sunzhiqiang
 * @create 2019-03-03
 */
public class DeleteOfNPostion {

    public static void main(String[] args) {

        // 创建单链表
        SingleList<Integer> singleList = new SingleList<>();
        singleList.add(5).add(4).add(3).add(2).add(1);
        singleList.print();

        // 要删除的位置（倒数）
        int deletePositon = 4;
        SingleList.Node first = singleList.head.next;
        int count = 0;
        while (first != null) {
            count++;
            first = first.next;
        }
        int positivePostion = count - deletePositon;
        first = singleList.head.next;
        for (int i = 1; i < positivePostion; i++) {
            first = first.next;
        }
        SingleList.Node deleteNode = first.next;
        first.next = deleteNode.next;
        singleList.print();
    }
}
