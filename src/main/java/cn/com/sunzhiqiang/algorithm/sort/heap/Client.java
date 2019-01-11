package cn.com.sunzhiqiang.algorithm.sort.heap;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new HeapSort();
        int[] sortedArray = sort.sort(new int[]{3, 7, 10, 2, 8, 111, 2, 5, 7, 9, 25, 34});

        System.out.println(Arrays.toString(sortedArray));
    }
}
