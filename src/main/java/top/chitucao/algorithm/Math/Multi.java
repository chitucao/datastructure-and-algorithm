package top.chitucao.algorithm.Math;

import org.junit.Test;

public class Multi {

    @Test
    public void testQuickMulti() {
        int res = quickMulti(3, 5);
        System.out.println(res);
    }

    /**
     * 快速乘
     * #思路
     * 1.俄罗斯农民乘法
     * 考虑 A 和 B 两数相乘的时候我们如何利用加法和位运算来模拟，其实就是将 B 二进制展开，
     * 如果 B 的二进制表示下第 i 位为 1，那么这一位对最后结果的贡献就是 A∗(1<<i) ，即 A<<i。
     * 我们遍历 B 二进制展开下的每一位，将所有贡献累加起来就是最后的答案，这个方法也被称作「俄罗斯农民乘法」，
     * 感兴趣的读者可以自行网上搜索相关资料。这个方法经常被用于两数相乘取模的场景，如果两数相乘已经超过数据范围，
     * 但取模后不会超过，我们就可以利用这个方法来拆位取模计算贡献，保证每次运算都在数据范围内。
     */
    public int quickMulti(int a, int b) {
        int ans = 0;
        for (; b > 0; b >>= 1) {
            System.out.println(b);
            System.out.println(a);
            System.out.println(ans);

            if ((b & 1) > 0) {
                System.out.println("in");
                ans += a;
            }
            a <<= 1;
            System.out.println("---");
        }
        return ans;
    }
}
