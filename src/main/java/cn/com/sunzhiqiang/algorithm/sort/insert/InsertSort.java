package cn.com.sunzhiqiang.algorithm.sort.insert;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 插入排序
 */
public class InsertSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        for (int i = 1; i < waitingSortArray.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (waitingSortArray[j] >= waitingSortArray[j - 1]) {
                    break;
                }
                int temp = waitingSortArray[j];
                waitingSortArray[j] = waitingSortArray[j - 1];
                waitingSortArray[j - 1] = temp;
            }
        }

        return waitingSortArray;
    }
}
