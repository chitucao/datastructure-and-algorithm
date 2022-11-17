package top.chitucao.algorithm.String;

import org.junit.Test;

/**
 * @author chitucao
 * @since 2022/10/26 10:33
 * 给定一个字符串数组[“hello”,”max”,”aello”,”world”],
 * search(String s)
 * 判断字符串数组中是否存在一个字符串s1使得：s修改1个字符变为s1
 * <p>
 * 解法 https://blog.csdn.net/qq_44297220/article/details/119776474
 */
public class ReplaceOneChar {

    private static final String[] STRS = new String[]{"hello", "max", "aello", "world"};


    @Test
    public void testSearch() {
        String s = "ballo";
        System.out.println(search(s));
    }

    /**
     * 解法1：遍历统计
     */
    public static boolean search(String s) {

        for (int i = 0; i < STRS.length; i++) {
            int count = 0;
            String str = STRS[i];

            if (str.length() != s.length()) {
                return false;
            }

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != s.charAt(j)) {
                    if (count == 0) {
                        count++;
                    } else {
                        count++;
                        break;
                    }
                }
            }

            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
