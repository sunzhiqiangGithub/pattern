package cn.com.sunzhiqiang.pattern.template;

/**
 * 功能描述: 模板方法模式
 *
 * @author sunzhiqiang
 * @create 2018-08-06
 */
public abstract class TemplatePattern2 {

    public void process() {
        preprocessImpl();
        processImpl();
        postprocessImpl();
    }

    void preprocessImpl(){

    }

    abstract void processImpl();

    void postprocessImpl(){

    }
}

class TemplateImpl2 extends TemplatePattern2 {

    void processImpl() {

    }
}
