package cn.com.sunzhiqiang.pattern.adapter;

/**
 * 功能描述: 适配器模式
 *
 * @author sunzhiqiang
 * @create 2018-08-11
 */
public class ElectricCompary implements AlternatingCurrent {

    public byte[] getAC() {

        return new byte[]{1,-1,3,-3,5,-2,6,-1,3,-2};
    }
}
