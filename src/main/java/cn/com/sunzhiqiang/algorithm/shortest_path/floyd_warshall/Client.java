package cn.com.sunzhiqiang.algorithm.shortest_path.floyd_warshall;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-19
 */
public class Client {

    public static void main(String[] args) {

        // 顶点个数
        int pointNum = 4;
        // 初始化矩阵
        int[][] matrix = new int[pointNum][pointNum];

        // 描述正无穷
        int max = Integer.MAX_VALUE / 2;

        // 输入边
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = max;
                }
            }
        }
        matrix[0][1] = 2;
        matrix[0][2] = 6;
        matrix[0][3] = 4;
        matrix[1][2] = 3;
        matrix[2][0] = 7;
        matrix[2][3] = 1;
        matrix[3][0] = 5;
        matrix[3][2] = 12;

        FloydWarshall.Result result = new FloydWarshall().getSortestPath(matrix);

        for (int i = 0; i < result.getMatrix().length; i++) {
            for (int j = 0; j < result.getMatrix().length; j++) {
                System.out.print(result.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }

        Map<String, List<Integer>> shortestPathMap = result.getShortPathMap();
        Set<Map.Entry<String, List<Integer>>> entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }
}
