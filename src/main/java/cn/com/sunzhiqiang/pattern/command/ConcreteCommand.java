package cn.com.sunzhiqiang.pattern.command;

/**
 * 功能描述: 命令模式-具体命令
 *
 * @author sunzhiqiang
 * @create 2018-11-11
 */
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute() {

        return receiver.doCommand();
    }
}
