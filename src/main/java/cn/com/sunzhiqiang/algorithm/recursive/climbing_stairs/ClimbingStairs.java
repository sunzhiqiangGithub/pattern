package cn.com.sunzhiqiang.algorithm.recursive.climbing_stairs;

/**
 * 功能描述: 爬楼梯
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class ClimbingStairs {

    public static void main(String[] args) {

        int result = climbStairs(5);
        System.out.println(result);

        result = climbStairs2(5);
        System.out.println(result);
    }

    public static int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs2(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int pre1 = 1;
        int pre2 = 2;
        int result = 3;
        for (int i = 3; i <= n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }

        return result;
    }
}
