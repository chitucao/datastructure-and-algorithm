package top.dennyfly.algorithm.dynamicprograming;

/**
 * @author DennyFly
 * @since 2021/11/18 13:55
 * 最长公共子串长度（Longest common substring length）
 * <p>
 * #思路#
 */
public class MaxCharCommon {

    /**
     * @param str1 作为状态矩阵的竖向
     * @param str2 作为状态矩阵的横向
     * @return 最长公共串长度
     */
    public int commonCount(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // 值表示相同字符串的长度
        int status[][] = new int[n][m];

        // 初始化第一行数据
        for (int j = 0; j < m; j++) {
            if (str2.charAt(j) == str1.charAt(0)) {
                status[0][j] = 1;
            } else if (j != 0) {
                status[0][j] = status[0][j - 1];
            } else {
                status[0][j] = 0;
            }
        }

        // 初始化第一列数据
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                status[i][0] = 1;
            } else if (i != 0) {
                status[i][0] = status[i - 1][0];
            } else {
                status[i][0] = 0;
            }
        }

        // 动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    status[i][j] = max(status[i - 1][j], status[i][j - 1], status[i - 1][j - 1] + 1);
                } else {
                    status[i][j] = max(status[i - 1][j], status[i][j - 1], status[i - 1][j - 1]);
                }
            }
        }

        return status[n - 1][m - 1];
    }

    private int max(int x, int y, int z) {
        int max = Integer.MIN_VALUE;

        if (x > max) {
            max = x;
        }

        if (y > max) {
            max = y;
        }

        if (z > max) {
            max = z;
        }

        return max;
    }
}
