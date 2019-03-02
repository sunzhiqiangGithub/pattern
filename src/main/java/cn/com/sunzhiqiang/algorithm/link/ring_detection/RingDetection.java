package cn.com.sunzhiqiang.algorithm.link.ring_detection;

import cn.com.sunzhiqiang.algorithm.link.SingleCycleList;
import cn.com.sunzhiqiang.algorithm.link.SingleList;

/**
 * 功能描述: 单链表中环的检测
 *
 * @author sunzhiqiang
 * @create 2019-03-02
 */
public class RingDetection {

    public static void main(String[] args) {

        // 单链表
        SingleList<Integer> singleList = new SingleList<>();
        singleList.add(5).add(4).add(3).add(2).add(1);

        // 单向循环列表
        SingleCycleList<Integer> singleCycleList = new SingleCycleList<>();
        singleCycleList.add(5).add(4).add(3).add(2).add(1);

        // 环检测
        boolean isHasCycle = detectCycle(singleCycleList);
        System.out.println(isHasCycle);

        isHasCycle = detectCycle2(singleList);
        System.out.println(isHasCycle);
    }

    private static boolean detectCycle(SingleCycleList<Integer> singleCycleList) {
        SingleCycleList.Node fast = singleCycleList.head.next;
        SingleCycleList.Node slow = singleCycleList.head.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    private static boolean detectCycle2(SingleList<Integer> singleList) {
        SingleList.Node fast = singleList.head.next;
        SingleList.Node slow = singleList.head.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
