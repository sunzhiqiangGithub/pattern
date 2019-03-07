package cn.com.sunzhiqiang.algorithm.recursive.fibonacci;

import java.security.InvalidParameterException;

/**
 * 功能描述: 斐波那契数列
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class FibonacciSequence {

    public static void main(String[] args) {

        System.out.println(getValueOfSequenceByN(5));
    }

    private static int getValueOfSequenceByN(int n) {

        if (n <= 0) {
            throw new InvalidParameterException("参数比较大于0");
        }

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return getValueOfSequenceByN(n - 1) + getValueOfSequenceByN(n - 2);
    }
}
