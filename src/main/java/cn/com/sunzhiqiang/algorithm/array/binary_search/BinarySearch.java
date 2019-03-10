package cn.com.sunzhiqiang.algorithm.array.binary_search;

/**
 * 功能描述: 等值二分查找
 *
 * @author sunzhiqiang
 * @create 2019-03-10
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] sortedArray = new int[]{1, 3, 5, 6, 6, 6, 10, 15, 30};
        int value = 6;

        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.binarySearch(sortedArray, value);
        System.out.println(result);

        result = binarySearch.binarySearchOfFirstEqual(sortedArray, value);
        System.out.println(result);

        result = binarySearch.binarySearchOfLastEqual(sortedArray, value);
        System.out.println(result);

        result = binarySearch.binarySearchOfFirstGtOrEqual(sortedArray, value);
        System.out.println(result);

        result = binarySearch.binarySearchOfLastLtOrEqual(sortedArray, value);
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

    /**
     * 查找第一个等于value的元素下标
     *
     * @param sortedArray
     * @param value
     * @return
     */
    public int binarySearchOfFirstEqual(int[] sortedArray, int value) {

        int start = 0;
        int end = sortedArray.length - 1;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
            } else {
                if (value == sortedArray[middle - 1]) {
                    end = middle - 1;
                } else {
                    return middle;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个等于value的元素下标
     *
     * @param sortedArray
     * @param value
     * @return
     */
    public int binarySearchOfLastEqual(int[] sortedArray, int value) {

        int start = 0;
        int end = sortedArray.length - 1;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
            } else {
                if (value == sortedArray[middle + 1]) {
                    start = middle + 1;
                } else {
                    return middle;
                }
            }
        }

        return -1;
    }

    /**
     * 查找第一个大于等于value的元素下标
     *
     * @param sortedArray
     * @param value
     * @return
     */
    public int binarySearchOfFirstGtOrEqual(int[] sortedArray, int value) {

        int start = 0;
        int end = sortedArray.length - 1;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
                if (value < sortedArray[middle + 1]) {
                    return middle + 1;
                }
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
                if (value > sortedArray[middle - 1]) {
                    return middle;
                }
            } else {
                if (value == sortedArray[middle - 1]) {
                    end = middle - 1;
                } else {
                    return middle;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于value的元素下标
     *
     * @param sortedArray
     * @param value
     * @return
     */
    public int binarySearchOfLastLtOrEqual(int[] sortedArray, int value) {

        int start = 0;
        int end = sortedArray.length - 1;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (value > sortedArray[middle]) {
                start = middle + 1;
                if (value < sortedArray[middle + 1]) {
                    return middle;
                }
            } else if (value < sortedArray[middle]) {
                end = middle - 1;
                if (value > sortedArray[middle - 1]) {
                    return middle - 1;
                }
            } else {
                if (value == sortedArray[middle + 1]) {
                    start = middle + 1;
                } else {
                    return middle;
                }
            }
        }

        return -1;
    }
}
