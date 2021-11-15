package top.dennyfly.algorithm.backtracking;

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
    public void testPackage() {
        int[] items = new int[]{10, 20, 30, 40, 35, 45, 55, 75};

        PackageWeight pkg = new PackageWeight();
        pkg.countMaxWeight(0, 0, items, 4, 25);
        System.out.println(pkg.maxW);
    }

    @Test
    public void testPackageValue() {
        Pkg[] pkgs = new Pkg[5];

        pkgs[0] = new Pkg("苹果", 10, 100);
        pkgs[1] = new Pkg("香蕉", 20, 80);
        pkgs[2] = new Pkg("香蕉", 30, 120);
        pkgs[3] = new Pkg("菠萝", 25, 125);
        pkgs[4] = new Pkg("橙子", 15, 160);

        PackageValue instance = new PackageValue();

        instance.countMaxValue(pkgs, 5, 50);
        System.out.println("最大总价值为 ：" + instance.maxV);
        System.out.println("最大总重量为 ：" + instance.maxW);
    }

    @Test
    public void testSudoku(){
        Sudoku sudoku = new Sudoku(9, SUDUKU);
//        Sudoku sudoku = new Sudoku(9);
        sudoku.place();
        sudoku.printData();
    }

}
