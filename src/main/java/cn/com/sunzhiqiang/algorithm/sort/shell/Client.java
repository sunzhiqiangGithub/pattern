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

        sort(new int[]{2, 4, 6, 1, 65, 23, 12, 89, 12, 4, 8, 3});
    }

    public static void sort(int[] arr) {
        // i表示希尔排序中的第n/2+1个元素（或者n/4+1）
        // j表示希尔排序中从0到n/2的元素（n/4）
        // r表示希尔排序中n/2+1或者n/4+1的值
        int i, j, r, tmp;
        // 划组排序
        for (r = arr.length / 2; r >= 1; r = r / 2) {
            for (i = r; i < arr.length; i++) {
                tmp = arr[i];
                j = i - r;
                // 一轮排序
                while (j >= 0 && tmp < arr[j]) {
                    arr[j + r] = arr[j];
                    j -= r;
                }
                arr[j + r] = tmp;
            }
            System.out.println(i + ":" + Arrays.toString(arr));
        }
    }
}
