package cn.com.sunzhiqiang.concurrent.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述: 原子类型Integer学习
 *
 * @author sunzhiqiang
 * @create 2018-09-02
 */
public class AtimicIntegerStudy {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void testGet(){
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testSet(){
        atomicInteger.set(2);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testLazySet(){
        atomicInteger.lazySet(3);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testGetAndUpdate(){
        atomicInteger.getAndUpdate(s -> {
            return s + 2;
        });
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testGetAndAccumulate(){
        atomicInteger.getAndAccumulate(2,(s,x) -> {
           return s + x;
        });
        System.out.println(atomicInteger.get());
    }
}
