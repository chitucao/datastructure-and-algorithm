package top.chitucao.solution.DynamicPrograming;

/**
 * @author DennyFly
 * @since 2021/11/17 20:52
 * 矩阵求最短路径 （基于动态规划，状态转移表法）
 */
public class MatrixDistance1 {

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

    public MatrixDistance1(int[][] data) {
        this.data = data;
        this.width = data[data.length - 1].length;
        this.length = data.length;
        this.status = new int[width][length];
    }

    public int navigate() {
        // 初始化第一列数据
        int sum = 0;
        for (int i = 0; i < width; i++) {
            sum += data[i][0];
            status[i][0] = sum;
        }

        // 初始化第一行数据
        sum = 0;
        for (int j = 0; j < length; j++) {
            sum += data[0][j];
            status[0][j] = sum;
        }

        for (int i = 1; i < width; i++) {
            for (int j = 1; j < length; j++) {
                status[i][j] = data[i][j] + Math.min(status[i][j - 1], status[i - 1][j]);
            }
        }
        return status[length - 1][width - 1];
    }
}
