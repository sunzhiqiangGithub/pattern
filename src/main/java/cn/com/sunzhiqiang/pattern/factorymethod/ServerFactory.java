package cn.com.sunzhiqiang.pattern.factorymethod;

/**
 * 功能描述: 具体工厂
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class ServerFactory implements ComputerFactory {

    @Override
    public Computer getComputer() {

        return new Server();
    }
}
