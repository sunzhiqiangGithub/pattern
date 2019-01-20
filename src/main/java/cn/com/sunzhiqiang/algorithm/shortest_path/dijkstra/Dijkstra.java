package cn.com.sunzhiqiang.algorithm.shortest_path.dijkstra;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 功能描述: Dijkstra算法
 *
 * @author sunzhiqiang
 * @create 2019-01-20
 */
public class Dijkstra {

    public Result getShortestPath(int pointNum, int source, List<Side> sides) {

        // 初始化矩阵
        int[][] matrix = initMatrix(pointNum, sides);

        // 初始化dis数组
        int[] dis = new int[pointNum];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = matrix[source][i];
        }

        // 初始化book数组
        int[] book = new int[pointNum];
        book[source] = 1;

        Result result = new Result();

        // 算法核心
        for (int i = 0; i < pointNum - 1; i++) {
            int minValue = Integer.MAX_VALUE;
            int indexOfMinValue = -1;
            for (int j = 0; j < dis.length; j++) {
                if (book[j] == 0 && dis[j] < minValue) {
                    minValue = dis[j];
                    indexOfMinValue = j;
                }
            }

            book[indexOfMinValue] = 1;

            // 松弛
            for (int k = 0; k < pointNum; k++) {
                if (dis[indexOfMinValue] + matrix[indexOfMinValue][k] < dis[k]) {
                    dis[k] = dis[indexOfMinValue] + matrix[indexOfMinValue][k];
                    // 记录中间点
                    recordIntermediatePoint(result, "" + source + k, indexOfMinValue);
                }
            }
        }

        result.setDis(dis);

        return result;
    }

    public Result getShortestPathByTable(int pointNum, int source, List<Side> sides) {

        Result result = new Result();

        // 初始化临接表
        List<List<Side>> table = Lists.newArrayListWithExpectedSize(pointNum);
        for (int i = 0; i < pointNum; i++) {
            table.add(Lists.newLinkedList());
        }
        sides.stream().forEach(side -> {
            List<Side> tempSides = table.get(side.getStartPoint());
            tempSides.add(side);
        });

        int max = Integer.MAX_VALUE / 2;

        // 初始化dis数组
        int[] dis = new int[pointNum];
        for (int i = 0; i < dis.length; i++) {
            if (i == source) {
                dis[i] = 0;
            } else {
                dis[i] = max;
            }
        }
        List<Side> sourceSideList = table.get(source);
        for (Side side : sourceSideList) {
            dis[side.getEndPoint()] = side.getWeights();
        }

        // 初始化book数组
        int[] book = new int[pointNum];
        book[source] = 1;

        // 算法核心
        for (int i = 0; i < pointNum - 1; i++) {
            int minValue = Integer.MAX_VALUE;
            int indexOfMinValue = -1;
            for (int j = 0; j < dis.length; j++) {
                if (book[j] == 0 && dis[j] < minValue) {
                    minValue = dis[j];
                    indexOfMinValue = j;
                }
            }

            book[indexOfMinValue] = 1;

            // 松弛
            List<Side> sideList = table.get(indexOfMinValue);
            int size = sideList.size();
            for (Side side : sideList) {
                if (dis[side.getEndPoint()] > dis[indexOfMinValue] + side.getWeights()) {
                    dis[side.getEndPoint()] = dis[indexOfMinValue] + side.getWeights();
                    // 记录中间点
                    recordIntermediatePoint(result, "" + source + side.getEndPoint(), indexOfMinValue);
                }
            }
        }

        result.setDis(dis);

        return result;
    }

    private int[][] initMatrix(int pointNum, List<Side> sides) {
        int[][] matrix = new int[pointNum][pointNum];

        int max = Integer.MAX_VALUE / 2;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = max;
                }
            }
        }

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
