package cn.com.sunzhiqiang.algorithm.sort.bubble;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new BubbleSort();
        int[] sortedArray = sort.sort(new int[]{1, 10, 3, 2, 5, 100, 999, 20, 14, 2, 7, 8});

        System.out.println(Arrays.toString(sortedArray));
    }
}
