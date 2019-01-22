package cn.com.sunzhiqiang.algorithm.dfs;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-22
 */
public class Client {

    public static void main(String[] args) {

        final int pointNum = 5;

        List<Side> sides = Lists.newArrayListWithExpectedSize(pointNum);
        sides.add(new Side(0, 1));
        sides.add(new Side(0, 2));
        sides.add(new Side(0, 4));
        sides.add(new Side(1, 3));
        sides.add(new Side(2, 4));

        DepthFirst depthFirst = new DepthFirst();
        depthFirst.depthFirstList(pointNum, sides, 0);
    }
}
