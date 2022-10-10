package top.chitucao.algorithm.DynamicPrograming;

import top.chitucao.algorithm.BackTracking.Pkg;

/**
 * @author DennyFly
 * @since 2021/11/15 17:26
 * 01背包问题（基于动态规划）
 * #复杂度#
 * 时间复杂度 O(n*w)
 * 空间复杂度 O(w)
 */
public class PackageValue {

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
    public int[] status;

    public PackageValue(Pkg[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;

        status = new int[maxWeight + 1];
        for (int i = 0; i < maxNum; i++) {
            status[i] = -1;
        }
    }

    public int countMaxValue() {
        // 初始化第一个元素
        status[0] = 0;
        status[items[0].getWeight()] = items[0].getValue();

        // 动态规划状态转移，跳过第一个元素
        for (int i = 1; i < maxNum; i++) {
            // 放入第i个元素
            for (int j = maxWeight - items[i].getWeight(); j >= 0; j--) {
                if (status[j] >= 0) {
                    int value = status[j] + items[i].getValue();
                    if (value > status[j + items[i].getWeight()]) {
                        status[j + items[i].getWeight()] = value;
                    }
                }
            }
        }

        // 找出最大值
        int maxValue = -1;
        for (int i = 0; i <= maxWeight; i++) {
            if (status[i] > maxValue) {
                maxValue = status[i];
            }
        }
        return maxValue;
    }


}
