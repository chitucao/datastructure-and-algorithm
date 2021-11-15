package top.dennyfly.algorithm.backtracking;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/11/15 14:25
 * 数独问题
 * #条件#
 * 1.数字 1-9 在每一行只能出现一次。
 * 2.数字 1-9 在每一列只能出现一次。
 * 3.数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * #参考资料#
 * https://blog.csdn.net/tianyaleixiaowu/article/details/50912924
 */
public class Sudoku {

    /**
     * 数独大小
     */
    private int length;

    /**
     * 用一个二维数组来表示数独
     */
    private int[][] data;

    public Sudoku(int length) {
        this(length, new int[length][length]);
    }

    public Sudoku(int length, int[][] data) {
        this.length = length;
        this.data = data;
    }

    @Test
    public void place() {
        place(0, 0);
    }

    /**
     * 放置元素
     *
     * @param row    行
     * @param column 列
     */
    private void place(int row, int column) {
        // 终止条件
        if (row == length - 1 && column == length) {
            printData();
            return;
        }

        // 列达到末尾，行+1，列回到开始未
        if (column == length) {
            row++;
            column = 0;
        }

        if (data[row][column] == 0) {
            for (int num = 1; num <= 9; num++) {
                if (check(row, column, num)) {
                    // 当前空格
                    data[row][column] = num;

                    // 进入一个空格
                    place(row, column + 1);

                    // 如果回头，需要给当前空格格式化
                    data[row][column] = 0;
                } else {
                    place(row, column + 1);
                }
            }
        }

    }

    /**
     * 校验是否满足数独条件
     */
    private boolean check(int row, int column, int num) {
        // 每一行每一列只能出现一次
        for (int i = 0; i < length; i++) {
            if (data[row][i] == num || data[i][column] == num) {
                return false;
            }
        }

        // 3x3 宫格中只能出现一次
        int startRow = row / 3;
        int startColumn = column / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (data[startRow * 3 + i][startColumn * 3 + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 打印数独
     */
    public void printData() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
