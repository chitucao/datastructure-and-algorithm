package top.dennyfly.algorithm.T05_Dynamic_Programing;

/**
 * @author DennyFly
 * @since 2021/11/18 10:43
 * 莱文斯坦距离（levenshtein distance）  基于动态规划（状态转移方程+备忘录）
 * #思路#
 * 之前的距离只有左边和上边两种状态，这里有上边，左边，和左上边
 * 如果当前两个元素相等时，只需要取上一次的左上角的值，否则还需要加上1
 */
public class LevenshteinCharDiff {

    /**
     * @param str1 作为状态矩阵的竖向
     * @param str2 作为状态矩阵的横向
     * @return 莱文斯坦距离
     */
    public int diffCount(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int status[][] = new int[n][m];

        // 初始化第一行数据
        for (int j = 0; j < m; j++) {
            if (str2.charAt(j) == str1.charAt(0)) {
                status[0][j] = j;
            } else if (j != 0) {
                status[0][j] = status[0][j - 1] + 1;
            } else {
                status[0][j] = 1;
            }
        }

        // 初始化第一列数据
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                status[i][0] = i;
            } else if (i != 0) {
                status[i][0] = status[i - 1][0] + 1;
            } else {
                status[i][0] = 1;
            }
        }

        // 动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    status[i][j] = min(status[i - 1][j] + 1, status[i][j - 1] + 1, status[i - 1][j - 1]);
                } else {
                    status[i][j] = min(status[i - 1][j], status[i][j - 1], status[i - 1][j - 1]) + 1;
                }
            }
        }

        return status[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int min = Integer.MAX_VALUE;
        if (x < min) {
            min = x;
        }
        if (y < min) {
            min = y;
        }
        if (z < min) {
            min = z;
        }

        return min;
    }
}
