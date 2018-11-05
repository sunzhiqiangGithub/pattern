package cn.com.sunzhiqiang.java.innerclass;

import org.junit.jupiter.api.Test;

/**
 * 功能描述: 内部类学习
 *
 * @author sunzhiqiang
 * @create 2018-11-04
 */
public class InnerClassStudy {

    // 成员内部类
    class InnerClass {

    }

    // 静态内部类
    static class StaticInnerClass {

    }

    public void method(){
        // 局部内部类
        class LocalInnerClass{

        }

        new Thread(new Runnable() { //匿名内部类
            @Override
            public void run() {
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
        class LocalInnerClass{
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
