package cn.com.sunzhiqiang.pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体观察者（舞蹈家）
 */
class Dancer implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        Music music = (Music) o;
        System.out.println(String.format("舞蹈家听到音乐【%s】开始跳舞", music.getName()));
    }
}
