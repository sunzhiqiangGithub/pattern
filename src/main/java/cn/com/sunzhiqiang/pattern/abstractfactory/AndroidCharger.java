package cn.com.sunzhiqiang.pattern.abstractfactory;

/**
 * 功能描述: 具体产品
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class AndroidCharger implements Charger {

    @Override
    public void chargerMessage() {

        System.out.println("这是安卓手机充电器.");
    }
}
