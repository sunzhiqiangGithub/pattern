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

        for (int i = 0, dev = 1; i <= maxDigit; i++, dev *= 10) {
            int[][] buckets = new int[20][0];
            for (int j = 0; j < waitingSortArray.length; j++) {
                int index = waitingSortArray[j] / dev % 10 + 10;
                buckets[index] = arrAppend(buckets[index], waitingSortArray[j]);
            }

            int temp = 0;
            for (int[] bucket : buckets) {
                for (int k = 0; k < bucket.length; k++) {
                    waitingSortArray[temp++] = bucket[k];
                }
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
