package cn.com.sunzhiqiang.pattern.command;

/**
 * 功能描述: 命令模式-请求发起人
 *
 * @author sunzhiqiang
 * @create 2018-11-11
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public String invoke() {

        return command.execute();
    }
}
