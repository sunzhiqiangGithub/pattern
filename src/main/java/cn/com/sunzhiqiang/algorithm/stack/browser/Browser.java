package cn.com.sunzhiqiang.algorithm.stack.browser;

import cn.com.sunzhiqiang.data.structure.stack.MyStack;

/**
 * 功能描述: 用栈模拟浏览器前进后退功能
 *
 * @author sunzhiqiang
 * @create 2019-03-07
 */
public class Browser {

    /**
     * 前进栈
     */
    private MyStack<String> forward;

    /**
     * 后退栈
     */
    private MyStack<String> backward;

    /**
     * 当前页面
     */
    private String currentPage;

    public Browser() {
        forward = new MyStack<>(10);
        backward = new MyStack<>(10);
    }

    /**
     * 前进
     *
     * @return
     */
    public String forward() {

        if (forward.peek() != null) {
            String url = forward.pop();
            if (currentPage != null) {
                backward.push(currentPage);
            }
            currentPage = url;
            return currentPage;
        }

        return null;
    }

    /**
     * 后退
     *
     * @return
     */
    public String backward() {

        if (backward.peek() != null) {
            String url = backward.pop();
            if (currentPage != null) {
                forward.push(currentPage);
            }
            currentPage = url;
            return currentPage;
        }

        return null;
    }

    /**
     * 普通方式访问一个url
     *
     * @param url
     */
    public Browser access(String url) {

        if (currentPage != null) {
            backward.push(currentPage);
        }
        currentPage = url;

        return this;
    }
}
