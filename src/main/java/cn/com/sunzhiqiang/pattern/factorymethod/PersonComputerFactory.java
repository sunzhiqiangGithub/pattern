package cn.com.sunzhiqiang.pattern.factorymethod;

/**
 * 功能描述: 具体工厂类
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class PersonComputerFactory implements ComputerFactory{

    @Override
    public Computer getComputer() {

        return new PersonComputer();
    }
}
