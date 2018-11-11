package cn.com.sunzhiqiang.pattern.command;

/**
 * 功能描述: 命令模式-客户端
 *
 * @author sunzhiqiang
 * @create 2018-11-11
 */
public class Client {

    public static void main(String[] args) {

        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker(command);

        String invokeResult = invoker.invoke();
        System.out.println(invokeResult);
    }
}
