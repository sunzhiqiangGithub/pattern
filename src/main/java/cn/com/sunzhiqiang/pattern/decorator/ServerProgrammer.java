package cn.com.sunzhiqiang.pattern.decorator;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 具体元素（服务端程序员）
 */
public class ServerProgrammer implements Programmer {

    @Override
    public void program() {

        System.out.println("服务端编程");
    }
}
