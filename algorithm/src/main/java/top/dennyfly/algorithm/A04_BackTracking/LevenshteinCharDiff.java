package top.dennyfly.algorithm.A04_BackTracking;

/**
 * @author DennyFly
 * @since 2021/11/18 10:43
 * 莱文斯坦距离（levenshtein distance）    基于回溯算法
 * #利用莱文斯坦距离计算差异距离（levenshtein distance）
 */
public class LevenshteinCharDiff {

    /**
     * 两个字符串的最小差异距离
     */
    private int minDist;

    private String str1;

    private String str2;

    public LevenshteinCharDiff(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        this.minDist = Integer.MAX_VALUE;
    }

    public int diffCount() {
        diffCount(0, 0, 0);
        return minDist;
    }

    private void diffCount(int i, int j, int edist) {
        // 终止条件，有一个字符串先达到末尾
        if (i == str1.length() || j == str2.length()) {
            if (i < str1.length()) {
                edist += str1.length() - i;
            }
            if (j < str2.length()) {
                edist += str2.length() - j;
            }
            if (edist < minDist) {
                minDist = edist;
            }
            return;
        }


        if (str1.charAt(i) == str2.charAt(j)) {
            // 匹配的情况
            diffCount(i + 1, j + 1, edist);
        } else {
            // 删除str1[i]或者在str2[j]前面加上一个字符
            diffCount(i + 1, j, edist + 1);
            // 删除str2[j]或者在str1[i]前面加上一个字符
            diffCount(i, j + 1, edist + 1);
            // 将str1[i]和str2[j]替换成相同字符
            diffCount(i + 1, j + 1, edist + 1);
        }
    }

}
