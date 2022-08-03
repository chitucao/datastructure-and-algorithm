package top.chitucao.solution.BackTracking;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/11/15 11:15
 */
public class Main {

    // 号称世界上最难数独
    private static final int[][] SUDUKU = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    @Test
    public void testEightQueen() {
        EightQueen eightQueen = new EightQueen();
        eightQueen.place();
        System.out.println("解法数量：" + eightQueen.count);
    }

    @Test
    public void testPackageWeight() {
        int[] items = new int[]{10, 20, 30, 40, 35, 45, 55, 75};

//        PackageWeight pkg = new PackageWeight(items,4,25);
        PackageWeight pkg = new PackageWeight(items, 8, 60);
        pkg.countMaxWeight();
        System.out.println(pkg.maxW);
    }

    @Test
    public void testPackageValue() {
        Pkg[] items = new Pkg[5];

        items[0] = new Pkg("苹果", 10, 100);
        items[1] = new Pkg("香蕉", 20, 80);
        items[2] = new Pkg("香蕉", 30, 120);
        items[3] = new Pkg("菠萝", 25, 125);
        items[4] = new Pkg("橙子", 15, 160);

//        PackageValue instance = new PackageValue(items,5,20);
        PackageValue instance = new PackageValue(items, 5, 50);
        instance.countMaxValue();
        System.out.println("最大总价值为 ：" + instance.maxV);
        System.out.println("最大总重量为 ：" + instance.maxW);
    }

    @Test
    public void testSudoku() {
        Sudoku sudoku = new Sudoku(9, SUDUKU);
//        Sudoku sudoku = new Sudoku(9);
        sudoku.place();
        sudoku.printData();
    }

    @Test
    public void testMatrixDistance() {
        int[][] data = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        MatrixDistance instance = new MatrixDistance(data);
        instance.navigate();
        System.out.println("最短路径长度：" + instance.minDistance);
    }

    @Test
    public void testMoneyChange(){
        int[] coins = {1,3,5};
        int expected = 9;
        MoneyChange moneyChange = new MoneyChange(coins, expected);
        System.out.println("最少找零数量：" + moneyChange.change());
    }

    @Test
    public void testLevenshteinCharMatch(){
        String str1 = "mitcmu";
        String str2 = "mtacnu";
        LevenshteinCharDiff instance = new LevenshteinCharDiff(str1, str2);
        System.out.println("两个字符串的莱文斯坦距离为："+instance.diffCount());
    }

    @Test
    public void testFullArray() {
        char[] arr = new char[]{'1', '2', '3', '4', '5', '6'};
        FullArray.perm(arr, 0, 5);
    }
}
