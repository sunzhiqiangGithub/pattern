package cn.com.sunzhiqiang.algorithm.shortest_path.floyd_warshall;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: Floyd-Warshall最短路径算法
 *
 * @author sunzhiqiang
 * @create 2019-01-19
 */
public class FloydWarshall {

    public Result getSortestPath(int[][] matrix) {

        Result result = new Result();

        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        recordShortestPath(result, k, i, j);
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                List<Integer> shortestPath = result.shortPathMap.get("" + i + j);
                if(shortestPath == null){
                    shortestPath = Lists.newArrayList();
                    shortestPath.add(i);
                    shortestPath.add(j);
                    result.shortPathMap.put("" + i + j, shortestPath);
                }else{
                    shortestPath.add(j);
                }
            }
        }

        result.setMatrix(matrix);

        return result;
    }

    private void recordShortestPath(Result result, int k, int i, int j) {
        List<Integer> tempShortPath = result.getShortPathMap().get("" + i + j);
        if (tempShortPath == null) {
            tempShortPath = Lists.newArrayList();
            tempShortPath.add(i);
            tempShortPath.add(k);
            result.shortPathMap.put("" + i + j, tempShortPath);
        } else {
            tempShortPath.add(k);
        }
    }

    @Data
    static class Result {

        private int[][] matrix;
        private Map<String, List<Integer>> shortPathMap = new HashMap<>();
    }
}
