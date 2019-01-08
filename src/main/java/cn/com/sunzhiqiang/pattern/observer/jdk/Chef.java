package cn.com.sunzhiqiang.pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体观察者（厨师）
 */
class Chef implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        Music music = (Music)o;
        System.out.println(String.format("厨师听到音乐【%s】开始做饭", music.getName()));
    }
}
