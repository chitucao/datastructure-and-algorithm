package top.chitucao.algorithm.DynamicPrograming;

/**
 * @author DennyFly
 * @since 2021/11/17 21:35
 * 硬币找零  基于动态规划
 * #这里的硬币问题对比背包问题其实是三个维度
 */
public class MoneyChange {

    /**
     * 硬币类型
     */
    private int[] coins;

    /**
     * 期望找零的面值
     */
    private int expected;

    /**
     * 最大的遍历次数（全是找零最小面值的）
     */
    private int maxLevel;

    /**
     * 动态规划状态矩阵
     * 维度1：找零次数
     * 维度2：累加金额
     * 值：当前位置的面额
     */
    private int[][] status;


    public MoneyChange(int[] coins, int expected) {
        this.coins = coins;
        this.expected = expected;
        this.maxLevel = expected / coins[0];
        this.status = new int[maxLevel][expected + 1];

        for (int i = 0; i < maxLevel; i++) {
            for (int j = 0; j < expected + 1; j++) {
                status[i][j] = -1;
            }
        }
    }

    public int change() {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        if (expected < 1) {
            return -1;
        }

        int length = coins.length;

        // 初始化第一层数据
        for (int i = 0; i < length; i++) {
            status[0][coins[i]] = coins[i];
        }

        int minNum = -1;

        // 动态规划状态转移
        for (int i = 1; i < maxLevel; i++) {
            for (int j = 0; j < expected; j++) {
                if (status[i - 1][j] != -1) {
                    // 遍历每种硬币情况
                    for (int k = 0; k < length; k++) {
                        if (j + coins[k] <= expected) {
                            status[i][j + coins[k]] = coins[k];
                        }
                    }
                }

                if (status[i][expected] >= 0) {
                    minNum = i + 1;
                    break;
                }
            }

            if (minNum > 0) {
                break;
            }
        }

        printStatus();

        // 反推出币的组合
        int tmpMoney = expected;
        for (int i = minNum - 1; i >= 0; i--) {
            for (int j = expected; j >= 0; j--) {
                if (j == tmpMoney) {
                    System.out.println("当前值为：" + status[i][j]);
                    tmpMoney = tmpMoney - status[i][j];
                    break;
                }
            }
        }

        return minNum;
    }

    private void printStatus() {
        for (int i = 0; i <= status.length; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[i].length; j++) {
                if (status[i][j] != -1) {
                    System.out.print(status[i][j]+ " ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

}
