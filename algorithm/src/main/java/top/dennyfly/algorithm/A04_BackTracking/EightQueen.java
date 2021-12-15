package top.dennyfly.algorithm.A04_BackTracking;

/**
 * @author DennyFly
 * @since 2021/11/12 17:15
 * 8皇后问题
 * #思路#
 * 这里每一行有8种情况，将问题分解为当前行和剩下的行递归处理
 * 针对当前行的处理逻辑是判断是否满足横边斜边条件
 */
public class EightQueen {

    // 长度
    private static final int SIZE = 8;

    // 棋盘，索引表示行，值表示列
    int[] board = new int[SIZE];

    // 统计解法数量
    public int count = 0;


    public void place() {
        place(0);
    }

    // 放置元素
    private void place(int row) {
        if (row == SIZE) {
            count++;
            printBoard();
            return;
        }
        for (int column = 0; column < SIZE; column++) {
            if (isOK2(row, column)) {
                board[row] = column;
                place(row + 1);
            }
        }
    }

    // 是否满足横向和斜向不冲突的情况
    private boolean isOK1(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;

        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 该列被别的行使用的情况
            if (board[i] == column) {
                return false;
            }

            // 考查左上角
            if (leftUp >= 0 && board[i] == leftUp) {
                return false;
            }

            // 考察右上角
            if (rightUp < SIZE && board[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    // 是否满足横向和斜向不冲突的情况
    private boolean isOK2(int row, int column) {
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 该列被别的行使用的情况
            if (board[i] == column) {
                return false;
            }

            if (Math.abs(row - i) == Math.abs(column - board[i])) {
                return false;
            }
        }
        return true;
    }


    // 打印棋盘
    private void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
