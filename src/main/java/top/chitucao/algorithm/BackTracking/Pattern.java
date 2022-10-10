package top.chitucao.algorithm.BackTracking;

/**
 * @author DennyFly
 * @since 2021/11/15 10:06
 * 正则匹配
 * <p>
 * “*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符
 */
public class Pattern {

    private boolean matched = false;
    private String pattern; // 正则表达式

    public Pattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean match(String text) {
        matched = false;
        rematch(0, 0, text);
        return matched;
    }

    /**
     * 注意这里都是匹配完正则串才给出结果，没有匹配不上提前return的情况
     */
    private void rematch(int ti, int pj, String text) {
        // 已经匹配了就无需递归了
        if (matched) {
            return;
        }

        // 到达正则表达式结尾（终止条件）
        if (pj == pattern.length()) {
            // 如果同时也达到字符串末尾，表示匹配成功
            if (ti == text.length()) {
                matched = true;
            }
            return;
        }


        if (pattern.charAt(pj) == '*') {
            // 匹配0个或任意个字符
            for (int k = 0; k < text.length() - ti; k++) {
                rematch(ti + k, pj + 1, text);
            }
        } else if (pattern.charAt(pj) == '?') {
            // 匹配0个或一个字符
            rematch(ti, pj + 1, text);
            rematch(ti + 1, pj + 1, text);
        } else if (ti < text.length() && pattern.charAt(pj) == text.charAt(ti)) {
            // 完全匹配
            rematch(ti + 1, pj + 1, text);
        }
    }
}
