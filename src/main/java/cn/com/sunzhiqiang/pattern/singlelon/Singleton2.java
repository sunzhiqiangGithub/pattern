package cn.com.sunzhiqiang.pattern.singlelon;

/**
 * 功能描述: 单例模式(懒汉式)
 *
 * @author sunzhiqiang
 * @create 2018-08-05
 */
public final class Singleton2 {

    private static Singleton2 INSTANCE = null;

    private Singleton2(){
    }

    public static Singleton2 getInstance(){
        if(INSTANCE == null){
            synchronized (Singleton2.class){
                if(INSTANCE == null){
                    INSTANCE = new Singleton2();
                }
            }
        }

        return INSTANCE;
    }
}
