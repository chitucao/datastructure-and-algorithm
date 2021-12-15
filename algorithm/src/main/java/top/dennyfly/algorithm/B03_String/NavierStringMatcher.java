package top.dennyfly.algorithm.B03_String;

/**
 * @author dennyfly
 * @since 2021/12/13 17:38
 * 朴素的字符串匹配
 * 时间复杂度 O(n*k)  n:主子符串的长度 k:查找字符串的长度
 */
public class NavierStringMatcher {

    /**
     * @param text 主子符串
     * @param str  查找字符串
     * @return 主子符串中的匹配第一个元素的索引
     */
    public static int match(String text, String str) {
        for (int i = 0; i < text.length(); i++) {
            int k = i;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != text.charAt(k)) {
                    break;
                } else {
                    k++;
                    if (j == str.length() - 1) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
