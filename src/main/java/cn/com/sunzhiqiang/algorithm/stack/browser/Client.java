package cn.com.sunzhiqiang.algorithm.stack.browser;

/**
 * 功能描述: 客户端类
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class Client {

    public static void main(String[] args) {

        Browser browser = new Browser();
        browser.access("a").access("b").access("c").access("d").access("e");

        System.out.println(browser.backward());
        System.out.println(browser.backward());
        System.out.println(browser.backward());
        System.out.println(browser.forward());
    }
}
