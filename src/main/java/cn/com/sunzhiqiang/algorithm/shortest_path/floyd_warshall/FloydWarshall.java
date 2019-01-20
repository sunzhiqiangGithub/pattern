package cn.com.sunzhiqiang.algorithm.shortest_path.floyd_warshall;

import cn.com.sunzhiqiang.algorithm.shortest_path.Result;
import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 功能描述: Floyd-Warshall最短路径算法
 *
 * @author sunzhiqiang
 * @create 2019-01-19
 */
public class FloydWarshall {

    public Result getSortestPath(int pointNum, List<Side> sides) {

        // 初始化矩阵
        int[][] matrix = initMatrix(pointNum, sides);

        Result result = new Result();

        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        // 记录中间点
                        recordIntermediatePoint(result, "" + i + j, k);
                    }
                }
            }
        }

        result.setMatrix(matrix);

        return result;
    }

    private int[][] initMatrix(int pointNum, List<Side> sides) {
        int[][] matrix = new int[pointNum][pointNum];

        // 除以2是防止2个max相加超出int范围
        int max = Integer.MAX_VALUE / 2;

        // 初始化:1-》1 = 0 1 -》其他等于max(无穷大)
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = max;
                }
            }
        }

        // 初始化边信息
        sides.stream().forEach(side -> matrix[side.getStartPoint()][side.getEndPoint()] = side.getWeights());

        return matrix;
    }

    private void recordIntermediatePoint(Result result, String key, int append) {
        List<Integer> tempShortPath = result.getIntermediatePointListMap().get(key);
        if (tempShortPath == null) {
            tempShortPath = Lists.newLinkedList();
            result.getIntermediatePointListMap().put(key, tempShortPath);
        }

        tempShortPath.add(append);
    }
}
