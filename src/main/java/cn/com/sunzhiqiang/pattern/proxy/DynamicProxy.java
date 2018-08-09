package cn.com.sunzhiqiang.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能描述: 动态代理
 *
 * @author sunzhiqiang
 * @create 2018-08-09
 */
public class DynamicProxy implements InvocationHandler {

    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("动态代理");
        return method.invoke(obj, args);
    }

    public static void main(String[] args) throws Exception {

        DynamicProxy handle = new DynamicProxy(new Boss());

        Phone phone = (Phone) Proxy.newProxyInstance(Phone.class.getClassLoader(), new Class[]{Phone.class}, handle);

        phone.handlePhone();
    }
}
