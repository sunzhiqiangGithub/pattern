package cn.com.sunzhiqiang.algorithm.sort.count;

import cn.com.sunzhiqiang.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-14
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new CountSort();
        int[] sortedArray = sort.sort(new int[]{1, 2, 4, 3, 7, 10, 15, 6, 8, 2, 30});

        System.out.println(Arrays.toString(sortedArray));
    }
}
