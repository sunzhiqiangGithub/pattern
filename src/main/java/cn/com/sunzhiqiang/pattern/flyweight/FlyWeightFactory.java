package cn.com.sunzhiqiang.pattern.flyweight;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述: 享元模式
 * 享元工厂
 *
 * @author sunzhiqiang
 * @create 2018-08-15
 */
public final class FlyWeightFactory {

    private static final FlyWeightFactory FACTORY = new FlyWeightFactory();

    private final ConcurrentHashMap<String,SoftReference<FlyWeight>> cache = new ConcurrentHashMap<>();

    private FlyWeightFactory() {

    }

    public static FlyWeightFactory factory() {
        return FACTORY;
    }

    public FlyWeight getFlyWeight(String key){

        if(cache.get(key) != null){
            return cache.get(key).get();

        }else{
            FlyWeight flyWeight = new ConcreteFlyWeight(key);
            cache.putIfAbsent(key,new SoftReference<>(flyWeight));
            return flyWeight;
        }
    }
}
