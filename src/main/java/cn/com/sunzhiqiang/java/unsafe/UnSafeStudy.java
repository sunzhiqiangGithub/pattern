package cn.com.sunzhiqiang.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 功能描述: UnSafe类学习
 *
 * @author sunzhiqiang
 * @create 2018-09-05
 */
public class UnSafeStudy {

    private int b = 2;

    class InnerClass {
        private int a = 2;
    }

    public static void main(String[] args) throws Exception {

        UnSafeStudy unSafeStudy = new UnSafeStudy();

        // Unsafe对象的创建
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);

        InnerClass innerClass = new UnSafeStudy().new InnerClass();
        int a = unsafe.getInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")));
        System.out.println(a);

        unsafe.putInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")), 3);
        System.out.println(innerClass.a);

//        int b = unsafe.getInt(unsafe.getAddress(unSafeStudy.b));
//        System.out.println(b);
    }
}
