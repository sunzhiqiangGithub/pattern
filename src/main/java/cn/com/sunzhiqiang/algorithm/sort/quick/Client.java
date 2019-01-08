package cn.com.sunzhiqiang.algorithm.sort.quick;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new QuickSort();
        int[] sortedArray = sort.sort(new int[]{5, 1, 6, 8, 10, 100, 55, 300, 999, 76, 1, 2, 4, 8});

        System.out.println(Arrays.toString(sortedArray));
    }
}
