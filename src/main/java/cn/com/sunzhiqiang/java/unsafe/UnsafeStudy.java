package cn.com.sunzhiqiang.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 功能描述: UnSafe类学习
 *
 * @author sunzhiqiang
 * @create 2018-09-05
 */
public class UnsafeStudy {

    private int b = 2;

    class InnerClass {
        private int a = 2;
    }

    public static void main(String[] args) throws Exception {

        UnsafeStudy unsafeStudy = new UnsafeStudy();

        // Unsafe对象的创建
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);

        // getInt(Object var1, long var2)
        InnerClass innerClass = new UnsafeStudy().new InnerClass();
        int a = unsafe.getInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")));
        System.out.println(a);

        // putInt(Object var1, long var2, int var4)
        unsafe.putInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")), 3);
        System.out.println(innerClass.a);

        // getInt(long var1) TODO 待研究如何使用
//        int b = unsafe.getInt(unsafeStudy.b);
//        System.out.println(b);

        // getAddress(long var1) TODO 待研究如何使用
        long address = unsafe.getAddress(unsafe.allocateMemory(1000));
        System.out.println(address);

        // allocateMemory(long var1) TODO ?
        System.out.println(unsafe.allocateMemory(1000));

        // reallocateMemory(long var1, long var3)
        System.out.println(unsafe.reallocateMemory(unsafe.allocateMemory(1000),2000));

        //
    }
}
