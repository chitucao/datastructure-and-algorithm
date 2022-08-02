package top.chitucao.nowcoder;

/**
 * 比较版本号
 * https://www.nowcoder.com/practice/2b317e02f14247a49ffdbdba315459e7
 * <p>
 * 解法1：分割后比较
 */
public class VersionCompare {

    public int compare(String version1, String version2) {
        return soluton1(version1, version2);
    }

    /**
     * 分割后比较
     * 时间复杂度 O(m+n)
     * <p>
     * #思路
     * 1.分割后，按照高位进行比较，每段字符串去掉首位的0；
     */
    public int soluton1(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int len = Math.max(str1.length, str2.length);
        for (int i = 0; i < len; i++) {
            String a = i < str1.length ? str1[i] : "0";
            String b = i < str2.length ? str2[i] : "0";

            for (int j = 0; j < a.length(); j++) {

                // 只有一个字符（可以是0）或者 遇到第一个不为 0 的字符
                if (j == a.length() - 1 || a.charAt(j) != '0') {
                    a = a.substring(j);
                    break;
                }
            }
            for (int j = 0; j < b.length(); j++) {
                if (j == b.length() - 1 || b.charAt(j) != '0') {
                    b = b.substring(j);
                    break;
                }
            }
            if (a.length() > b.length()) {
                return 1;
            } else if (a.length() < b.length()) {
                return -1;
            } else {
                if (a.compareTo(b) > 0) {
                    return 1;
                } else if (a.compareTo(b) < 0) {
                    return -1;
                }
            }
        }
        // 说明所有轮数比较都相同
        return 0;
    }

}
