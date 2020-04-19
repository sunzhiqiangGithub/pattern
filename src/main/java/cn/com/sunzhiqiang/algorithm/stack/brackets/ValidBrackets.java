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

        for (int i = 0, length = charArray.length; i < length; i++) {
            if (charArray[i] == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            } else if (charArray[i] == ']') {
                if (stack.empty() || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            } else if (charArray[i] == '}') {
                if (stack.empty() || stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(charArray[i]);
            }
        }

        return stack.empty();
    }
}
