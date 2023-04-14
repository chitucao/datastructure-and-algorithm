package top.chitucao.algorithm.Math;

import java.util.Objects;

/**
 * @author chitucao
 * @since 2022/11/23 10:10
 * 大数加法，包括正负的情况
 * 参考 https://developer.aliyun.com/article/1057265
 */
public class LargeNumAddSub {

    public static void main(String[] args) {
        String num1 = "-123";
        String num2 = "456";
        System.out.println(new LargeNumAddSub().add(num1, num2));
    }

    public String add(String num1, String num2) {
        boolean isPostiveNum1 = !num1.startsWith("-");
        boolean isPositveNum2 = !num2.startsWith("-");
        if (isPostiveNum1 && isPositveNum2) {
            return doAdd(num1, num2);
        } else if (!isPostiveNum1 && !isPositveNum2) {
            return negate(doAdd(num1.substring(1), num2.substring(1)));
        } else if (isPostiveNum1 && !isPositveNum2) {
            return doSub(num1, num2.substring(1));
        } else {
            return doSub(num2, num1.substring(1));
        }
    }

    // 相加
    private String doAdd(String num1, String num2) {
        int tail1 = num1.length() - 1;
        int tail2 = num2.length() - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (tail1 >= 0 || tail2 >= 0) {
            int n1 = tail1 >= 0 ? num1.charAt(tail1--) - '0' : 0;
            int n2 = tail2 >= 0 ? num2.charAt(tail2--) - '0' : 0;
            int sum = n1 + n2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    // 相减
    private String doSub(String num1, String num2) {
        if (!compare(num1, num2)) {
            return negate(doSub(num2, num1));
        }

        int tail1 = num1.length()-1;
        int tail2 = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        int borrow = 0;
        while (tail1 >= 0 || tail2 >= 0) {
            int n1 = tail1 >= 0 ? num1.charAt(tail1--) - '0' : 0;
            int n2 = tail2 >= 0 ? num2.charAt(tail2--) - '0' : 0;
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

    // num1是否大于等于num2
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

    // 反转正负情况
    private String negate(String num) {
        if (Objects.equals(num, "0")) {
            return num;
        } else if (num.startsWith("-")) {
            return num.substring(1);
        } else {
            return "-" + num;
        }
    }

}
