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
        String s = "aallo";
        System.out.println(solution1(s));
    }

    /**
     * 解法1：遍历统计
     * 时间复杂度 O(mn)  m表示单个字符串长度，n表示字符串数量
     * 空间复杂度 O(1)
     * <p>
     * #思路
     * 1.遍历所有的字符串和所给字符串比较，长度不同的直接排除；
     * 2.长度相同的，遍历字符串，定义一个变量记录不同字符的数量，注意可以count>1可以提前退出
     */
    public static boolean search(String s) {

        for (int i = 0; i < STRS.length; i++) {
            String str = STRS[i];

            if (str.length() != s.length()) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != s.charAt(j)) {
                    if (count == 0) {
                        count++;
                    } else {
                        count++;    // 此时count==2
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

    private boolean solution1(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }

        for (int i = 0; i < STRS.length; i++) {
            String curStr = STRS[i];
            if (curStr.length() != s.length()) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != curStr.charAt(j)) {
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
