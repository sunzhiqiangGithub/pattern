package cn.com.sunzhiqiang.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;

/**
 * 功能描述: UnSafe类学习
 *
 * @author sunzhiqiang
 * @create 2018-09-05
 */
public class UnsafeStudy {

    private int b = 2;
    private static int c = 3;
    private long e = 1;

    class InnerClass {
        private int a = 2;
        private int d = 4;
    }

    public static void main(String[] args) throws Exception {

        Unsafe unsafe = getUnsafe();

        testObjectField(unsafe);

        testAddress(unsafe);

        testMemory(unsafe);

        testStaticField(unsafe);

        testCreateInstance(unsafe);

        testInitialized(unsafe);

        testArray(unsafe);

        testOther(unsafe);

        testMonitor(unsafe);

        testException(unsafe);

        testCAS(unsafe);

        testVolatilePutAndGet(unsafe);

        testPark(unsafe);

        testAtomicUpdate(unsafe);
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
        long value = unsafe.getAddress(memoryAddress);
        System.out.println("getAddress方法获取的值为:" + value);
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
        System.out.println("通过getInt获取的对象的实例变量值:" + a);
        unsafe.putInt(innerClass, unsafe.objectFieldOffset(InnerClass.class.getDeclaredField("a")), 3);
        System.out.println("通过putInt重新设置值后:" + innerClass.a);
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
        return unsafe;
    }

    /**
     * Unsafe类分配内存,设置值,重新分配内存,复制内存释放内存
     *
     * @param unsafe
     * @throws InterruptedException
     */
    private static void testMemory(Unsafe unsafe) throws InterruptedException {
        long memoryAddress = unsafe.allocateMemory(1);
        System.out.println("分配的内存地址为:" + memoryAddress);
        unsafe.setMemory(memoryAddress, 1, (byte) 1);
        System.out.println("设置内存里的数据为:" + unsafe.getByte(memoryAddress));
        long secondAddress = unsafe.reallocateMemory(memoryAddress, 1);
        System.out.println("重新分配的内存地址为:" + secondAddress);
        unsafe.setMemory(secondAddress, 1, (byte) 2);
        System.out.println("设置内存里的数据为:" + unsafe.getByte(secondAddress));

        System.out.println("测试内存复制");
        long oneAddress = unsafe.allocateMemory(1);
        long twoAddress = unsafe.allocateMemory(1);
        unsafe.setMemory(oneAddress, 1, (byte) 1);
        unsafe.setMemory(twoAddress, 1, (byte) 2);
        System.out.println("被复制的值:" + unsafe.getByte(oneAddress));
        System.out.println("复制前:" + unsafe.getByte(twoAddress));
        unsafe.copyMemory(oneAddress, twoAddress, 1);
        System.out.println("复制后:" + unsafe.getByte(twoAddress));

        unsafe.freeMemory(memoryAddress);
        unsafe.freeMemory(secondAddress);
        unsafe.freeMemory(oneAddress);
        unsafe.freeMemory(twoAddress);
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
        UnsafeStudy unsafeStudy1 = new UnsafeStudy();
        System.out.println("通过new关键字创建的对象的实例变量:" + unsafeStudy1.b);
        System.out.println("通过new关键字创建的对象的静态变量:" + unsafeStudy1.c);
        System.out.println("通过allocateInstance创建的对象的实例变量:" + unsafeStudy.b);
        System.out.println("通过allocateInstance创建的对象的静态变量:" + unsafeStudy.c);
    }

    /**
     * 测试initialized相关
     *
     * @param unsafe
     * @throws InstantiationException
     */
    private static void testInitialized(Unsafe unsafe) throws InstantiationException {
        System.out.println(unsafe.shouldBeInitialized(String.class));
        System.out.println(unsafe.shouldBeInitialized(UnsafeStudy.class));
        unsafe.ensureClassInitialized(UnsafeStudy.class);
        UnsafeStudy unsafeStudy = UnsafeStudy.class.cast(unsafe.allocateInstance(UnsafeStudy.class));
        System.out.println(unsafeStudy.b);
    }

    /**
     * 测试操作数组
     *
     * @param unsafe
     */
    private static void testArray(Unsafe unsafe) {
        int[] intArray = new int[]{1, 2, 3, 4, 5};
        int base = unsafe.arrayBaseOffset(int[].class);
        int offset = unsafe.arrayIndexScale(int[].class);
        System.out.println("数组中第一个元素的值:" + unsafe.getInt(intArray, base));
        System.out.println("数组中第二个元素的值:" + unsafe.getInt(intArray, base + offset));
        unsafe.putInt(intArray, base + offset, 8);
        System.out.println("重新设置数组中第二个元素的值:" + unsafe.getInt(intArray, base + offset));
    }

    /**
     * 其他
     *
     * @param unsafe
     */
    private static void testOther(Unsafe unsafe) {
        System.out.println(unsafe.addressSize());
        System.out.println(unsafe.pageSize());
    }

    /**
     * Unsafe实现锁相关操作
     *
     * @param unsafe
     * @throws InterruptedException
     */
    private static void testMonitor(Unsafe unsafe) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> testLock(unsafe));
        }
        unsafe.tryMonitorEnter(UnsafeStudy.class);
        System.out.println("主线程获取到锁");
        UnsafeStudy.c++;
        Thread.sleep(1000);
        System.out.println("等待结束");
        unsafe.monitorExit(UnsafeStudy.class);
    }

    private static void testLock(Unsafe unsafe) {
        unsafe.monitorEnter(UnsafeStudy.class);
        System.out.println("当前线程为:" + Thread.currentThread().getName());
        System.out.println("测试monitor,i = " + UnsafeStudy.c++);
        unsafe.monitorExit(UnsafeStudy.class);
    }

    /**
     * 异常相关
     *
     * @param unsafe
     */
    private static void testException(Unsafe unsafe) {
        unsafe.throwException(new Exception("异常测试"));
    }

    /**
     * CAS相关
     *
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private static void testCAS(Unsafe unsafe) throws NoSuchFieldException {
        UnsafeStudy unsafeStudy = new UnsafeStudy();
        long offset = unsafe.objectFieldOffset(UnsafeStudy.class.getDeclaredField("b"));
        System.out.println(unsafe.compareAndSwapInt(unsafeStudy, offset, 2, 3));
        System.out.println(unsafe.compareAndSwapInt(unsafeStudy, offset, 2, 3));
    }

    /**
     * 测试支持volatile语意的设置和获取
     *
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private static void testVolatilePutAndGet(Unsafe unsafe) throws NoSuchFieldException, BrokenBarrierException, InterruptedException {
        UnsafeStudy unsafeStudy = new UnsafeStudy();
        long offset = unsafe.objectFieldOffset(UnsafeStudy.class.getDeclaredField("e"));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        System.out.println("第一轮执行getLong测试开始");
        executorService.submit(() -> {
            unsafe.putLong(unsafeStudy, offset, 3);
            System.out.println(Thread.currentThread().getName() + "设置值为:" + unsafeStudy.e);
            cyclicBarrier.await();
            return null;
        });
        executorService.submit(() -> {
            long value = unsafe.getLong(unsafeStudy, offset);
            System.out.println(Thread.currentThread().getName() + "获取值为:" + value);
            cyclicBarrier.await();
            return null;
        });
        cyclicBarrier.await();
        System.out.println("第一轮测试结束");

        cyclicBarrier.reset();
        System.out.println("第二轮执行getLongVolatile测试开始");
        executorService.submit(() -> {
            unsafe.putLongVolatile(unsafeStudy, offset, 4);
            System.out.println(Thread.currentThread().getName() + "设置值为:" + unsafeStudy.e);
            cyclicBarrier.await();
            return null;
        });
        executorService.submit(() -> {
            long value = unsafe.getLongVolatile(unsafeStudy, offset);
            System.out.println(Thread.currentThread().getName() + "获取值为:" + value);
            cyclicBarrier.await();
            return null;
        });
        cyclicBarrier.await();
        System.out.println("第二轮测试结束");

        cyclicBarrier.reset();
        System.out.println("第三轮执行putOrderLong测试开始");
        executorService.submit(() -> {
            unsafe.putOrderedLong(unsafeStudy, offset, 5);
            System.out.println(Thread.currentThread().getName() + "设置值为:" + unsafeStudy.e);
            cyclicBarrier.await();
            return null;
        });
        executorService.submit(() -> {
            long value = unsafe.getLong(unsafeStudy, offset);
            System.out.println(Thread.currentThread().getName() + "获取值为:" + value);
            cyclicBarrier.await();
            return null;
        });
        cyclicBarrier.await();
        System.out.println("第三轮测试结束");
    }

    /**
     * 测试park和unpark
     *
     * @param unsafe
     */
    private static void testPark(Unsafe unsafe) throws ExecutionException, InterruptedException {
        Thread mainThread = Thread.currentThread();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Thread> threadFuture = executor.submit(() -> {
            System.out.println("5s后恢复主线程" + mainThread);
            Thread.sleep(5000);
            unsafe.unpark(mainThread);
            return Thread.currentThread();
        });
        System.out.println("挂起主线程:" + mainThread);
        unsafe.park(false, 0L);
        System.out.println("主线程被恢复" + mainThread);
    }

    /**
     * 原子的增加一个值和设置一个值
     *
     * @param unsafe
     * @throws NoSuchFieldException
     */
    private static void testAtomicUpdate(Unsafe unsafe) throws NoSuchFieldException {
        UnsafeStudy unsafeStudy = new UnsafeStudy();
        long offset = unsafe.objectFieldOffset(UnsafeStudy.class.getDeclaredField("b"));
        System.out.println(unsafe.getAndAddInt(unsafeStudy, offset, 3));
        System.out.println(unsafe.getAndSetInt(unsafeStudy, offset, 1));
    }
}
