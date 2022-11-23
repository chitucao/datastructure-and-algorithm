package top.chitucao.algorithm.Math;

import java.util.Objects;

/**
 * @author chitucao
 * @since 2022/11/23 10:10
 * 大数加法，包括正负的情况
 */
public class LargeNumber {

    public static void main(String[] args) {
        String num1 = "0";
        String num2 = "-458";
        System.out.println(new LargeNumber().add(num1, num2));
    }

    public String add(String num1, String num2) {

        boolean isPositiveNum1 = num1.charAt(0) != '-';
        boolean isPositiveNum2 = num2.charAt(0) != '-';

        if (isPositiveNum1 && isPositiveNum2) {
            return doAdd(num1, num2);
        } else if (!isPositiveNum1 && !isPositiveNum2) {
            return negate(doAdd(num1.substring(1), num2.substring(1)));
        } else if (isPositiveNum1 && !isPositiveNum2) {
            return doSub(num1, num2.substring(1));
        } else {
            return doSub(num2, num1.substring(1));
        }
    }

    // 相加
    private String doAdd(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int carry = 0;

        StringBuilder sb = new StringBuilder();

        while (len1 >= 0 || len2 >= 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1--) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2--) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }

        // 这里注意进位
        if (carry > 0) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    // 相减
    private String doSub(String num1, String num2) {
        if (!compare(num1, num2)) {
            return negate(doSub(num2, num1));
        }

        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int borrow = 0;

        StringBuilder sb = new StringBuilder();

        while (len1 >= 0 || len2 >= 0) {
            int n1 = len1 >= 0 ? num1.charAt(len1--) - '0' : 0;
            int n2 = len2 >= 0 ? num2.charAt(len2--) - '0' : 0;
            int num = n1 - n2 - borrow;
            if (num < 0) {
                borrow = 1;
                num += 10;
            }
            sb.append(num);
        }

        // 反转后去掉前导0
        sb.reverse();
        int idx = 0;
        while (idx < sb.length() && sb.charAt(idx) == 0) {
            idx++;
        }
        // 等于0的特殊情况
        if (idx == sb.length()) {
            return "0";
        }

        return sb.substring(idx);
    }

    // num1是否大于num2
    private boolean compare(String num1, String num2) {
        if (num1.length() == num2.length()) {
            return num1.compareTo(num2) > 0;
        } else {
            return num1.length() > num2.length();
        }
    }

    // 反转正负情况
    private String negate(String num) {
        if (num.charAt(0) == '-') {
            return num.substring(1);
        } else if (Objects.equals(num, "0")) {
            return num;
        } else {
            return "-" + num;
        }
    }

}
