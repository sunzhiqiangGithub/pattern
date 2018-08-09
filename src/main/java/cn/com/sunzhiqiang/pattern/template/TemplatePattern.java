package cn.com.sunzhiqiang.pattern.template;

/**
 * 功能描述: 模板方法模式
 *
 * @author sunzhiqiang
 * @create 2018-08-06
 */
public abstract class TemplatePattern {

    public void process() {
        preprocessImpl();
        processImpl();
        postprocessImpl();
    }

    public abstract void preprocessImpl();

    public abstract void processImpl();

    public abstract void postprocessImpl();
}

class TemplateImpl extends TemplatePattern {

    public void preprocessImpl() {
        System.out.println("前置处理");
    }

    public void processImpl() {
        System.out.println("处理中");
    }

    public void postprocessImpl() {
        System.out.println("后置处理");
    }
}
