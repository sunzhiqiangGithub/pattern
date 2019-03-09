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

        result = maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
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

    public static int[] maxSlidingWindow2(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int maxIndex = getMaxIndex(nums, 0, k - 1);
        int[] result = new int[nums.length - k + 1];
        result[0] = nums[maxIndex];

        for (int i = 1, j = k; i < result.length; i++, j++) {
            if (maxIndex >= i) {
                if (nums[j] >= nums[maxIndex]) {
                    maxIndex = j;
                }
            } else {
                maxIndex = getMaxIndex(nums, i, j);
            }

            result[i] = nums[maxIndex];
        }

        return result;
    }

    private static int getMaxIndex(int[] nums, int start, int end) {

        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] >= nums[maxIndex]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
