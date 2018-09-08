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

    private static int c = 3;

    class InnerClass {
        private int a = 2;
        private int b = 4;
    }

    public static void main(String[] args) throws Exception {

        Unsafe unsafe = getUnsafe();

        //testObjectField(unsafe);

        //testAddress(unsafe);

        //testMemory(unsafe);

        testStaticField(unsafe);

    }

    /**
     * 测试操作address相关
     *
     * @param unsafe
     */
    private static void testAddress(Unsafe unsafe) {
        long memoryAddress = unsafe.allocateMemory(1);
        System.out.println("分配的内存地址为:" + memoryAddress);
        unsafe.putAddress(memoryAddress, 2);
        long address = unsafe.getAddress(memoryAddress);
        System.out.println("getAddress方法获取的值为:" + address);
        System.out.println("getByte方法获取的值:" + unsafe.getByte(memoryAddress));
    }

    /**
     * 测试获取对象的值和设置对象的值
     *
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private static void testObjectField(Unsafe unsafe) throws NoSuchFieldException {
        InnerClass innerClass = new UnsafeStudy().new InnerClass();
        int a = unsafe.getInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")));
        System.out.println(a);
        unsafe.putInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")), 3);
        System.out.println(innerClass.a);
    }

    /**
     * Unsafe类创建对象
     *
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        // Unsafe对象的创建
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
        return unsafe;
    }

    /**
     * Unsafe类分配内存,设置值,重新分配内存,复制内存释放内存
     *
     * @param unsafe
     * @throws InterruptedException
     */
    private static void testMemory(Unsafe unsafe) throws InterruptedException {
        System.out.println("===========内存分配,设置值,释放内存");
        long memoryAddress = unsafe.allocateMemory(1);
        System.out.println("==========第一次的内存地址为:" + memoryAddress);
        unsafe.setMemory(memoryAddress, 1, (byte) 1);
        System.out.println("==========第一次内存地址里的数据为:" + unsafe.getByte(memoryAddress));
        long secondAddress = unsafe.reallocateMemory(memoryAddress, 1);
        System.out.println("==========第二次的内存地址为:" + secondAddress);
        unsafe.setMemory(secondAddress, 1, (byte) 2);
        System.out.println("==========第二次内存地址里的数据为:" + unsafe.getByte(secondAddress));
        unsafe.freeMemory(memoryAddress);
        Thread.sleep(1000);
        unsafe.freeMemory(secondAddress);
        Thread.sleep(1000);
        System.out.println(unsafe.getByte(secondAddress));
        System.out.println("=========测试内存复制");
        long oneAddress = unsafe.allocateMemory(1);
        long twoAddress = unsafe.allocateMemory(1);
        unsafe.setMemory(oneAddress, 1, (byte) 1);
        System.out.println("被复制的值:" + unsafe.getByte(oneAddress));
        System.out.println("复制前:" + unsafe.getByte(twoAddress));
        unsafe.copyMemory(oneAddress, twoAddress, 1);
        System.out.println("复制后:" + unsafe.getByte(twoAddress));
    }

    /**
     * 获取和设置静态字段的值
     *
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private static void testStaticField(Unsafe unsafe) throws NoSuchFieldException {
        Object obj = unsafe.staticFieldBase(UnsafeStudy.class.getDeclaredField("c"));
        long staticFieldOffset = unsafe.staticFieldOffset(UnsafeStudy.class.getDeclaredField("c"));
        long staticFieldValue = unsafe.getInt(obj, staticFieldOffset);
        System.out.println(staticFieldValue);
    }

    /**
     * 使用Unsafe类创建对象
     *
     * @param unsafe
     * @throws InstantiationException
     */
    private static void testCreateInstance(Unsafe unsafe) throws InstantiationException {
        UnsafeStudy unsafeStudy = UnsafeStudy.class.cast(unsafe.allocateInstance(UnsafeStudy.class));
        System.out.println(unsafeStudy.b);
    }
}
