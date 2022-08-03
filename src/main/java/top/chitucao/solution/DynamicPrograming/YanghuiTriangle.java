package top.chitucao.solution.DynamicPrograming;

/**
 * @author DennyFly
 * @since 2021/11/16 14:50
 * 杨辉三角最短路径（基于动态规划实现）
 */
public class YanghuiTriangle {

    /**
     * 杨辉三角数据
     */
    private int[][] data;

    /**
     * 高度
     * （rowLen-1）对应第一行元素列的下标
     */
    private int rowLen;

    /**
     * 宽度
     */
    private int cloumnLen;

    /**
     * 动态规划状态
     * 一维：行
     * 二维：列
     * 值：路径权重和
     */
    private int[][] status;


    public YanghuiTriangle(int[][] data) {
        this.data = data;
        rowLen = data.length;
        cloumnLen = data[data.length - 1].length;
        status = new int[rowLen][cloumnLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < cloumnLen; j++) {
                status[i][j] = -1;
            }
        }
        // 初始化第一个节点
        status[0][rowLen - 1] = data[0][rowLen - 1];
    }

    public void navigate() {
        for (int i = 1; i < rowLen; i++) {
            for (int j = 0; j < cloumnLen; j++) {
                // 找到上一个节点
                if (status[i - 1][j] > 0) {
                    // 设置左节点的值
                    int leftValue = status[i - 1][j] + data[i][j - 1];
                    if (status[i][j - 1] == -1) {
                        // 如果没有设置则直接设置
                        status[i][j - 1] = leftValue;
                    } else {
                        // 如果已经设置过则判断设置最小值
                        // 这里只需要设置一次就行了，左节点和右节点都会走一次的
                        if (leftValue < status[i][j - 1]) {
                            status[i][j - 1] = leftValue;
                        }
                    }

                    // 设置右节点的值
                    int rightValue = status[i - 1][j] + data[i][j + 1];
                    if (status[i][j + 1] == -1) {
                        status[i][j + 1] = rightValue;
                    }
                }
            }
        }

        printStatus();

        // 找到最小值
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < cloumnLen; i++) {
            if (status[rowLen - 1][i] < minValue && status[rowLen - 1][i] != -1) {
                minValue = status[rowLen - 1][i];
            }
        }

        // 倒推出路径，存在缺陷，三角形是有左右临边约束的，不像商品问题只有顺序要求
        // 这个倒推要保证同一行的元素值都是不一样的，否则会违反临边约束，倒推相同值时会选择最左边那个数据
        int tempValue = minValue;
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = 0; j < cloumnLen; j++) {
                if (status[i][j] == tempValue) {
                    System.out.print("->" + data[i][j]);
                    tempValue = tempValue - data[i][j];
                    break;
                }
            }
        }
        System.out.println();
    }

    private void printStatus() {
        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[i].length; j++) {
                if (status[i][j] != -1) {
                    System.out.print(status[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
