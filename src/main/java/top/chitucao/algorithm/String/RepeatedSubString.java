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
        int count = 1;

        while (count < n) {
            if (n % (count + 1) == 0) {
                boolean repeat = true;
                int len = n / (count + 1);
                for (int i = 1; i <= count; i++) {
                    for (int j = 0; j < len; j++) {
                        if (s.charAt(j) != s.charAt(j + i * len)) {
                            repeat = false;
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
            count++;
        }
        return false;
    }

}
