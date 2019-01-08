package cn.com.sunzhiqiang.pattern.observer.jdk;

import java.util.Observable;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体被观察者
 */
class Music extends Observable {

    private String name;

    public String getName() {
        return name;
    }

    public void start(String name) {

        this.name = name;

        setChanged();
        notifyObservers();
    }
}
