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

    abstract void preprocessImpl();

    abstract void processImpl();

    abstract void postprocessImpl();
}

class TemplateImpl extends TemplatePattern {

    void preprocessImpl() {
        System.out.println("前置处理");
    }

    void processImpl() {
        System.out.println("处理中");
    }

    void postprocessImpl() {
        System.out.println("后置处理");
    }
}
