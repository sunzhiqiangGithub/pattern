package cn.com.sunzhiqiang.algorithm.sort.shell;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/11
 * @desc 希尔排序
 */
public class ShellSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        int increment = waitingSortArray.length / 2;
        while (increment > 0) {
            for (int i = 1; i < waitingSortArray.length; i++) {
                for (int j = i - 1 + increment; j < waitingSortArray.length; j = j + increment) {
                    if (waitingSortArray[j] < waitingSortArray[j - increment]) {
                        int temp = waitingSortArray[j];
                        waitingSortArray[j] = waitingSortArray[j - increment];
                        waitingSortArray[j - increment] = temp;
                    }
                }
            }

            increment = increment / 2;
        }

        return waitingSortArray;
    }
}
