package cn.com.sunzhiqiang.java.date;

import java.time.Clock;

/**
 * @author sunzhiqiang
 * @description: Clock学习
 * @date 2019-03-2217:21
 */
public class ClockStudy {

    public static void main(String[] args) {

        Clock clock = Clock.systemUTC();
        System.out.println(clock);

        clock = Clock.systemDefaultZone();
        System.out.println(clock);
    }
}
