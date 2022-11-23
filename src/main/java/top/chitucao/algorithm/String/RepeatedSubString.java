package top.chitucao.algorithm.String;

/**
 * @author chitucao
 * @since 2022/11/15 13:10
 */
public class RepeatedSubString {


    public static void main(String[] args) {
        String str = "abababa";
        System.out.println(new RepeatedSubString().checkRepeatSub(str));
    }

    public boolean checkRepeatSub(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }

        int n = s.length();
        int parts = 1;  // 一共几段

        while (parts < n) {
            if (n % (parts + 1) == 0) {
                boolean repeat = true;
                int len = n / (parts + 1);
                for (int i = 1; i <= parts; i++) {  // 外层是第几段，这里因为是取（count+1）计算的，所以是<=
                    for (int j = 0; j < len; j++) { // 内层是段中索引
                        if (s.charAt(j) != s.charAt(j + i * len)) {
                            repeat = false; // false的时候内层和外层都需要退出循环，所以有两个break
                            break;
                        }
                    }
                    if (!repeat) {
                        break;
                    }
                }
                if (repeat) {
                    return true;
                }
            }
            parts++;
        }
        return false;
    }


}
