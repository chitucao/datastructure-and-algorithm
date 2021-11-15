package top.dennyfly.algorithm.backtracking;

/**
 * @author DennyFly
 * @since 2021/11/15 13:18
 * 01背包问题（数量和最大重量限制，求最大重量）
 * <p>
 * #条件#
 * 对于每个物品来说，有装入背包与不装入背包两种选择，也就是需要考察每个有加入背包的情况与不加入的情况
 * <p>
 * #解题思路#
 * 我们可以把物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理，选择装进去或者不装进去（只有两种情况），然后再递归地处理剩下的物品。
 * <p>
 * #优化思路#
 * 1.避免递归的重复计算，使用二维数组缓存；
 * 2.
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
     * 背包中物品总重量的最大值
     */
    public int maxW;

    /**
     * 是否已经计算过的缓存
     * i -> index
     * j -> sumW
     */
    public boolean[][] mem;



    public PackageWeight(int[] items, int maxNum, int maxWeight) {
        this.items = items;
        this.maxNum = maxNum;
        this.maxWeight = maxWeight;
        this.maxW = Integer.MIN_VALUE;
        this.mem = new boolean[maxNum][maxWeight];
    }

    public void countMaxWeight() {
        countMaxWeight(0, 0);
    }

    /**
     * 统计能放入的最大重量
     * 1.物品不能分割；
     * 2.放入的物品总数量不能大于maxNum;
     * 3.放入的物品总重量不能大于maxWeight;
     *
     * @param index 当前物品索引
     * @param sumW  累计重量
     */
    private void countMaxWeight(int index, int sumW) {
        // 终止条件 重量或者数量达到上限
        if (index == maxNum || sumW == maxWeight) {
            // 判断这次选择的重量是否比上次的重量大
            if (maxW < sumW) {
                maxW = sumW;
            }
            return;
        }

        // 使用缓存避免重复计算
        if (mem[index][sumW]) {
            return;
        }
        mem[index][sumW] = true;

        // 不装当前，考虑下一个元素
        countMaxWeight(index + 1, sumW);

        // 装入当前，考虑当前元素
        if (sumW + items[index] <= maxWeight) {
            countMaxWeight(index + 1, sumW + items[index]);
        }
    }

}
