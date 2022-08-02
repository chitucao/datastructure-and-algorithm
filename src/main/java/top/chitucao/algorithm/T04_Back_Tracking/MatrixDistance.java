package top.chitucao.algorithm.T04_Back_Tracking;

/**
 * @author DennyFly
 * @since 2021/11/17 19:58
 * 矩阵求最短路径 （基于回溯算法）
 */
public class MatrixDistance {

    /**
     * 最短路径
     */
    public int minDistance;

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

    public MatrixDistance(int[][] data) {
        this.minDistance = Integer.MAX_VALUE;
        this.data = data;
        this.width = data[data.length - 1].length;
        this.length = data.length;
    }

    public void navigate() {
        navigate(0, 0, 0);
    }

    private void navigate(int i, int j, int sumDistance) {
        if (i == length - 1 && j == width - 1) {
            // 注意加上最后一个元素的长度
            sumDistance = sumDistance + data[i][j];
            if (sumDistance < minDistance) {
                minDistance = sumDistance;
            }
            return;
        }

        // 向下
        if (j < width - 1) {
            navigate(i, j + 1, sumDistance + data[i][j]);
        }

        // 向右
        if (i < length - 1) {
            navigate(i + 1, j, sumDistance + data[i][j]);
        }
    }
}
