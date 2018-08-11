package cn.com.sunzhiqiang.pattern.adapter;

/**
 * 功能描述: 适配器模式
 * 适配器
 *
 * @author sunzhiqiang
 * @create 2018-08-11
 */
public class ElectricAdapter implements DirectCurrent{

    private AlternatingCurrent ac;

    public ElectricAdapter(AlternatingCurrent ac){
        this.ac = ac;
    }

    public byte[] getDC() {

        byte[] acCurrent = ac.getAC();

        byte[] dcCurrent = new byte[acCurrent.length];
        for (int i = 0; i < acCurrent.length; i++) {
            dcCurrent[i] = (byte) Math.abs(acCurrent[i]);
        }

        return dcCurrent;
    }
}


