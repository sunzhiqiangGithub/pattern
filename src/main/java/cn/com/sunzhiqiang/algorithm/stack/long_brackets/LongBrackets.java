package cn.com.sunzhiqiang.algorithm.stack.long_brackets;

import java.util.Stack;

/**
 * 功能描述: 最长的有效括号
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class LongBrackets {

    public static void main(String[] args) {

        int maxLength = longestValidParentheses("((()");
        System.out.println(maxLength);
    }

    public static int longestValidParentheses(String s) {

        char[] charArray = s.toCharArray();

        Stack<Element> stack = new Stack();

        for (int i = 0; i < charArray.length; i++) {
            if (!stack.empty()) {
                Element temp = stack.peek();
                if ((charArray[i] == ')' && temp.character == '(')) {
                    stack.pop();
                    continue;
                }
            }

            stack.push(new Element(i, charArray[i]));
        }

        int maxLength = 0;
        int lastIndex = -1;
        int currIndex = 0;
        for (Element e : stack) {
            currIndex = e.index;
            if (currIndex - lastIndex - 1 > maxLength) {
                maxLength = currIndex - lastIndex - 1;
            }
            lastIndex = currIndex;
        }

        currIndex = charArray.length;
        if (currIndex - lastIndex - 1 > maxLength) {
            maxLength = currIndex - lastIndex - 1;
        }

        return maxLength;
    }

    static class Element {

        int index;
        Character character;

        public Element(int index, Character character) {
            this.index = index;
            this.character = character;
        }
    }
}
