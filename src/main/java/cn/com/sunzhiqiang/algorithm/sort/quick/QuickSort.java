package cn.com.sunzhiqiang.algorithm.sort.quick;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 快速排序
 */
public class QuickSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        return quickSortImpl(waitingSortArray, 0, waitingSortArray.length - 1);
    }

    private int[] quickSortImpl(int[] waitingSortArray, int start, int end) {

        if (start >= end) {
            return waitingSortArray;

        } else {
            int j = start - 1;
            for (int i = start; i < end; i++) {
                if (waitingSortArray[i] <= waitingSortArray[end]) {
                    j++;
                    if (i != j) {
                        int temp = waitingSortArray[i];
                        waitingSortArray[i] = waitingSortArray[j];
                        waitingSortArray[j] = temp;
                    }
                }
            }

            j++;
            int temp = waitingSortArray[end];
            waitingSortArray[end] = waitingSortArray[j];
            waitingSortArray[j] = temp;

            quickSortImpl(waitingSortArray, start, j - 1);
            quickSortImpl(waitingSortArray, j + 1, end);

            return waitingSortArray;
        }
    }
}
