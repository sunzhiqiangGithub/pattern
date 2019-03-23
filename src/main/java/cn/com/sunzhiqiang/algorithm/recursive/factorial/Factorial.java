package cn.com.sunzhiqiang.algorithm.recursive.factorial;

import java.security.InvalidParameterException;

/**
 * 功能描述: 求n的阶乘
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class Factorial {

    public static void main(String[] args) {

        System.out.println(getItemByParam(10));

        System.out.println(getItemByParam(10, 1));
    }

    private static int getItemByParam(int n) {

        if (n <= 0) {
            throw new InvalidParameterException("参数必须大于0");
        }

        if (n == 1) {
            return 1;
        }

        return n * getItemByParam(n - 1);
    }

    private static int getItemByParam(int n, int res) {

        if (n <= 0) {
            throw new InvalidParameterException("参数必须大于0");
        }

        if (n == 1) {
            return res;
        }

        return getItemByParam(n - 1, n * res);
    }
}
