package cn.com.sunzhiqiang.algorithm.sort.radix;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * 功能描述: 基数排序
 *
 * @author sunzhiqiang
 * @create 2019-01-15
 */
public class RadixSort implements Sort {

    private Sort sort;

    public RadixSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int[] sort(int[] waitingSortArray) {

        int max = getMaxValue(waitingSortArray);
        int maxDigit = getDigit(max);

        int[][] buckets = new int[maxDigit][0];
        for (int i = 0; i < waitingSortArray.length; i++) {
            buckets[getDigit(waitingSortArray[i]) - 1] =
                    arrAppend(buckets[getDigit(waitingSortArray[i]) - 1], waitingSortArray[i]);
        }

        int temp = 0;
        for (int i = 0; i < buckets.length; i++) {
            int[] sortedArray = sort.sort(buckets[i]);
            for (int j = 0; j < sortedArray.length; j++) {
                waitingSortArray[temp++] = sortedArray[j];
            }
        }

        return waitingSortArray;
    }

    private int getDigit(int max) {

        int maxDigit = 0;
        while (max != 0) {
            maxDigit++;
            max = max / 10;
        }

        return maxDigit;
    }

    private int getMaxValue(int[] waitingSortArray) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < waitingSortArray.length; i++) {
            if (waitingSortArray[i] > max) {
                max = waitingSortArray[i];
            }
        }

        return max;
    }

    private int[] arrAppend(int[] bucket, int value) {

        bucket = Arrays.copyOf(bucket, bucket.length + 1);
        bucket[bucket.length - 1] = value;

        return bucket;
    }
}
