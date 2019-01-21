package cn.com.sunzhiqiang.algorithm.shortest_path.bellman_ford;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-21
 */
public class Client {

    public static void main(String[] args) {

        final int pointNum = 5;
        final int source = 0;

        List<Side> sides = Lists.newArrayListWithExpectedSize(5);
        sides.add(new Side(1, 2, 2));
        sides.add(new Side(0, 1, -3));
        sides.add(new Side(0, 4, 5));
        sides.add(new Side(3, 4, 2));
        sides.add(new Side(2, 3, 3));

        Result result = new BellmanFord().getShortestPath(pointNum, source, sides);
        System.out.println(Arrays.toString(result.getDis()));
        Map<String, List<Integer>> shortestPathMap = result.getIntermediatePointListMap();
        Set<Map.Entry<String, List<Integer>>> entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }

        System.out.println("==================================================");

        result = new BellmanFord().getShortestPathByQueue(pointNum, source, sides);
        System.out.println(Arrays.toString(result.getDis()));
        shortestPathMap = result.getIntermediatePointListMap();
        entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }
}
