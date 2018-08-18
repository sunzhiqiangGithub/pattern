package cn.com.sunzhiqiang.pattern.abstractfactory;

/**
 * 功能描述: 具体产品
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class IOSPhone implements Phone {

    @Override
    public void phoneMessage() {

        System.out.println("这是苹果手机.");
    }
}
