package lv1simple;

import java.util.Stack;

/**
 * Q20 有效的括号
 * <p>
 * difficulty: esay
 * <p>
 * think1 栈
 *
 * @link { https://leetcode-cn.com/problems/valid-parentheses/}
 */
public class Q20_S_valid_parentheses {


    public static boolean isValid(String str) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "(){}";

        String str2 = "([()]){}";

        String str3 = "{abc}";

        String str4 = "{{}adfsb";

        System.out.println(isValid(str4));
    }

}
