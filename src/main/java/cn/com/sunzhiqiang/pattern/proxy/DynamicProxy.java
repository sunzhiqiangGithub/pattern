package cn.com.sunzhiqiang.pattern.proxy;

import sun.misc.ProxyGenerator;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

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

        //Properties properties = new Properties();
        //properties.put("sun.misc.ProxyGenerator.saveGeneratedFiles",true);
        //System.setProperties(properties);

        //System.out.println(Boolean.parseBoolean(System.getProperty("sun.misc.ProxyGenerator.saveGeneratedFiles")));
        //byte[] proxyClassByteArray = ProxyGenerator.generateProxyClass("$Proxy1",new Class<?>[]{Phone.class});
        //BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream("D://app/pattern/Proxy2.class"));
        //buffer.write(proxyClassByteArray);
        //buffer.close();

        DynamicProxy handle = new DynamicProxy(new Boss());
        Phone phone = (Phone) Proxy.newProxyInstance(Phone.class.getClassLoader(), new Class[]{Phone.class}, handle);
        phone.handlePhone();

        //ProxyGenerator
    }
}
