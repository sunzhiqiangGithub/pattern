package cn.com.sunzhiqiang.java.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 功能描述: 4种引用类型
 *
 * @author sunzhiqiang
 * @create 2018-08-11
 */
public class Test {

    public static void main(String[] args){

        //1.强引用
        Test test = new Test();

        //2. 软应用
        SoftReference<Test> test1 = new SoftReference<Test>(new Test());

        //3. 弱引用
        WeakReference<Test> test2 = new WeakReference<Test>(new Test());

        //4. 虚引用
        PhantomReference<Test> test3 = new PhantomReference<Test>(new Test(),new ReferenceQueue<Test>());
    }
}
