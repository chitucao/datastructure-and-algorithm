package top.chitucao.algorithm.BackTracking;

/**
 * @author DennyFly
 * @since 2021/11/17 21:35
 * 硬币找零  基于回溯法
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
     * 找零的最小数量
     */
    private int min;


    public MoneyChange(int[] coins, int expected) {
        this.coins = coins;
        this.expected = expected;
        this.min = -1;
    }

    public int change() {
        return change(0, 0);
    }

    /**
     * 递归
     *
     * @param num      累加硬币数量
     * @param sumValue 累加金额
     * @return 硬币数量
     */
    private int change(int num, int sumValue) {
        if (sumValue == expected) {
            return num;
        }

        for (int i = 0; i < coins.length; i++) {
            if (sumValue + coins[i] <= expected) {
                int curMin = change(num + 1, coins[i] + sumValue);

                if (min == -1) {
                    min = curMin;
                } else {
                    if (curMin < min) {
                        min = curMin;
                    }
                }
            }
        }

        return min;
    }

}
