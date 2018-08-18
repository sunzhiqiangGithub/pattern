package cn.com.sunzhiqiang.pattern.abstractfactory;

/**
 * 功能描述: 具体工厂
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class IOSPhoneProductFactory implements PhoneProductFactory{

    @Override
    public Phone getPhone() {

        return new IOSPhone();
    }

    @Override
    public Charger getCharger() {

        return new IOSCharger();
    }
}
