package top.dennyfly.algorithm.dynamicprograming;

import org.junit.Test;
import top.dennyfly.algorithm.backtracking.Pkg;

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
}
