package cn.com.sunzhiqiang.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述: 代理模式
 *
 * @author sunzhiqiang
 * @create 2018-08-09
 */
public class Boss implements Phone {

    public void handlePhone() {
        System.out.println("老板接电话");
    }

    public static void main(String[] args) {

        final Phone boss = new Boss();

        Phone proxy = (Phone) Proxy.newProxyInstance(Phone.class.getClassLoader(), new Class[]{Phone.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理");
                Object obj = method.invoke(boss, args);
                return obj;
            }
        });

        proxy.handlePhone();
    }
}
