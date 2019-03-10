package cn.com.sunzhiqiang.algorithm.array.binary_search;

/**
 * 功能描述: 计算正整数的平方根
 *
 * @author sunzhiqiang
 * @create 2019-03-10
 */
public class SqrtCalc {

    public static void main(String[] args) {

        SqrtCalc sqrtCalc = new SqrtCalc();
        int result = sqrtCalc.mySqrt(8);
        System.out.println(result);
    }

    public int mySqrt(int x) {

        if (x == 0) {
            return 0;
        }

        int start = 1;
        int end = x;

        int middle;
        while (start <= end) {
            middle = start + ((end - start) >> 2);

            if (middle <= x / middle) {
                start = middle + 1;
                if (middle + 1 == x || (middle + 1) > x / (middle + 1)) {
                    return middle;
                }
            } else {
                end = middle - 1;
            }
        }

        return 0;
    }
}
