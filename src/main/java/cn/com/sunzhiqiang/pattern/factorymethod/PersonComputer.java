package cn.com.sunzhiqiang.pattern.factorymethod;

/**
 * 功能描述: 具体产品
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class PersonComputer implements Computer {

    @Override
    public void run() {

        System.out.println("个人电脑正在运行...");
    }
}
