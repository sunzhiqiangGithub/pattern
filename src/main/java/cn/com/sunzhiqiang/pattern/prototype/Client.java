package cn.com.sunzhiqiang.pattern.prototype;

import java.io.IOException;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2018-08-19
 */
public class Client {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        PersonComputer personComputer = new PersonComputer();
        personComputer.cpu = "4核心8线程";
        personComputer.ram = "16G,DDR4";

        PersonComputer personComputer1 = (PersonComputer) personComputer.getObjectByClone();
        System.out.println("cpu:" + personComputer1.cpu + ",ram:" + personComputer1.ram);

        PersonComputer personComputer2 = (PersonComputer) personComputer.getObjectBySerializable();
        System.out.println("cpu:" + personComputer2.cpu + ",ram:" + personComputer2.ram);
    }
}
