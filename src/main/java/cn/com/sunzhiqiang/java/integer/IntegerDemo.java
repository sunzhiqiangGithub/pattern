package cn.com.sunzhiqiang.java.integer;

import org.junit.jupiter.api.Test;

/**
 * 功能描述: Integer使用
 *
 * @author sunzhiqiang
 * @create 2018-08-17
 */
public class IntegerDemo {

    @Test
    public void test1(){

        Integer a = 1;
        Integer b = 1;
        Integer c = 128;
        Integer d = 128;

        Integer e = new Integer(1);

        Integer f = -1;
        Integer g = -1;
        Integer h = -128;
        Integer i = -128;

        Integer k = Integer.valueOf(1);

        System.out.println(a == b); // true
        System.out.println(c == d); // false
        System.out.println(a == e); // false
        System.out.println(f == g); // true
        System.out.println(h == i); // true
        System.out.println(a == k); // true
    }
}
