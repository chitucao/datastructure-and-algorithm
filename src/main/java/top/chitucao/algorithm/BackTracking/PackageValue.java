package top.chitucao.algorithm.BackTracking;

/**
 * @author DennyFly
 * @since 2021/11/15 13:46
 * 01背包问题（数量和最大重量限制，求最大价值）
 * <p>
 * #条件#
 * 每个物品的重量不同，价值也不相同，在重量不超过背包重量的前提下，让背包的总价值最大化。
 * 对于每个物品来说，有装入背包与不装入背包两种选择，也就是需要考察每个有加入背包的情况与不加入的情况。
 * <p>
 * #思路#
 * 我们可以把物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理，选择装进去或者不装进去（只有两种情况），然后再递归地处理剩下的物品。
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
     * 背包中物品总重量的最大值
     */
    public int maxW;

    /**
     * 背包中物品的总价值的最大值
     */
    public int maxV;

    public PackageValue(Pkg[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;
        this.maxW = Integer.MIN_VALUE;
        this.maxV = Integer.MIN_VALUE;
    }

    public void countMaxValue() {
        countMaxValue(0, 0, 0);
    }

    /**
     * 在不超过最大数量和最大重量限制下，求最大价值
     *
     * @param index     物品索引
     * @param sumW      累计重量
     * @param sumV      累计价值
     */
    private void countMaxValue(int index, int sumW, int sumV) {
        // 终止条件，达到最大数量或最大重量
        if (index == maxNum || sumW == maxWeight) {
            // 判断是否比上一个条件优秀
            if (sumW > maxW) {
                maxW = sumW;
            }
            if (sumV > maxV) {
                maxV = sumV;
            }
            return;
        }

        // 不装当前，考虑下一个元素
        countMaxValue(index + 1, sumW, sumV);

        // 装入当前，考虑当前元素
        Pkg curItem = items[index];
        if (sumW + curItem.getWeight() <= maxWeight) {
            countMaxValue(index + 1, sumW + curItem.getWeight(), sumV + curItem.getValue());
        }
    }

}
