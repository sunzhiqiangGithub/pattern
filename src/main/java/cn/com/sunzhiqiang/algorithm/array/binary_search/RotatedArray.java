package cn.com.sunzhiqiang.algorithm.array.binary_search;

/**
 * 功能描述: 旋转数组
 *
 * @author sunzhiqiang
 * @create 2019-03-10
 */
public class RotatedArray {

    public static void main(String[] args) {

        int[] nums = new int[]{3, 1};
        int target = 1;

        RotatedArray rotatedArray = new RotatedArray();
        int result = rotatedArray.search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 2);

            if (nums[start] <= nums[middle]) {
                // start 到 middle有序
                if (target >= nums[start] && target <= nums[middle]) {
                    return binarySearch(nums, start, middle, target);
                }
                start = middle + 1;
            } else {
                // middle到 end有序
                if (target >= nums[middle] && target <= nums[end]) {
                    return binarySearch(nums, middle, end, target);
                }
                end = middle - 1;
            }
        }

        return -1;
    }

    private int binarySearch(int[] sortedArray, int startIndex, int endIndex, int value) {

        int start = startIndex;
        int end = endIndex;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
