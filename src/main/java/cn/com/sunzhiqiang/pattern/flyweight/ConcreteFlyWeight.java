package cn.com.sunzhiqiang.pattern.flyweight;

/**
 * 功能描述: 享元模式
 * 具体享元
 *
 * @author sunzhiqiang
 * @create 2018-08-15
 */
class ConcreteFlyWeight implements FlyWeight {

    private String intrinsicState;

    public ConcreteFlyWeight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void opration(String extrinsicState) {

        System.out.println(intrinsicState + extrinsicState);
    }
}
