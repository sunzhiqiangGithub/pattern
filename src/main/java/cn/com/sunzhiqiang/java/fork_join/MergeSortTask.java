package cn.com.sunzhiqiang.java.fork_join;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * 功能描述: 基于fork-join框架的归并排序
 *
 * @author sunzhiqiang
 * @create 2019-01-06
 */
public class MergeSortTask extends RecursiveTask<int[]> {

    private static final int THRESHOLD = 2;

    private int[] waitingSortArray;

    public MergeSortTask(int[] waitingSortArray) {
        this.waitingSortArray = waitingSortArray;
    }

    @Override
    protected int[] compute() {

        if (waitingSortArray.length < THRESHOLD) {
            return waitingSortArray;

        } else {
            int middle = waitingSortArray.length / 2;
            MergeSortTask subMergeSortTask1 = new MergeSortTask(Arrays.copyOfRange(waitingSortArray, 0, middle));
            MergeSortTask subMergeSortTask2 = new MergeSortTask(Arrays.copyOfRange(waitingSortArray, middle, waitingSortArray.length));
            subMergeSortTask1.fork();
            subMergeSortTask2.fork();
            int[] waitMergeArray1 = subMergeSortTask1.join();
            int[] waitMergeArray2 = subMergeSortTask2.join();

            return mergeArray(waitMergeArray1, waitMergeArray2);
        }
    }

    private int[] mergeArray(int[] waitMergeArray1, int[] waitMergeArray2) {

        int[] resultArray = new int[waitMergeArray1.length + waitMergeArray2.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < waitMergeArray1.length && j < waitMergeArray2.length) {
            if (waitMergeArray1[i] < waitMergeArray2[j]) {
                resultArray[k++] = waitMergeArray1[i++];
            } else {
                resultArray[k++] = waitMergeArray2[j++];
            }
        }

        if (i < waitMergeArray1.length) {
            while (i < waitMergeArray1.length) {
                resultArray[k++] = waitMergeArray1[i++];
            }
        } else if (j < waitMergeArray2.length) {
            while (j < waitMergeArray2.length) {
                resultArray[k++] = waitMergeArray2[j++];
            }
        }

        return resultArray;
    }
}
