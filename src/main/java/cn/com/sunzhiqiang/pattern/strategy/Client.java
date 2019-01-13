package cn.com.sunzhiqiang.pattern.strategy;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        int[] waitingSortArray = new int[]{2, 3, 2, 6, 290, 4234, 1, 7, 3, 21, 45};

        SortContext sortContext = new SortContext(new MergeSort());
        System.out.println(Arrays.toString(sortContext.sort(waitingSortArray)));

        sortContext.setSort(new QuickSort());
        System.out.println(Arrays.toString(sortContext.sort(waitingSortArray)));
    }
}
