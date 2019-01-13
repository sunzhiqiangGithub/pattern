package cn.com.sunzhiqiang.algorithm.sort.shell;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/11
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new ShellSort();
        int[] sortedArray = sort.sort(new int[]{2, 4, 6, 1, 65, 23, 12, 89, 12, 4, 8, 3});

        System.out.println(Arrays.toString(sortedArray));
    }
}
