package cn.com.sunzhiqiang.algorithm.sort.select;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new SelectSort();
        int[] sortedArray = sort.sort(new int[]{5, 1, 2, 190, 10, 44, 35, 67, 88, 2, 2, 1, 17});

        System.out.println(Arrays.toString(sortedArray));
    }
}
