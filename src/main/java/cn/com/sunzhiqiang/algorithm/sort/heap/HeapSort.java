package cn.com.sunzhiqiang.algorithm.sort.heap;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 堆排序
 */
public class HeapSort implements Sort {

    @Override
    public int[] sort(int[] waitingSortArray) {

        int[] sortedArray = internalHeapSort(waitingSortArray);

        return sortedArray;
    }

    private int[] internalHeapSort(int[] waitingSortArray) {

        // 初始化最大堆
        for (int i = waitingSortArray.length / 2; i >= 0; i--) {
            // 调整堆结构
            createMaxHeapify(waitingSortArray, waitingSortArray.length, i);
        }

        for (int i = 0; i < waitingSortArray.length; i++) {
            int temp = waitingSortArray[waitingSortArray.length - 1 - i];
            waitingSortArray[waitingSortArray.length - 1 - i] = waitingSortArray[0];
            waitingSortArray[0] = temp;

            createMaxHeapify(waitingSortArray, waitingSortArray.length - 1 - i, 0);
        }

        return waitingSortArray;
    }

    private void createMaxHeapify(int[] waitingSortArray, int heapSize, int i) {

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        boolean exchange = false;
        int maxIndex = i;

        if (left < heapSize && waitingSortArray[maxIndex] < waitingSortArray[left]) {
            maxIndex = left;
            exchange = true;
        }

        if (right < heapSize && waitingSortArray[maxIndex] < waitingSortArray[right]) {
            maxIndex = right;
            exchange = true;
        }

        if (exchange) {
            int temp = waitingSortArray[i];
            waitingSortArray[i] = waitingSortArray[maxIndex];
            waitingSortArray[maxIndex] = temp;

            createMaxHeapify(waitingSortArray, heapSize, maxIndex);
        }
    }
}
