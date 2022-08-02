package top.chitucao.algorithm.T05_Dynamic_Programing;

import top.chitucao.algorithm.T04_Back_Tracking.Pkg;

/**
 * @author DennyFly
 * @since 2021/11/15 17:26
 * 01背包问题（基于动态规划）
 * #复杂度#
 * 时间复杂度 O(n*w)
 * 虽然时间复杂度比较低，但是空间复杂度比较高，需要额外申请一个 n 乘以 w+1 的二维数组
 * <p>
 * #优化思路#
 * 使用一个一维数组（w+1长度）优化空间复杂度 {@link }
 */
public class PackageValue2 {

    /**
     * 物品清单
     */
    public Pkg[] items;

    /**
     * 最大数量限制
     */
    public int maxNum;

    /**
     * 最大重量限制
     */
    public int maxWeight;

    /**
     * 背包价值二维数组
     * 下标1 -> 总数量
     * 下标2 -> 总重量
     * 值 -> 累计价值
     */
    public int[][] status;

    public PackageValue2(Pkg[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;

        status = new int[maxNum][maxWeight + 1];
        for (int i = 0; i < maxNum; i++) {
            for (int j = 0; j < maxWeight + 1; j++) {
                status[i][j] = -1;
            }
        }
    }

    public int countMaxValue() {
        // 初始化第一个元素
        status[0][0] = 0;
        status[0][items[0].getWeight()] = items[0].getValue();

        // 动态规划状态转移，跳过第一个元素
        for (int i = 1; i < maxNum; i++) {
            // 不放入第i个元素
            for (int j = 0; j <= maxWeight; j++) {
                if (status[i - 1][j] >= 0) {
                    status[i][j] = status[i - 1][j];
                }
            }
            // 放入第i个元素
            for (int j = 0; j <= maxWeight - items[i].getWeight(); j++) {
                if (status[i - 1][j] >= 0) {
                    int value = status[i - 1][j] + items[i].getValue();
                    if (value > status[i][j + items[i].getWeight()]) {
                        status[i][j + items[i].getWeight()] = value;
                    }
                }
            }
        }

        // 找出最大值
        int maxValue = -1;
        for (int i = 0; i <= maxWeight; i++) {
            if (status[maxNum - 1][i] > maxValue) {
                maxValue = status[maxNum - 1][i];
            }
        }
        return maxValue;
    }
}
