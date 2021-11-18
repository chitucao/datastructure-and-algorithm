package top.dennyfly.algorithm.dynamicprograming;

/**
 * @author DennyFly
 * @since 2021/11/17 20:52
 * 矩阵求最短路径 （基于动态规划，状态转移方程法(递归+备忘录)）
 */
public class MatrixDistance2 {

    /**
     * 矩阵数据
     */
    private int[][] data;

    /**
     * 长度
     */
    private int length;

    /**
     * 宽度
     */
    private int width;

    /**
     * 动态规划状态
     * 一维：行
     * 二维：列
     * 值：最短值
     */
    private int[][] status;

    public MatrixDistance2(int[][] data) {
        this.data = data;
        this.width = data[data.length - 1].length;
        this.length = data.length;
        this.status = new int[width][length];
    }

    public int navigate() {
        return navigate(width - 1, length - 1);
    }

    private int navigate(int i, int j) {
        if (i == 0 && j == 0) {
            return data[0][0];
        }

        // 状态矩阵中对应位置已经有数据
        if (status[i][j] > 0) {
            return status[i][j];
        }

        // 要得到最终解只有两条路，正推为i-1,j，或者i,j-1
        // 左边最小元素
        int minLeft = Integer.MAX_VALUE;
        if (j >= 1) {
            minLeft = navigate(i, j - 1);
        }

        // 上边最小元素
        int minUp = Integer.MAX_VALUE;
        if (i >= 1) {
            minUp = navigate(i - 1, j);
        }

        int minCur = data[i][j] + Math.min(minLeft, minUp);
        status[i][j] = minCur;
        return minCur;
    }
}
