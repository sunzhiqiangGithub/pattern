package cn.com.sunzhiqiang.algorithm.shortest_path;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 最小路径返回结果类
 *
 * @author sunzhiqiang
 * @create 2019-01-20
 */
@Data
public class Result {

    /**
     * 封装最小路径值的矩阵
     */
    private int[][] matrix;

    /**
     * 封装中间点集合
     */
    private Map<String, List<Integer>> intermediatePointListMap = new HashMap<>();
}
