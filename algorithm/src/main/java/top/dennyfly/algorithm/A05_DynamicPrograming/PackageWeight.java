package top.dennyfly.algorithm.A05_DynamicPrograming;

/**
 * @author DennyFly
 * @since 2021/11/15 16:21
 * 01背包问题（基于动态规划）
 * #复杂度#
 * 时间复杂度 O(n*w)
 * 虽然时间复杂度比较低，但是空间复杂度比较高，需要额外申请一个 n 乘以 w+1 的二维数组
 * <p>
 * #优化思路#
 * 使用一个一维数组（w+1长度）优化空间复杂度 {@link PackageWeight2}
 */
public class PackageWeight {

    /**
     * 物品清单
     */
    public int[] items;

    /**
     * 最大数量限制
     */
    public int maxNum;

    /**
     * 最大重量限制
     */
    public int maxWeight;

    /**
     * 背包状态二维数组
     * 下标1 -> 总数量
     * 下标2 -> 总重量
     */
    private boolean[][] states;

    public PackageWeight(int[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;
        this.states = new boolean[maxNum][maxWeight + 1];

    }

    public int countMaxWeight() {
        // 第一行的元素初始化
        states[0][0] = true;
        states[0][items[0]] = true;

        // 动态规划状态转移
        for (int i = 1; i < maxNum; i++) {
            // 不放入第i个元素
            for (int j = 0; j <= maxWeight; j++) {
                if (states[i - 1][j] == true) {
                    states[i][j] = true;
                }
            }
            // 放入第i个元素
            for (int j = 0; j <= maxWeight - items[i]; j++) {
                if (states[i - 1][j] == true) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        // 物品个数的最后一行，重量从大到小遍历
        for (int i = maxWeight; i >= 0; i--) {
            if (states[maxNum - 1][i] == true) {
                return i;
            }
        }

        return 0;
    }


}
