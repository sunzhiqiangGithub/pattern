package cn.com.sunzhiqiang.pattern.decorator;

/**
 * @author sunzhiqiang
 * @date 2019/1/9
 * @desc 装饰者（运维能力）
 */
public class OperationDecorator implements Programmer {

    @Override
    public void program() {

        System.out.println("服务端编程");

        operation();
    }

    private void operation() {

        System.out.println("部署程序");
    }
}
