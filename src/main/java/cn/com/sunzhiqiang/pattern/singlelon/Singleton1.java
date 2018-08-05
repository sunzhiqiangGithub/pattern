package cn.com.sunzhiqiang.pattern.singlelon;

/**
 * 功能描述: 单例模式(饿汉式)
 *
 * @author sunzhiqiang
 * @create 2018-08-05
 */
public final class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){
    }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
