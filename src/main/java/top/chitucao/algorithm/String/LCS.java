package top.chitucao.algorithm.String;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2022/7/26 14:42
 * longest common string
 */
public class LCS {

    @Test
    public void testLcs() {
        String str1 = "ffabc";
        String str2 = "abcdef";
        String result = lcs(str1, str2);
        System.out.println(result);
    }


    /**
     * 最长公共子串
     * <p>
     * 时间复杂度 O(mn)
     * 空间复杂度 O(1)
     * <p>
     * 动态规划
     * #思路
     * 转移方程
     * 1.i-1和j-1相等，则dp[i][j] = dp[i-1][j-1]+1；
     * 2.不相等，dp[i][j] = 0;
     * 3.记录最长的长度和对应长度时的最后一个位置索引；
     * 注意这里是公共字串不是公共子序列
     */
    public String lcs(String str1, String str2) {

        if (str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        int m = str1.length();
        int n = str2.length();
        int maxLength = 0;
        int maxLastIndex = 0;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        maxLastIndex = i - 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return str1.substring(maxLastIndex + 1 - maxLength, maxLastIndex + 1);

    }


}
