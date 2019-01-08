package cn.com.sunzhiqiang.algorithm.sort.merge;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new MergeSort();
        int[] sortedArray = sort.sort(new int[]{4, 2, 3, 10, 100, 500, 39, 20, 100, 999, 2, 1});

        System.out.println(Arrays.toString(sortedArray));
    }
}
