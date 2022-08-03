package top.chitucao.solution.DynamicPrograming;

/**
 * @author DennyFly
 * @since 2021/11/16 9:28
 * 双11凑单满减（基于动态规划）
 * #条件#
 * 购物车中有n件商品；
 * 现有满200减50的券；
 * 选择最小的满足于200的商品，尽可能的薅羊毛；
 */
public class Double11 {

    /**
     * 商品价格清单
     */
    private int[] prices;

    /**
     * 商品数量（价格清单的长度）
     */
    private int itemNum;


    /**
     * 满减条件
     **/
    private int condition;

    /**
     * 最大消费金额
     */
    private int maxBalance;

    /**
     * 动态规划状态二维数组
     * 索引1：购买数量
     * 索引2：消费金额
     */
    private boolean[][] status;


    public Double11(int[] prices, int condition) {
        this.prices = prices;
        this.itemNum = prices.length;
        this.condition = condition;
        // 这里设置最大消费金额为满减条件的三倍，再大的话薅羊毛就没有意义了
        this.maxBalance = 3 * condition;
        this.status = new boolean[itemNum][maxBalance + 1];
    }

    public void shopping() {
        // 初始化第一个节点
        status[0][0] = true;
        status[0][prices[0]] = true;

        // 动态规划状态转移
        for (int i = 1; i < itemNum; i++) {
            // 不购买第i个商品
            for (int j = 0; j <= maxBalance; j++) {
                if (status[i - 1][j]) {
                    status[i][j] = true;
                }
            }
            // 购买第i个商品
            for (int j = 0; j <= maxBalance - prices[i]; j++) {
                if (status[i - 1][j]) {
                    status[i][j + prices[i]] = true;
                }
            }
        }

        // 输出消费的最小金额，从左向右
        int p;
        for (p = condition; p <= maxBalance; p++) {
            if (status[itemNum - 1][p]) {
                break;
            }
        }
        // 没有找到可行解
        if (p == maxBalance + 1) {
            return;
        }

        // 打印购买清单，从右向左
        for (int i = itemNum - 1; i >= 1; i--) {
            if (p - prices[i] >= 0 && status[i - 1][p - prices[i]]) {
                // 表示购买这个商品
                System.out.print(prices[i] + " ");
                p = p - prices[i];
            }
            // else 表示不购买这个商品，p不变，只改变i
        }
        // 打印第一个商品
        if (p != 0) {
            System.out.print(prices[0]);
        }
    }
}
