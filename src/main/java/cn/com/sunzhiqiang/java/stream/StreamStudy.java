package cn.com.sunzhiqiang.java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * @author sunzhiqiang
 * @description: stream api学习
 * @date 2019-03-2211:02
 */
public class StreamStudy {

    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList();
        list.add(8);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(6);

        // filter
        list.stream().filter(s -> s == 1).forEach(System.out::println);

        // map
        System.out.println(list.stream().map(item -> String.valueOf(item)).collect(Collectors.joining(",")));

        // mapToInt
        list.stream().mapToInt(item -> item).forEach(s -> System.out.print(s + "\t"));
        System.out.println();

        // mapToLong
        list.stream().mapToLong(item -> item).forEach(s -> System.out.print(s + "\t"));
        System.out.println();

        // mapToDouble
        list.stream().mapToDouble(item -> item).forEach(s -> System.out.print(s + "\t"));
        System.out.println();

        // flatMap
        ArrayList<Integer> list2 = (ArrayList<Integer>) (((ArrayList<Integer>) list).clone());
        List<List<Integer>> table = Lists.newArrayList(list, list2);
        table.stream().flatMap(item -> item.stream()).forEach(item -> System.out.print(item + "\t"));
        System.out.println();

        // flatMapToInt
        table.stream().flatMapToInt(item -> item.stream().mapToInt(s -> s)).forEach(
                item -> System.out.print(item + "\t"));
        System.out.println();

        // flatMapToLong
        table.stream().flatMapToLong(item -> item.stream().mapToLong(s -> s)).forEach(
                item -> System.out.print(item + "\t"));
        System.out.println();

        // flatMapToDouble
        table.stream().flatMapToDouble(item -> item.stream().mapToDouble(s -> s)).forEach(
                item -> System.out.print(item + "\t"));
        System.out.println();

        // distinct
        list.stream().distinct().forEach(item -> System.out.print(item + "\t"));
        System.out.println();

        // sorted
        list.stream().sorted().forEach(item -> System.out.print(item + "\t"));
        System.out.println();

        // peek
        list.stream().peek(item -> System.out.print(item + "\t")).filter(item -> item == 1).collect(
                Collectors.toList());
        System.out.println();

        // limit
        list.stream().limit(3).forEach(item -> System.out.print(item + "\t"));
        System.out.println();

        // skip
        list.stream().skip(1).forEach(item -> System.out.print(item + "\t"));
        System.out.println();

        // forEachOrdered
        list.stream().forEachOrdered(item -> System.out.print(item + "\t"));
        System.out.println();

        // toArray
        System.out.println(Arrays.toString(list.stream().toArray()));

        // reduce
        System.out.println(list.stream().reduce((item, item2) -> item + item2).get());

        // min
        System.out.println(
                list.stream().min((item1, item2) -> item1 - item2 > 0 ? 1 : item1 - item2 == 0 ? 0 : -1).get());

        // max
        System.out.println(
                list.stream().max((item1, item2) -> item1 - item2 > 0 ? 1 : item1 - item2 == 0 ? 0 : -1).get());

        // count
        System.out.println(list.stream().count());

        // anyMatch
        System.out.println(list.stream().anyMatch(item -> item == 8));

        // allMatch
        System.out.println(list.stream().allMatch(item -> item == 8));

        // noneMatch
        System.out.println(list.stream().noneMatch(item -> item == -1));

        // findFirst
        System.out.println(list.stream().findFirst().get());

        // findAny
        System.out.println(list.stream().findAny().get());
    }
}
