package cn.com.sunzhiqiang.java.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LambdaStudy2 {

    @Test
    public void testLambda1() throws Exception {

        Thread thread = new Thread(() -> {

            try {
                Thread.sleep(1000);
                System.out.println("lambda表达式实现runnable接口");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        System.out.println("主线程开始");
        thread.join();
        System.out.println("主线程结束");
    }

    @Test
    public void testLambda2(){

        List<Integer> list = Arrays.asList(1,2,3,4,5);

        list.forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void testLambda3(){

        method(p -> {
            return p;
        });
    }

    private interface MyFunction<R,P>{

        R getRusult(P p);
    }

    private <R,P> void method(MyFunction<R,P> function){

        String result = "result";
        System.out.println(function.getRusult((P) result));
    }
}
