package cn.com.sunzhiqiang.algorithm.array.majority;

import java.util.Arrays;

/**
 * 功能描述: 求众数
 *
 * @author sunzhiqiang
 * @create 2019-03-04
 */
public class Majority {

    public static void main(String[] args) {

        int[] array = new int[]{2};

        int result = majorityElement(array);
        System.out.println(result);
    }

    public static int majorityElement(int[] nums) {

        int[] tempArray = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tempArray);

        int length = tempArray.length;
        int count = 1;
        int maxCount = 1;
        int mode = tempArray[0];

        for (int i = 1; i < length; i++) {
            if (tempArray[i] == tempArray[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count > maxCount) {
                maxCount = count;
                mode = tempArray[i];
            }
        }

        return mode;
    }
}
