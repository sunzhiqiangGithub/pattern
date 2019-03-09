package cn.com.sunzhiqiang.algorithm.array.sliding_window_maximum;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 功能描述: 滑动窗口最大值
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int[] result = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(result));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        LinkedList<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];

        int position = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.getFirst() == (i - k)) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            position = (i + 1 - k) <= 0 ? 0 : (i + 1 - k);
            result[position] = nums[deque.getFirst()];
        }

        return result;
    }
}
