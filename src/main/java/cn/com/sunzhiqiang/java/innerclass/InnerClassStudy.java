package cn.com.sunzhiqiang.java.innerclass;

import org.junit.jupiter.api.Test;

/**
 * 功能描述: 内部类学习
 *
 * @author sunzhiqiang
 * @create 2018-11-04
 */
public class InnerClassStudy {

    private static int a;
    private int b;

    // 成员内部类
    class InnerClass {
        void method() {
            System.out.println(a);//成员内部类可以访问外部类的静态成员
            System.out.println(b); //成员内部类可以访问外部类的实例成员
        }
    }

    // 静态内部类
    static class StaticInnerClass {
        void method() {
            System.out.println(a);//静态内部类只能访问外部类的静态成员
        }
    }

    public void method() {
        int c = 1;
        final int d = 2;
        // 局部内部类
        class LocalInnerClass {
            void method() {
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(d);
            }
        }

        new Thread(new Runnable() { //匿名内部类
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(d);
            }
        });
    }

    @Test
    public void testCreateInnerClassInstance() {

        // 成员内部类持有外部类的引用，必须先实例化外部类才能实例化内部类
        InnerClass innerClass = new InnerClassStudy().new InnerClass();

        // 静态内部类不持有外部类的引用，所以可以直接new创建
        StaticInnerClass staticInnerClass = new InnerClassStudy.StaticInnerClass();

        // 局部内部类只能在方法中创建,基本上不用
        class LocalInnerClass {
        }
        new LocalInnerClass();

        // 匿名内部类常用在接口回调中,下面创建了一个实现了Runnable接口的类的对象
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
