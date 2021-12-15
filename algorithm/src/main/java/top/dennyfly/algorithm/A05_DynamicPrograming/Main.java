package top.dennyfly.algorithm.A05_DynamicPrograming;

import org.junit.Test;
import top.dennyfly.algorithm.A04_BackTracking.Pkg;

import java.util.Arrays;

/**
 * @author DennyFly
 * @since 2021/11/15 16:34
 */
public class Main {

    @Test
    public void testPackageWeight() {
        int[] items = new int[]{10, 20, 30, 40, 35, 45, 55, 75};

        PackageWeight pkg = new PackageWeight(items, 2, 60);
        System.out.println(pkg.countMaxWeight());
    }

    @Test
    public void testPackageWeight2() {
        int[] items = new int[]{10, 20, 30, 40, 35, 45, 55, 75};

        PackageWeight2 pkg = new PackageWeight2(items, 2, 60);
        System.out.println(pkg.countMaxWeight());
    }

    @Test
    public void testPackageValue() {
        Pkg[] items = new Pkg[5];

        items[0] = new Pkg("苹果", 10, 100);
        items[1] = new Pkg("香蕉", 20, 80);
        items[2] = new Pkg("香蕉", 30, 120);
        items[3] = new Pkg("菠萝", 25, 125);
        items[4] = new Pkg("橙子", 15, 160);

        PackageValue instance = new PackageValue(items, 5, 50);
        System.out.println("最大总价值为 ：" + instance.countMaxValue());
    }

    @Test
    public void testPackageValue2() {
        Pkg[] items = new Pkg[5];

        items[0] = new Pkg("苹果", 10, 100);
        items[1] = new Pkg("香蕉", 20, 80);
        items[2] = new Pkg("香蕉", 30, 120);
        items[3] = new Pkg("菠萝", 25, 125);
        items[4] = new Pkg("橙子", 15, 160);

        PackageValue2 instance = new PackageValue2(items, 5, 20);
        System.out.println("最大总价值为 ：" + instance.countMaxValue());
    }

    @Test
    public void testDouble11() {
        int[] prices = new int[]{58, 68, 78, 45, 49, 88, 128};
        int condition = 200;
        Double11 instance = new Double11(prices, condition);
        instance.shopping();
    }

    @Test
    public void testYanghuiTriangle() {

        // 两条路径，因为列是从前向后遍历的，所以这个路径寻找的不正确，最小值是正确的
        // ->2->4->2->7->5  对
        // ->2->1->4->8->5  错++
        int[][] data = {
                {0, 0, 0, 0, 5},
                {0, 0, 0, 7, 0, 8},
                {0, 0, 2, 0, 3, 0, 4},
                {0, 4, 0, 9, 0, 6, 0, 1},
                {2, 0, 7, 0, 9, 0, 4, 0, 5}
        };
        YanghuiTriangle instance = new YanghuiTriangle(data);
        instance.navigate();


        System.out.println("-------------");

        // 两条路径，因为列是从前向后遍历的，所以这个路径寻找的不正确，最小值是正确的
        // ->1->4->2->7->5  错
        // ->1->1->4->8->5  对++
        int[][] data3 = {
                {0, 0, 0, 0, 5},
                {0, 0, 0, 7, 0, 8},
                {0, 0, 2, 0, 3, 0, 4},
                {0, 4, 0, 9, 0, 6, 0, 1},
                {2, 0, 7, 0, 9, 0, 1, 0, 5}
        };
        instance = new YanghuiTriangle(data3);
        instance.navigate();
    }

    @Test
    public void testMatrixDistance1() {
        int[][] data = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        MatrixDistance1 instance = new MatrixDistance1(data);
        System.out.println("最短路径长度：" + instance.navigate());
    }

    @Test
    public void testMatrixDistance2() {
        int[][] data = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        MatrixDistance2 instance = new MatrixDistance2(data);
        System.out.println("最短路径长度：" + instance.navigate());
    }

    @Test
    public void testChangeMoney() {
        int[] coins = {1, 3, 5};
        int expected = 9;
        MoneyChange moneyChange = new MoneyChange(coins, expected);
        System.out.println("最少找零数量：" + moneyChange.change());
    }

    @Test
    public void testLevenshteinCharMatch() {
        String str1 = "mitcmu";
        String str2 = "mtacnu";
        LevenshteinCharDiff instance = new LevenshteinCharDiff();
        System.out.println("两个字符串的莱文斯坦距离为：" + instance.diffCount(str1, str2));
    }

    @Test
    public void testMaxCharCommon() {
        String str1 = "mitcmu";
        String str2 = "mtacnu";
        MaxCharCommon instance = new MaxCharCommon();
        System.out.println("两个字符串的最大公共子串长度为：" + instance.commonCount(str1, str2));
    }

    @Test
    public void testMaxSequence() {
        MaxSequence instance = new MaxSequence();
        int[] nums = {2, 9, 3, 6, 5, 1, 7};
        System.out.println("测试数据："+ Arrays.toString(nums));
        System.out.println("最大递增序列为：" + instance.countMaxLength(nums));
    }
}
