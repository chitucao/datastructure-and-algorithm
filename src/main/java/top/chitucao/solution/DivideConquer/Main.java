package top.chitucao.solution.DivideConquer;

import org.junit.Test;
import top.chitucao.solution.BackTracking.Pattern;

import java.util.List;

/**
 * @author DennyFly
 * @since 2021/11/12 10:59
 */
public class Main {

    // {2,1}、{4,1}、{3,1}、{4,3}
    private static int[] arr1 = new int[]{2, 4, 3, 1, 5, 6};

    // {2,1,1,0}
    private static int[] arr2 = new int[]{5, 2, 6, 1};

    @Test
    public void testReversePairCount() {
        int count = ReversePairCount.count(arr1);
        System.out.println("逆序对个数" + count);
    }

    @Test
    public void testReversePairList(){
        List<Integer> res = ReversePairList.solution1(arr2);
        System.out.println(res);
    }

    @Test
    public void testPattern(){
        String pattern = "tom*2?4";
        String text = "tom213211234";
        System.out.println(new Pattern(pattern).match(text));
    }
}
