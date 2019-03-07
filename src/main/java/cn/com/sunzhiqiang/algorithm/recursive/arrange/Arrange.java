package cn.com.sunzhiqiang.algorithm.recursive.arrange;

/**
 * 功能描述: 全排列
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class Arrange {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3};

        printFullArrange(array, 0, array.length - 1);
    }

    private static void printFullArrange(int[] array, int start, int end) {

        if (start == end) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + "\t");
            }
            System.out.println();

        }else {
            for (int i = start; i <= end; i++) {
                int temp = array[i];
                array[i] = array[start];
                array[start] = temp;
                printFullArrange(array, start + 1, end);
                temp = array[i];
                array[i] = array[start];
                array[start] = temp;
            }
        }
    }
}
