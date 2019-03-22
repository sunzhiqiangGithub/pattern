package cn.com.sunzhiqiang.java.date;

import java.time.LocalTime;

/**
 * @author sunzhiqiang
 * @description: LocalTime学习
 * @date 2019-03-2217:10
 */
public class LocalTimeStudy {

    public static void main(String[] args) {

        // 当前时间
        LocalTime now = LocalTime.now();
        System.out.println(now);

        // 加小时
        LocalTime localTime = now.plusHours(2);
        System.out.println(localTime);
    }
}
