package cn.com.sunzhiqiang.algorithm.sort.bucket;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * 功能描述: 桶排序
 *
 * @author sunzhiqiang
 * @create 2019-01-14
 */
public class BucketSort implements Sort {

    private Sort sort;

    public BucketSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int[] sort(int[] waitingSortArray) {

        int max = waitingSortArray[0];
        int min = waitingSortArray[0];
        for (int i = 0; i < waitingSortArray.length; i++) {
            if (waitingSortArray[i] > max) {
                max = waitingSortArray[i];
            } else if (waitingSortArray[i] < min) {
                min = waitingSortArray[i];
            }
        }

        int bucketCount = (max - min) / 5 + 1;
        int[][] buckets = new int[bucketCount][0];

        int index = 0;
        for (int i = 0; i < waitingSortArray.length; i++) {
            index = (waitingSortArray[i] - min) / 5;
            buckets[index] = arrAppend(buckets[index], waitingSortArray[i]);
        }

        int arrIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            int[] sortedArray = sort.sort(buckets[i]);
            if (sortedArray.length <= 0) {
                continue;
            }
            for (int j = 0; j < sortedArray.length; j++) {
                waitingSortArray[arrIndex++] = sortedArray[j];
            }
        }

        return waitingSortArray;
    }

    private int[] arrAppend(int[] bucket, int value) {

        bucket = Arrays.copyOf(bucket, bucket.length + 1);
        bucket[bucket.length - 1] = value;

        return bucket;
    }
}
