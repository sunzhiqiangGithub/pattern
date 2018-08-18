package cn.com.sunzhiqiang.pattern.factorymethod;

/**
 * 功能描述: 具体产品
 *
 * @author sunzhiqiang
 * @create 2018-08-18
 */
public class Server implements Computer {

    @Override
    public void run() {

        System.out.println("服务器正在运行...");
    }
}
