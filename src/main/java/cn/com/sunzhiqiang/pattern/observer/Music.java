package cn.com.sunzhiqiang.pattern.observer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体被观察者
 */
class Music implements Observerable {

    List<Observer> observers = Lists.newArrayList();

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public void addObserver(Observer observer) {

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void start(String name) {

        this.name = name;
        notifyObservers();
    }
}
