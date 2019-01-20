package cn.com.sunzhiqiang.algorithm.shortest_path.floyd_warshall;

import cn.com.sunzhiqiang.algorithm.shortest_path.Result;
import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import com.google.common.collect.Lists;

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

        int pointNum = 4;

        List<Side> sides = Lists.newArrayList();
        sides.add(new Side(0, 1, 2));
        sides.add(new Side(0, 2, 6));
        sides.add(new Side(0, 3, 4));
        sides.add(new Side(1, 2, 3));
        sides.add(new Side(2, 0, 7));
        sides.add(new Side(2, 3, 1));
        sides.add(new Side(3, 0, 5));
        sides.add(new Side(3, 2, 12));

        Result result = new FloydWarshall().getSortestPath(pointNum, sides);

        for (int i = 0; i < result.getMatrix().length; i++) {
            for (int j = 0; j < result.getMatrix().length; j++) {
                System.out.print(result.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }

        Map<String, List<Integer>> shortestPathMap = result.getIntermediatePointListMap();
        Set<Map.Entry<String, List<Integer>>> entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }
}
