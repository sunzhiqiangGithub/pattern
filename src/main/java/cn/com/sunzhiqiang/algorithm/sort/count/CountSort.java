package cn.com.sunzhiqiang.algorithm.sort.count;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * 功能描述: 计数排序
 *
 * @author sunzhiqiang
 * @create 2019-01-14
 */
public class CountSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        int max = maxValue(waitingSortArray);

        int[] countArray = new int[max + 1];
        for (int i = 0; i < waitingSortArray.length; i++) {
            countArray[waitingSortArray[i]]++;
        }

        int j = 0;
        for (int i = 0; i < countArray.length; i++) {
            while (countArray[i] > 0) {
                waitingSortArray[j++] = i;
                countArray[i]--;
            }
        }

        return waitingSortArray;
    }

    private int maxValue(int[] waitingSortArray) {

        int max = Integer.MIN_VALUE;
        int indexOfMaxValue = -1;
        for (int i = 0; i < waitingSortArray.length; i++) {
            if (waitingSortArray[i] > max) {
                indexOfMaxValue = i;
            }
        }
        return waitingSortArray[indexOfMaxValue];
    }
}
