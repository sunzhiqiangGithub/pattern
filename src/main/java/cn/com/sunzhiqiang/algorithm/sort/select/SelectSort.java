package cn.com.sunzhiqiang.algorithm.sort.select;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 选择排序
 */
public class SelectSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < waitingSortArray.length - 1; i++) {

            minValue = Integer.MAX_VALUE;
            minIndex = -1;

            for (int j = i; j < waitingSortArray.length; j++) {
                if (waitingSortArray[j] < minValue) {
                    minValue = waitingSortArray[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = waitingSortArray[i];
                waitingSortArray[i] = waitingSortArray[minIndex];
                waitingSortArray[minIndex] = temp;
            }
        }

        return waitingSortArray;
    }
}
