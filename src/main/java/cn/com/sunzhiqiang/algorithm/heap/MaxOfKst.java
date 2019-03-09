package cn.com.sunzhiqiang.algorithm.heap;

/**
 * 功能描述: 求第K大的数
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class MaxOfKst {

    public static void main(String[] args) {

        int[] nums = new int[]{5, 7, 7, 9, 0, 1, 4, 3, 2, 6};
        int k = 2;

        MaxOfKst maxOfKst = new MaxOfKst();
        int result = maxOfKst.getKstMax(nums, 4);
        System.out.println(result);
    }

    public int getKstMax(int[] nums, int k) {

        // 初始化小顶堆
        for (int i = k - 1 / 2; i >= 0; i--) {
            siftdown(nums, k, i);
        }

        int temp;
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                temp = nums[i];
                nums[i] = nums[0];
                nums[0] = temp;

                siftdown(nums, k, 0);
            }
        }

        return nums[0];
    }

    private void siftdown(int[] nums, int k, int parent) {
        int minIndex;
        int temp;
        int left;
        int right;
        while (parent < k) {
            minIndex = parent;
            left = 2 * parent + 1;
            right = 2 * parent + 2;
            if (left < k && nums[left] < nums[minIndex]) {
                minIndex = left;
            }
            if (right < k && nums[right] < nums[minIndex]) {
                minIndex = right;
            }
            if (minIndex != parent) {
                temp = nums[parent];
                nums[parent] = nums[minIndex];
                nums[minIndex] = temp;
            } else {
                break;
            }

            parent = minIndex;
        }
    }
}
