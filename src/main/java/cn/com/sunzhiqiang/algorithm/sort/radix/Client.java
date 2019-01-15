package cn.com.sunzhiqiang.algorithm.sort.radix;

import cn.com.sunzhiqiang.algorithm.sort.Sort;
import cn.com.sunzhiqiang.algorithm.sort.insert.InsertSort;

import java.util.Arrays;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-15
 */
public class Client {

    public static void main(String[] args) {

        Sort sort = new RadixSort(new InsertSort());
        int[] sortedArray = sort.sort(new int[]{5, 6, 1, 3, 10, 999, 75, 333, 1, 6, 8, 200});

        System.out.println(Arrays.toString(sortedArray));
    }
}
