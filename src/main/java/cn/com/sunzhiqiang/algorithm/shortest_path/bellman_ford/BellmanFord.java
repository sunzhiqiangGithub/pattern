package cn.com.sunzhiqiang.algorithm.shortest_path.bellman_ford;

import cn.com.sunzhiqiang.algorithm.shortest_path.Side;
import cn.com.sunzhiqiang.data.structure.queue.MyQueue;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 功能描述: Bellman-Ford算法
 *
 * @author sunzhiqiang
 * @create 2019-01-21
 */
public class BellmanFord {

    public Result getShortestPath(int pointNum, int source, List<Side> sides) {

        Result result = new Result();

        int max = Integer.MAX_VALUE / 2;
        int[] dis = new int[pointNum];
        for (int i = 0; i < dis.length; i++) {
            if (i == source) {
                dis[i] = 0;
            } else {
                dis[i] = max;
            }
        }

        int size = sides.size();
        boolean change = false;
        for (int i = 0; i < pointNum - 1; i++) {
            change = false;

            for (int j = 0; j < size; j++) {
                Side tempSide = sides.get(j);
                if (dis[tempSide.getEndPoint()] > dis[tempSide.getStartPoint()] + tempSide.getWeights()) {
                    dis[tempSide.getEndPoint()] = dis[tempSide.getStartPoint()] + tempSide.getWeights();
                    change = true;
                    if (tempSide.getStartPoint() != source) {
                        recordIntermediatePoint(result, "" + source + tempSide.getEndPoint(), tempSide.getStartPoint());
                    }
                }
            }

            if (!change) {
                break;
            }
        }

        result.setDis(dis);

        return result;
    }

    public Result getShortestPathByQueue(int pointNum, int source, List<Side> sides) {

        // 初始化临接表
        List<List<Side>> table = Lists.newArrayListWithExpectedSize(pointNum);
        for (int i = 0; i < pointNum; i++) {
            table.add(Lists.newLinkedList());
        }
        int size = sides.size();
        for (int i = 0; i < size; i++) {
            Side tempSide = sides.get(i);
            table.get(tempSide.getStartPoint()).add(tempSide);
        }

        // 初始化dis数组
        int max = Integer.MAX_VALUE / 2;
        int[] dis = new int[pointNum];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = max;
        }
        dis[source] = 0;

        // 初始化book数组
        int[] book = new int[pointNum];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
        book[source] = 1;

        // 初始化队列
        MyQueue<Integer> myQueue = new MyQueue<>(pointNum);
        myQueue.offer(source);

        Result result = new Result();

        // 算法核心
        while (!myQueue.isEmpty()) {
            Integer head = myQueue.peek();
            List<Side> tempSides = table.get(head);
            for (Side tempSide : tempSides) {
                if (dis[tempSide.getEndPoint()] > dis[tempSide.getStartPoint()] + tempSide.getWeights()) {
                    dis[tempSide.getEndPoint()] = dis[tempSide.getStartPoint()] + tempSide.getWeights();
                    if (book[tempSide.getEndPoint()] == 0) {
                        myQueue.offer(tempSide.getEndPoint());
                        book[tempSide.getEndPoint()] = 1;
                    }
                    if (tempSide.getStartPoint() != source) {
                        recordIntermediatePoint(result, "" + source + tempSide.getEndPoint(), tempSide.getStartPoint());
                    }
                }
            }
            book[head] = 0;
            myQueue.pop();
        }

        result.setDis(dis);

        return result;
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
