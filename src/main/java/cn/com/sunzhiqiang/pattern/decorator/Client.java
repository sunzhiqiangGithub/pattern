package cn.com.sunzhiqiang.pattern.decorator;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Programmer programmer = new ServerProgrammer();
        programmer.program();

        System.out.println("===================================");

        programmer = new OperationDecorator(programmer);
        programmer.program();
    }
}
