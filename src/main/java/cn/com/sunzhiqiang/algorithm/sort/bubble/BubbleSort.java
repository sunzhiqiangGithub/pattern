package cn.com.sunzhiqiang.algorithm.sort.bubble;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 冒泡排序
 */
public class BubbleSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        for (int i = 1; i < waitingSortArray.length; i++) {
            for (int j = 0; j < waitingSortArray.length - i; j++) {

                if (waitingSortArray[j] > waitingSortArray[j + 1]) {
                    int temp = waitingSortArray[j];
                    waitingSortArray[j] = waitingSortArray[j + 1];
                    waitingSortArray[j + 1] = temp;
                }
            }

        }

        return waitingSortArray;
    }
}
