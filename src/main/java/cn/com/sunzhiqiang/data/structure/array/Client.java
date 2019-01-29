package cn.com.sunzhiqiang.data.structure.array;

import java.util.Random;

/**
 * @author sunzhiqiang
 * @date 2019/1/15
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        MyArray myArray = new MyArray(10);

        // 初始化
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            myArray.append(random.nextInt(10));
        }
        System.out.println(myArray.toString());

        // 删除第二个位置
        myArray.delete(1);
        System.out.println(myArray.toString());

        // 在第二个位置插入
        myArray.insert(1, 8);
        System.out.println(myArray.toString());

        // 反转数组
        System.out.println(myArray.reverse().toString());

        // 数组元素个数
        System.out.println(myArray.size());

        // 查找元素
        System.out.println(myArray.find(2));
    }
}
