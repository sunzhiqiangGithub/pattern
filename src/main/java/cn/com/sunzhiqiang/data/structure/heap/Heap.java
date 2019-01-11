package cn.com.sunzhiqiang.data.structure.heap;


import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 堆
 */
public class Heap {

    public static class MaxHeap<T extends Comparable> {

        private T[] tArray;
        private int heapSize;

        public MaxHeap(T[] tArray) {
            buildMaxHeap(tArray);
        }

        private void buildMaxHeap(T[] tArray) {
            this.tArray = tArray;
            heapSize = tArray.length;

            for (int i = parent(heapSize - 1); i >= 0; i--) {
                maxHeapify(i);
            }
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int left(int i) {
            return 2 * i + 1;
        }

        private int right(int i) {
            return 2 * i + 2;
        }

        private void maxHeapify(int i) {

            int left = left(i);
            int right = right(i);

            int max = i;
            if (left <= heapSize - 1 && tArray[left].compareTo(tArray[max]) > 0) {
                max = left;
            }
            if (right <= heapSize - 1 && tArray[right].compareTo(tArray[max]) > 0) {
                max = right;
            }

            if (max != i) {
                T temp = tArray[i];
                tArray[i] = tArray[max];
                tArray[max] = temp;

                maxHeapify(max);
            }
        }

        public T[] heapSort(T[] tArray) {

            buildMaxHeap(tArray);

            return heapSort();
        }

        public T[] heapSort() {

            for (int i = 0; i < tArray.length; i++) {
                T temp = tArray[heapSize - 1];
                tArray[heapSize - 1] = tArray[0];
                tArray[0] = temp;
                heapSize--;

                maxHeapify(0);
            }

            return Arrays.copyOf(tArray, tArray.length);
        }
    }
}
