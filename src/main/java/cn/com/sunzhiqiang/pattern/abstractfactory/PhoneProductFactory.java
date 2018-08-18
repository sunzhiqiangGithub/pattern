package cn.com.sunzhiqiang.pattern.abstractfactory;

/**
 * 功能描述: 抽象工厂
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public interface PhoneProductFactory {

    Phone getPhone();

    Charger getCharger();
}
