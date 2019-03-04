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

        int[] nums = new int[]{0, -4, -1, -4, -2, -3, 2}; // -4,-4,-3,-2,-1,0,2

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

        for (int i = 0; i < tempArray.length - 2; i++) {
            if (i > 0 && tempArray[i] == tempArray[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < tempArray.length - 1; j++) {
                if (j > 1 && j != i + 1 && tempArray[j] == tempArray[j - 1]) {
                    continue;
                }
                for (int k = tempArray.length - 1; k > j; k--) {
                    if (k < tempArray.length - 1 && tempArray[k] == tempArray[k + 1]) {
                        continue;
                    }
                    int first = tempArray[i];
                    int second = tempArray[j];
                    int third = tempArray[k];
                    int sum = first + second + third;
                    if (sum < 0) {
                        break;
                    }
                    if (sum > 0) {
                        continue;
                    }
                    List<Integer> list = new ArrayList<>(3);
                    list.add(first);
                    list.add(second);
                    list.add(third);
                    result.add(list);
                }
            }
        }

        return result;
    }
}
