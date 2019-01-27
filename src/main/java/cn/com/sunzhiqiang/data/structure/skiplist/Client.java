package cn.com.sunzhiqiang.data.structure.skiplist;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-27
 */
public class Client {

    public static void main(String[] args) {

        SkipList<Integer> skipList = new SkipList<>(2);
        skipList.create(new Integer[]{2, 6, 9, 1, 5, 3, 4, 8});

        System.out.println("打印跳表");
        skipList.printAll();

        System.out.println("查询");
        SkipList.Node result = skipList.find(5);
        if (result != null) {
            System.out.println("当前结点：" + result.getData());
            if (result.getNext() != null) {
                System.out.println("下一个结点：" + result.getNext().getData());
            }
        }
    }
}
