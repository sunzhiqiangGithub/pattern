package cn.com.sunzhiqiang.pattern.singlelon;

/**
 * 功能描述: 单例模式(静态内部类)
 *
 * @author sunzhiqiang
 * @create 2018-08-05
 */
public class Singleton3 {

    private Singleton3(){
    }

    public static Singleton3 getInstance(){
        return Singleton3Holder.INSTANCE;
    }

    private static class Singleton3Holder{
        private static final Singleton3 INSTANCE = new Singleton3();
    }
}
