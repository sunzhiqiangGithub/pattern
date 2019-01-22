package cn.com.sunzhiqiang.algorithm.dfs;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;

import java.util.List;

/**
 * 功能描述: 无向图深度优先遍历
 *
 * @author sunzhiqiang
 * @create 2019-01-22
 */
public class DepthFirst {

    public void depthFirstList(int pointNum, List<Side> sides, int startPoint) {

        int[][] matrix = initMatrix(pointNum, sides);

        int[] book = new int[pointNum];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }

        book[startPoint] = 1;
        dfs(startPoint, book, 0, matrix);
    }

    private void dfs(int point, int[] book, int sum, final int[][] matrix) {
        System.out.println(String.format("顶点[%d]被访问到", point));

        sum++;
        if (sum == matrix.length) {
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[point][i] == 1 && book[i] == 0) {
                book[i] = 1;
                dfs(i, book, sum, matrix);
            }
        }
    }

    private int[][] initMatrix(int pointNum, List<Side> sides) {
        int[][] matrix = new int[pointNum][pointNum];

        int max = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = max;
                }
            }
        }

        int size = sides.size();
        for (int i = 0; i < size; i++) {
            Side tempSide = sides.get(i);
            matrix[tempSide.getStartPoint()][tempSide.getEndPoint()] = 1;
            matrix[tempSide.getEndPoint()][tempSide.getStartPoint()] = 1;
        }

        return matrix;
    }
}
