package cn.com.sunzhiqiang.algorithm.stack.brackets;

import java.util.Stack;

/**
 * 功能描述: 有效的括号
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class ValidBrackets {

    public static void main(String[] args) {

        boolean isValid = isValid("()");
        System.out.println(isValid);
    }

    public static boolean isValid(String s) {

        char[] charArray = s.toCharArray();

        Stack<Character> stack = new Stack();

        for (int i = 0; i < charArray.length; i++) {
            if (!stack.empty()) {
                Character temp = stack.peek();
                if ((charArray[i] == ')' && temp == '(')
                        || (charArray[i] == ']' && temp == '[')
                        || (charArray[i] == '}' && temp == '{')) {
                    stack.pop();
                    continue;
                }
            }

            stack.push(charArray[i]);
        }

        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }
}
