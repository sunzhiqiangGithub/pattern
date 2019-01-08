package cn.com.sunzhiqiang.pattern.observer;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Dancer dancer = new Dancer();
        Chef chef = new Chef();

        Music music = new Music();
        music.addObserver(dancer);
        music.addObserver(chef);

        music.start("学猫叫");
    }
}
