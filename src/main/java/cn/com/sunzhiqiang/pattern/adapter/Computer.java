package cn.com.sunzhiqiang.pattern.adapter;

import java.util.Arrays;

/**
 * 功能描述: 适配器模式
 * 客户端
 *
 * @author sunzhiqiang
 * @create 2018-08-11
 */
public class Computer {

    public void play(DirectCurrent dc){

        byte[] array = dc.getDC();
        System.out.println("使用直流电:" + Arrays.toString(array));
    }

    public static void main(String[] args){

        ElectricCompary electricCompary = new ElectricCompary();

        Computer computer = new Computer();
        computer.play(new ElectricAdapter(electricCompary));
    }
}
