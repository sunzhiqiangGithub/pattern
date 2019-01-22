package cn.com.sunzhiqiang.algorithm.shortest_path;

import lombok.Data;

/**
 * 功能描述: 边
 *
 * @author sunzhiqiang
 * @create 2019-01-20
 */
@Data
public class Side {

    /**
     * 起点
     */
    private int startPoint;

    /**
     * 终点
     */
    private int endPoint;

    /**
     * 权重
     */
    private int weights;

    public Side(int startPoint, int endPoint, int weights) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.weights = weights;
    }

    public Side(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
