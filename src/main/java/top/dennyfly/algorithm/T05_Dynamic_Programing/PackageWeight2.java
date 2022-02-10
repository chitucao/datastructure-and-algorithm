package top.dennyfly.algorithm.T05_Dynamic_Programing;

/**
 * @author DennyFly
 * @since 2021/11/15 16:21
 * 01背包问题（基于动态规划）
 * #复杂度#
 * 时间复杂度 O(n*w)
 * 虽然时间复杂度比较低，但是空间复杂度比较高，需要额外申请一个 n 乘以 w+1 的二维数组
 * <p>
 * #优化思路#
 * 使用一个一维数组（w+1长度）优化
 */
public class PackageWeight2 {

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
     * 背包状态数组
     * 下标 -> 总重量
     */
    private boolean[] states;

    public PackageWeight2(int[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;
        this.states = new boolean[maxWeight + 1];
    }

    public int countMaxWeight() {
        // 第一行的元素初始化
        states[0] = true;
        states[items[0]] = true;

        // 动态规划状态转移
        // 这里已经组合了所有值的情况，对应最后一行数据的所有情况
        for (int i = 1; i < maxNum; i++) {
            // 放入第i个元素，这里是从大到小排列避免了循环计算
            // 第一次一个值...
            // 第二次组合两个值...
            // 第三次组合三个值...
            // 其实就是一个从后向前组合所有情况的过程，相应的问题规模就是次数x情况数
            for (int j = maxWeight - items[i]; j >= 0; j--) {
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
            }

            // 如果是从前开始，states[j + items[i]]会再计算一次，这里的i其实是不合理的
//            for (int j = 0; j <= maxWeight - items[i]; j++) {
//                if (states[j] == true) {
//                    states[j + items[i]] = true;
//                }
//            }
        }

        // 重量从大到小遍历
        for (int i = maxWeight; i >= 0; i--) {
            if (states[i] == true) {
                return i;
            }
        }

        return 0;
    }
}
