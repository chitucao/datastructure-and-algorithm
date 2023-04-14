package top.chitucao.tmp;

/**
 * @author chitucao
 * @since 2022/12/15 19:30
 */
public class Temp {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                i++;
                continue;
            }
            System.out.println(i);
        }
    }
}
