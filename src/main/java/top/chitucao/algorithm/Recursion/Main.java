package top.chitucao.algorithm.Recursion;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2021/11/26 15:50
 */
public class Main {

    @Test
    public void testFactorial() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(4);
        System.out.println(result);
    }
}
