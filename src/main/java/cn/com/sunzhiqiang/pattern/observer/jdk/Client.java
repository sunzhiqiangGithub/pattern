package cn.com.sunzhiqiang.pattern.observer.jdk;

/**
 * @author sunzhiqiang
 * @date 2019/1/8
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        Chef chef = new Chef();
        Dancer dancer = new Dancer();

        Music music = new Music();
        music.addObserver(chef);
        music.addObserver(dancer);

        music.start("学猫叫");
    }
}
