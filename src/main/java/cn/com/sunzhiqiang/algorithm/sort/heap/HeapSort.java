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

        int[] minHeap = createMinHeap(waitingSortArray);

        return minHeap;
    }

    private int[] createMinHeap(int[] waitingSortArray) {

        int[] minHeap = new int[waitingSortArray.length];

        for (int i = 0; i < minHeap.length; i++) {

            // 生成堆结构
            for (int j = waitingSortArray.length - 1 - i; j > 0; j--) {
                if (waitingSortArray[j] < waitingSortArray[j / 2]) {
                    int temp = waitingSortArray[j];
                    waitingSortArray[j] = waitingSortArray[j / 2];
                    waitingSortArray[j] = temp;
                }
            }

            minHeap[i] = waitingSortArray[0];

            waitingSortArray[0] = waitingSortArray[waitingSortArray.length - 1 - i];
        }

        return minHeap;
    }
}
