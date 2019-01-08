package cn.com.sunzhiqiang.pattern.observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 具体观察者（舞蹈家）
 */
class Dancer implements Observer {

    @Override
    public void update(Observerable observerable) {

        Music music = (Music) observerable;
        System.out.println(String.format("舞蹈家听到音乐【%s】开始跳舞", music.getName()));
    }
}
