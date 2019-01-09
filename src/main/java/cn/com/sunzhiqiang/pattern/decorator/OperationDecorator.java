package cn.com.sunzhiqiang.pattern.decorator;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 装饰者（运维能力）
 */
public class OperationDecorator implements Programmer {

    private Programmer programmer;

    public OperationDecorator(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void program() {

        programmer.program();

        System.out.println("自动化部署程序");
    }
}
