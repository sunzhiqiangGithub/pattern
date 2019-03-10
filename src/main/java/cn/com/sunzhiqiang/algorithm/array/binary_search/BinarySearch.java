package cn.com.sunzhiqiang.algorithm.array.binary_search;

/**
 * 功能描述: 等值二分查找
 *
 * @author sunzhiqiang
 * @create 2019-03-10
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] sortedArray = new int[]{1, 3, 5, 6, 7, 8, 10, 15, 30};
        int value = 10;

        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.binarySearch(sortedArray, value);
        System.out.println(result);
    }

    public int binarySearch(int[] sortedArray, int value) {

        if (sortedArray == null || sortedArray.length == 0) {
            return -1;
        }

        int start = 0;
        int end = sortedArray.length - 1;

        if (value < sortedArray[start] || value > sortedArray[end]) {
            return -1;
        }

        if (value == sortedArray[start]) {
            return start;
        }
        if (value == sortedArray[end]) {
            return end;
        }

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
