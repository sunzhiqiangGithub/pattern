package cn.com.sunzhiqiang.algorithm.lru.link;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-01-29
 */
public class Client {

    public static void main(String[] args) {

        LRUByLinkList<Integer> lruByLinkList = new LRUByLinkList<>(10);

        for (int i = 0; i < 9; i++){
            lruByLinkList.insert(i + 1);
        }
        lruByLinkList.printList();

        lruByLinkList.insert(1);
        lruByLinkList.printList();

        lruByLinkList.insert(10);
        lruByLinkList.printList();

        lruByLinkList.insert(3);
        lruByLinkList.printList();

        System.out.println(lruByLinkList.getList().toString());
    }
}
