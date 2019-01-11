package cn.com.sunzhiqiang.data.structure.heap;

        import java.util.Arrays;

/**
 * @author sunzhiqiang
 * @date 2019/1/11
 * @desc 堆测试类
 */
public class Client {

    public static void main(String[] args) {

        Heap.MaxHeap<Integer> maxHeap = new Heap.MaxHeap<>(new Integer[]{3, 1, 2, 5, 10, 23, 41, 12, 14, 78, 65, 111, 443, 532, 1, 2, 3});
        Integer[] integers = maxHeap.heapSort();

        System.out.println(Arrays.toString(integers));
    }
}
