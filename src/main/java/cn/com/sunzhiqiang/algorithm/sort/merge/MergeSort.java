package cn.com.sunzhiqiang.algorithm.sort.merge;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 归并排序
 */
public class MergeSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        if (waitingSortArray.length < 2) {
            return waitingSortArray;

        }else{
            int middle = waitingSortArray.length / 2;
            int[] leftWaitingSortArray = Arrays.copyOfRange(waitingSortArray, 0, middle);
            int[] rightWaitingSortArray = Arrays.copyOfRange(waitingSortArray, middle, waitingSortArray.length);
            int[] leftSortedArray = sort(leftWaitingSortArray);
            int[] rightSortedArray = sort(rightWaitingSortArray);

            return mergeSortedArray(leftSortedArray, rightSortedArray);
        }
    }

    private int[] mergeSortedArray(int[] leftSortedArray, int[] rightSortedArray) {

        int[] resultSortedArray = new int[leftSortedArray.length + rightSortedArray.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSortedArray.length && j < rightSortedArray.length) {
            if (leftSortedArray[i] < rightSortedArray[j]) {
                resultSortedArray[k++] = leftSortedArray[i++];
            } else {
                resultSortedArray[k++] = rightSortedArray[j++];
            }
        }

        if (i < leftSortedArray.length) {
            while (i < leftSortedArray.length) {
                resultSortedArray[k++] = leftSortedArray[i++];
            }
        } else if (j < rightSortedArray.length) {
            while (j < rightSortedArray.length) {
                resultSortedArray[k++] = rightSortedArray[j++];
            }
        }

        return resultSortedArray;
    }
}
