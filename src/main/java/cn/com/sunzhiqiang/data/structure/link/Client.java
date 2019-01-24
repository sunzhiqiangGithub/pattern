package cn.com.sunzhiqiang.data.structure.link;

/**
 * @author sunzhiqiang
 * @date 2019/1/15
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        linkedList.add(3).add(2).add(6).add(10).add(1);
        System.out.println(linkedList.toString());

        linkedList.remove(6);
        System.out.println(linkedList.toString());

        linkedList.insertBefore(1, 2);
        System.out.println(linkedList.toString());

        linkedList.insertAfter(10, 9);
        System.out.println(linkedList.toString());

        System.out.println(linkedList.toInvertString());

        System.out.println(linkedList.contain(10));
        System.out.println(linkedList.contain(11));

        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
    }
}
