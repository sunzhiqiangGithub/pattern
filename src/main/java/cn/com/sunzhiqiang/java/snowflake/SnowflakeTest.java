package cn.com.sunzhiqiang.java.snowflake;

import com.google.common.collect.Maps;
import xyz.downgoon.snowflake.Snowflake;

import java.util.Map;

/**
 * @author sunzhiqiang
 * @date 2018/12/21
 * @desc 测试生成id算法
 */
public class SnowflakeTest {

    public static void main(String[] args) {

        Snowflake snowflake = new Snowflake(2, 5);

        long start = System.currentTimeMillis();
        Map<Long, Boolean> map = Maps.newHashMapWithExpectedSize(10000000);
        for (int i = 0; i < 10000000; i++) {
            long id = snowflake.nextId();
            System.out.println(id);
            System.out.println(snowflake.formatId(id));

            if (map.get(id) != null) {
                System.out.println("重复的id：" + id);
            } else {
                map.put(id, Boolean.TRUE);
            }
        }
        System.out.println("=======================");
        System.out.println(map.size());

        System.out.println("=======================");
        System.out.println(String.format("总耗时：%d秒", (System.currentTimeMillis() - start) / 1000));
    }
}
