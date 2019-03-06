package cn.com.sunzhiqiang.algorithm.array.first_miss_positive;

/**
 * 功能描述: 缺失的第一个正数
 *
 * @author sunzhiqiang
 * @create 2019-03-05
 */
public class FirstMissingPositive {

    public static void main(String[] args) {

        int[] array = new int[]{5, 7, 9, 1, 2, 3, 0, 10};
        int result = firstMissingPositive(array);
        System.out.println(result);
    }

    public static int firstMissingPositive(int[] nums) {

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return length + 1;
    }
}
