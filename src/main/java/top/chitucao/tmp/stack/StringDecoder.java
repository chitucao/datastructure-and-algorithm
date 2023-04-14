package top.chitucao.tmp.stack;

import java.util.Stack;

public class StringDecoder {

    public static void main(String[] args) {
//        String str = "3[a]2[bc]";
//        String res = solution1(str);
//        System.out.println(res);

        int count = 5;
        while (--count >-1){
            System.out.println(count);
        }
        System.out.println(count);
    }


    public static String solution1(String s) {
        int n = s.length();

        int count = 1;
        String str = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> strStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                count = num;
            } else if (ch == '[') {
                strStack.push(str);
                str = "";

                countStack.push(count);
                count = 1;
            } else if (ch == ']') {
                StringBuilder sb = new StringBuilder();
                sb.append(strStack.pop());
                for (int j = 0; j < countStack.pop(); j++) {
                    sb.append(str);
                }
                str = sb.toString();
            } else {
                str = str + ch;
            }
        }

        return str;
    }


}
