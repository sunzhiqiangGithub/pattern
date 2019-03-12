package cn.com.sunzhiqiang.algorithm.hash.map;

import java.util.Set;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-03-12
 */
public class Client {

    public static void main(String[] args) {

        MyHashMap<String, String> myHashMap = new MyHashMap();

        myHashMap.put("aaa", "aaa");
        System.out.println(myHashMap.get("aaa"));

        myHashMap.put("aaa", "aaaaaa");
        myHashMap.put("bbb", "bbb");
        System.out.println(myHashMap.get("aaa"));
        System.out.println(myHashMap.get("bbb"));

        myHashMap.remove("bbb");
        System.out.println(myHashMap.get("bbb"));

        myHashMap.put("ccc", "ccc");
        myHashMap.put("ddd", "ddd");
        Set<MyHashMap.Node<String, String>> set = myHashMap.entrySet();
        for (MyHashMap.Node<String, String> node : set) {
            System.out.println(node.key + "=" + node.value);
        }
    }
}
