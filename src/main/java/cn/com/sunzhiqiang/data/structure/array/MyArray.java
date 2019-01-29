package cn.com.sunzhiqiang.data.structure.array;

/**
 * 功能描述: 数组
 *
 * @author sunzhiqiang
 * @create 2019-01-28
 */
public class MyArray {

    private int[] array;
    private int size;

    public MyArray(int length) {
        array = new int[length];
        size = 0;
    }

    private MyArray(int[] array, boolean isCreateNewArray) {
        if (isCreateNewArray) {
            if (array == null || array.length == 0) {
                return;
            }
            this.array = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                this.array[i] = array[i];
            }

        } else {
            this.array = array;
        }

        size = array.length;
    }

    public MyArray(int[] array) {
        this(array, true);
    }

    /**
     * 数组中元素的个数
     *
     * @return
     */
    public int size() {

        return size;
    }

    /**
     * 插入元素到指定位置
     *
     * @param data
     */
    public boolean insert(int position, int data) {

        if (position < 0 || position > size) {
            return false;
        }

        // 扩容
        if (size == array.length) {
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }

        this.array[size] = this.array[position];
        this.array[position] = data;
        this.size++;

        return true;
    }

    /**
     * 在数组的末尾追加元素
     *
     * @param data
     * @return
     */
    public MyArray append(int data) {

        if (size == this.array.length) {
            int[] newArray = new int[this.array.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }

        this.array[size] = data;
        size++;

        return this;
    }

    /**
     * 删除指定位置上的数据
     *
     * @param position
     */
    public boolean delete(int position) {

        if (position < 0 || position > size - 1) {
            return false;
        }

        this.array[position] = this.array[size - 1];
        size--;

        return true;
    }

    /**
     * 查找元素，返回元素的下标，没有找到返回-1
     *
     * @param target
     * @return
     */
    public int find(int target) {

        for (int i = 0; i < size; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 反转数组
     *
     * @return
     */
    public MyArray reverse() {

        int[] returnArray = new int[size];

        int bound = size / 2;
        for (int i = 0; i <= bound; i++) {
            returnArray[i] = this.array[size - 1 - i];
            returnArray[size - 1 - i] = this.array[i];
        }

        return new MyArray(returnArray, false);
    }

    /**
     * 返回数组的字符串表示
     *
     * @return
     */
    public String toString() {

        StringBuilder sb = new StringBuilder("[");

        if (size == 0) {
            return sb.append("]").toString();
        }

        for (int i = 0; i < size; i++) {
            sb.append(this.array[i]).append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(",")).append("]");

        return sb.toString();
    }
}
