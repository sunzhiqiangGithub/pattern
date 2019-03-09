package cn.com.sunzhiqiang.algorithm.stack.polish_notation;

import java.util.Stack;

/**
 * 功能描述: 逆波兰表达式求值
 *
 * @author sunzhiqiang
 * @create 2019-03-09
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {

        int result = evalRPN(new String[]{"4", "3", "-"});
        System.out.println(result);
    }

    public static int evalRPN(String[] tokens) {

        Stack<Integer> numberStack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {

            Integer number1 = null;
            Integer number2 = null;
            switch (tokens[i]) {
                case "+":
                    number2 = numberStack.pop();
                    number1 = numberStack.pop();
                    numberStack.push(number1 + number2);
                    break;
                case "-":
                    number2 = numberStack.pop();
                    number1 = numberStack.pop();
                    numberStack.push(number1 - number2);
                    break;
                case "*":
                    number2 = numberStack.pop();
                    number1 = numberStack.pop();
                    numberStack.push(number1 * number2);
                    break;
                case "/":
                    number2 = numberStack.pop();
                    number1 = numberStack.pop();
                    numberStack.push(number1 / number2);
                    break;
                default:
                    numberStack.push(Integer.valueOf(tokens[i]));
                    break;
            }
        }

        return numberStack.peek();
    }
}
