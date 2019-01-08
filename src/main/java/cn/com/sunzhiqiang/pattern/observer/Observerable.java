package cn.com.sunzhiqiang.pattern.observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 被观察者接口
 */
interface Observerable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
