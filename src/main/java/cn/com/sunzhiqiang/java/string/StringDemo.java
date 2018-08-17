package cn.com.sunzhiqiang.java.string;

import org.junit.jupiter.api.Test;

/**
 * 功能描述: String类使用
 *
 * @author sunzhiqiang
 * @create 2018-08-17
 */
public class StringDemo {

    @Test
    public void test1(){

        String s1 = "aaa";
        String s2 = "aaa";
        String s3 = new String("aaa");
        String s4 = new String("aaa");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s3 == s4); // false
    }

    @Test
    public void test2(){

        String s1 = "aaa";
        String s2 = new String("aaa");
        String s3 = s2.intern();

        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
        System.out.println(s2 == s3); // false
    }
}
