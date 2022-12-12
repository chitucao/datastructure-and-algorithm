package top.chitucao.tmp;

import java.util.Objects;

/**
 * @author chitucao
 * @since 2022/12/12 14:45
 */
public class LargeNum {

    public static void main(String[] args) {
        String num1 = "-123";
        String num2 = "-456";
        System.out.println(new LargeNum().add(num1, num2));
    }

    public String add(String num1, String num2) {
        boolean positiveNum1 = num1.charAt(0) != '-';
        boolean positiveNum2 = num2.charAt(0) != '-';

        if (positiveNum1 && positiveNum2) {
            return doAdd(num1, num2);
        } else if (!positiveNum1 && !positiveNum2) {
            return negate(doAdd(num1.substring(1), num2.substring(1)));
        } else if (positiveNum1 && !positiveNum2) {
            return doSub(num1, num2.substring(1));
        } else {
            return doSub(num2, num1.substring(1));
        }
    }

    public String doAdd(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (len1 >= 0 || len2 >= 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1--) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2--) - '0' : 0;
            int sum = n1 + n2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public String doSub(String num1, String num2) {
        if (!compare(num1, num2)) {
            return negate(doSub(num2, num1));
        }

        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int borrow = 0;
        while (len1 >= 0 || len2 >= 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1--) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2--) - '0' : 0;
            int sub = n1 - n2 - borrow;
            if (sub < 0) {
                sub += 10;
                borrow = 1;
            }
            sb.append(sub);
        }

        sb.reverse();

        int idx = 0;

        while (idx < sb.length() && sb.charAt(idx) == '0') {
            idx++;
        }
        if (idx == sb.length()) {
            return "0";
        }
        return sb.substring(idx);
    }

    private boolean compare(String num1, String num2) {
        if (num1.length() == num2.length()) {
            for (int i = 0; i < num1.length(); i++) {
                if (num1.charAt(i) < num2.charAt(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return num1.length() > num2.length();
        }
    }

    private String negate(String num) {
        if (Objects.equals(num, "0")) {
            return num;
        } else if (num.charAt(0) == '-') {
            return num.substring(1);
        } else {
            return "-" + num;
        }
    }
}
