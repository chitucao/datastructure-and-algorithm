package top.dennyfly.algorithm.T01_Recursion;

/**
 * @author dennyfly
 * @since 2021/11/26 15:46
 * 编程实现求阶乘
 */
public class Factorial {

    // 递归实现
    public int calc(int n) {
        if (n == 1) {
            return 1;
        }
        return n * calc(n - 1);
    }
}
