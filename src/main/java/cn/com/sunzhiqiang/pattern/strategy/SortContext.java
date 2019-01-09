package cn.com.sunzhiqiang.pattern.strategy;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 上下文
 */
public class SortContext {

    private Sort sort;

    public SortContext(Sort sort) {
        this.sort = sort;
    }

    public int[] sort(int[] waitingSortArray) {

        return sort.sort(waitingSortArray);
    }
}
