package top.dennyfly.algorithm.A06_String;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2021/12/13 17:42
 */
public class Main {

    @Test
    public void testNavieStringMatch() {
        String text = "ASDFEGABC";
        String str = "ABC";
        int match = NavierStringMatcher.match(text, str);
        System.out.println("匹配的索引位置：" + match);
    }
}
