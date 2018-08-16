package cn.com.sunzhiqiang.java.lambda;

import java.util.function.Function;

public class LambdaStudy {

    public static void main(String[] args) {

        method1(s -> {
            return Integer.parseInt(s);
        });

        method2(s -> {
            return Integer.parseInt(s);
        });
    }

    private static void method1(Function<String,Integer> function){

        System.out.println(function.apply("20"));
    }

    private static void method2(MyFunction<String,Integer> function){

        System.out.println(function.getValue("15"));
    }

    private interface MyFunction<K,V>{

        V getValue(K k);
    }
}
