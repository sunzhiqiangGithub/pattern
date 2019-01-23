package cn.com.sunzhiqiang.algorithm.bfs;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import cn.com.sunzhiqiang.data.structure.queue.MyQueue;

import java.util.List;

/**
 * 功能描述: 广度优先遍历
 *
 * @author sunzhiqiang
 * @create 2019-01-23
 */
public class BreadthFirst {

    public void breadFirstList(int pointNum, List<Side> sides, int startPoint) {

        int[][] matrix = initMatrix(pointNum, sides);

        int[] book = new int[pointNum];
        for (int i = 0; i < pointNum; i++) {
            book[i] = 0;
        }
        book[startPoint] = 1;

        MyQueue<Integer> queue = new MyQueue<>(pointNum);
        queue.offer(startPoint);
        System.out.println(String.format("顶点%d被访问", startPoint));

        while (!queue.isEmpty()) {
            int head = queue.pop();
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[head][i] == 1 && book[i] == 0) {
                    queue.offer(i);
                    book[i] = 1;
                    System.out.println(String.format("订点%d被访问", i));
                }
            }

            if (queue.isFull()) {
                break;
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
