package top.dennyfly.algorithm.recursion;

import org.junit.Test;
import top.dennyfly.algorithm.backtracking.FullArray;

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
