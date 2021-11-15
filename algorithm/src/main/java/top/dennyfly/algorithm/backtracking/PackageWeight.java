package top.dennyfly.algorithm.backtracking;

/**
 * @author DennyFly
 * @since 2021/11/15 13:18
 * 01背包问题（数量和最大重量限制，求最大重量）
 * <p>
 * #条件#
 * 对于每个物品来说，有装入背包与不装入背包两种选择，也就是需要考察每个有加入背包的情况与不加入的情况
 * <p>
 * #思路#
 * 我们可以把物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理，选择装进去或者不装进去（只有两种情况），然后再递归地处理剩下的物品。
 */
public class PackageWeight {

    /**
     * 背包中物品总重量的最大值
     */
    public int maxW = Integer.MIN_VALUE;

    /**
     * 统计能放入的最大重量
     * 1.物品不能分割；
     * 2.放入的物品总数量不能大于maxNum;
     * 3.放入的物品总重量不能大于maxWeight;
     *
     * @param index     当前物品索引
     * @param sumW      累计重量
     * @param items     物品清单
     * @param maxNum    最大数量限制
     * @param maxWeight 最大重量限制
     */
    public void countMaxWeight(int index, int sumW, int[] items, int maxNum, int maxWeight) {
        // 终止条件 重量或者数量达到上限
        if (index == maxNum || sumW == maxWeight) {
            // 判断这次选择的重量是否比上次的重量大
            if (maxW < sumW) {
                maxW = sumW;
            }
            return;
        }

        // 处理下一个问题
        countMaxWeight(index + 1, sumW, items, maxNum, maxWeight);

        // 处理当前问题
        if (sumW + items[index] <= maxWeight) {
            countMaxWeight(index + 1, sumW + items[index], items, maxNum, maxWeight);
        }
    }

}
