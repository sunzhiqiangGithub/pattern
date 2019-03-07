package cn.com.sunzhiqiang.data.structure.stack;

/**
 * @author sunzhiqiang
 * @date 2019/1/15
 * @desc 客户端类
 */
public class Client {

    public static void main(String[] args) {

        MyStack<Integer> myStack = new MyStack<>(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println("数组实现的栈");
        System.out.println(myStack);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack);

        MyLinkStack<Integer> myLinkStack = new MyLinkStack<>();
        myLinkStack.push(1);
        myLinkStack.push(2);
        myLinkStack.push(3);
        myLinkStack.push(4);
        myLinkStack.push(5);
        System.out.println("链表实现的栈");
        System.out.println(myLinkStack);
        System.out.println(myLinkStack.peek());
        System.out.println(myLinkStack.pop());
        System.out.println(myLinkStack);
    }
}
