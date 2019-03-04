package cn.com.sunzhiqiang.algorithm.array.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述: 计算数组中3数之和
 *
 * @author sunzhiqiang
 * @create 2019-03-03
 */
public class ThreeSum {

    public static void main(String[] args) {

        int[] nums = new int[]{0, 0, 0, 0}; // -4,-4,-3,-2,-1,0,2

        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> integers : result) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
    }

    /**
     * 计算数组中所有的3数字之和等于0的组合
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        int[] tempArray = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tempArray);

        int i = 0;
        int j = 0;
        int k = 0;
        int first = 0;
        int second = 0;
        int third = 0;
        int sum = 0;

        int length = tempArray.length;

        for (i = 0; i < length - 2; i++) {
            if (i > 0 && tempArray[i] == tempArray[i - 1]) {
                continue;
            }
            first = tempArray[i];
            j = i + 1;
            k = length - 1;
            while (j < k) {
                second = tempArray[j];
                third = tempArray[k];
                sum = first + second + third;
                if (sum < 0) {
                    j++;
                    while (j < k && second == tempArray[j]) {
                        j++;
                    }
                    continue;
                }
                if (sum > 0) {
                    k--;
                    while (j < k && third == tempArray[k]) {
                        k--;
                    }
                    continue;
                }
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(first);
                    list.add(second);
                    list.add(third);
                    result.add(list);
                    k--;
                    while (j < k && third == tempArray[k]) {
                        k--;
                    }
                    j++;
                    while (j < k && second == tempArray[j]) {
                        j++;
                    }
                }
            }
        }

        return result;
    }
}
