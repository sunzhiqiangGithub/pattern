package cn.com.sunzhiqiang.algorithm.sort.insert;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new InsertSort();
        int[] sortedArray = sort.sort(new int[]{45, 12, 78, 90, 111, 1, 5, 9, 10});

        System.out.println(Arrays.toString(sortedArray));
    }
}
