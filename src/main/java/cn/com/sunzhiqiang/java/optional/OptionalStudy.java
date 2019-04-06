package cn.com.sunzhiqiang.java.optional;

import java.util.Optional;


/**
 * @author sunzhiqiang
 * @description: 学习Optional使用
 * @date 2019-04-0700:01
 */
public class OptionalStudy {

    static class Computer {

        Optional<Keyboard> keyboard;
    }

    static class Keyboard {

        Optional<Key> key;
    }

    static class Key {

        String name;
    }

    public static void main(String[] args) {

        // 创建一个空的Optional对象
        Optional<Integer> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);

        // 创建一个不能为空的Optional对象
        Optional<Integer> notNullOptional = Optional.of(1);
        System.out.println(notNullOptional);

        // 创建一个可以为空的Optional对象
        Optional<Integer> optional = Optional.ofNullable(2);
        System.out.println(optional);

        // get方法
        System.out.println(optional.get());

        // filter方法
        System.out.println(optional.filter(item -> item == 1).orElse(0));

        // map方法
        System.out.println(optional.map(item -> String.valueOf(item)).get());

        // flatmap方法
        Computer computer = new Computer();
        Keyboard keyboard = new Keyboard();
        Key key = new Key();
        key.name = "shift";
        keyboard.key = Optional.ofNullable(key);
        computer.keyboard = Optional.ofNullable(keyboard);
        Optional<Computer> computerOptional = Optional.ofNullable(computer);
        String name = computerOptional.flatMap(computer1 -> computer1.keyboard)
                .flatMap(keyboard1 -> keyboard.key)
                .map(name1 -> key.name)
                .orElse(null);
        System.out.println(name);

        // ifPresent方法
        optional.ifPresent(System.out::println);

        // isPresent方法
        System.out.println(optional.isPresent());

        // orElseGet方法
        System.out.println(emptyOptional.orElseGet(() -> Integer.valueOf(10)));

        // orElseThrow方法
        System.out.println(emptyOptional.orElseThrow(() -> new NullPointerException()));
    }
}
