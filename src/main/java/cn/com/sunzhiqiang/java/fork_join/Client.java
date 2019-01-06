package cn.com.sunzhiqiang.java.fork_join;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-06
 */
public class Client {

    public static void main(String[] args) {

        int[] array = new int[]{6, 8, 0, 9, 1, 2, 5, 10, 40, 88, 100, 2000, 13};

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        MergeSortTask mergeSortTask = new MergeSortTask(array);
        int[] resultArray = forkJoinPool.invoke(mergeSortTask);

        System.out.println(Arrays.toString(resultArray));
    }
}
