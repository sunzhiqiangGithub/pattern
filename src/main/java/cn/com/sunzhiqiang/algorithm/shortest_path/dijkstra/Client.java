package cn.com.sunzhiqiang.algorithm.shortest_path.dijkstra;

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
 * @create 2019-01-20
 */
public class Client {

    public static void main(String[] args) {

        int pointNum = 6;
        int source = 0;

        List<Side> sides = Lists.newArrayList();
        sides.add(new Side(0, 1, 1));
        sides.add(new Side(0, 2, 12));
        sides.add(new Side(1, 2, 9));
        sides.add(new Side(1, 3, 3));
        sides.add(new Side(2, 4, 5));
        sides.add(new Side(3, 2, 4));
        sides.add(new Side(3, 4, 13));
        sides.add(new Side(3, 5, 15));
        sides.add(new Side(4, 5, 4));

        Result result = new Dijkstra().getShortestPath(pointNum, source, sides);
        System.out.println(Arrays.toString(result.getDis()));
        Map<String, List<Integer>> shortestPathMap = result.getIntermediatePointListMap();
        Set<Map.Entry<String, List<Integer>>> entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }

        System.out.println("=====================================");

        result = new Dijkstra().getShortestPathByTable(pointNum, source, sides);
        System.out.println(Arrays.toString(result.getDis()));
        shortestPathMap = result.getIntermediatePointListMap();
        entrys = shortestPathMap.entrySet();
        for (Map.Entry<String, List<Integer>> entry : entrys) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
        }
    }
}
