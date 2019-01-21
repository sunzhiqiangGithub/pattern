package cn.com.sunzhiqiang.data.structure.queue;

import java.util.Random;

/**
 * @author sunzhiqiang
 * @date 2019/1/15
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        MyQueue<Integer> myQueue = new MyQueue<>(10);

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                myQueue.pop();
            } else {
                boolean success = myQueue.offer(random.nextInt(30));
            }

            System.out.println(myQueue.toString());
        }
    }
}
