package top.chitucao.tmp;

import org.junit.Test;

public class MathTest {

    /**
     * 最大公约数测试
     */
    @Test
    public void testGcd() {
        int gcd = gcd(4, 6);
        System.out.println("最大公约数："+ gcd);
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
}
