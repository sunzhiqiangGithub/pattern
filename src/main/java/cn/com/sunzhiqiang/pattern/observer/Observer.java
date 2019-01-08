package cn.com.sunzhiqiang.pattern.observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 观察者接口
 */
interface Observer {

    void update(Observerable observerable);
}
