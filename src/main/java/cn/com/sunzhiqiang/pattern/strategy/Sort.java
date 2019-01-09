package cn.com.sunzhiqiang.pattern.strategy;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 抽象策略（排序接口）
 */
public interface Sort {

    int[] sort(int[] waitingSortArray);
}
