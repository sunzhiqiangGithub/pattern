package cn.com.sunzhiqiang.algorithm.shortest_path.bellman_ford;

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
class Result {

    /**
     * 封装源点到各个顶点的最短距离
     */
    private int[] dis;

    /**
     * 封装中间点集合
     */
    private Map<String, List<Integer>> intermediatePointListMap = new HashMap<>();
}
