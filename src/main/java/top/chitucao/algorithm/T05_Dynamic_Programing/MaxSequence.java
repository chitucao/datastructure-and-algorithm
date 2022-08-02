package top.chitucao.algorithm.T05_Dynamic_Programing;

import java.util.Arrays;

/**
 * @author DennyFly
 * @since 2021/11/18 15:46
 * 最大递增子序列的长度 基于动态规划
 * #复杂度#
 * O(n^2)
 */
public class MaxSequence {

    public int countMaxLength(int[] nums) {

        /**
         * 动态规划状态
         * 值：最大自增子序列的长度
         */
        int[] status = new int[nums.length];

        // 初始化第一行数据
        status[0] = 1;

        // 动态规划状态转移
        int allMax = 0;
        System.out.print("-----=-=--");
        for (int i = 1; i < nums.length; i++) {
            // 表示前n个数字中的最大递增数量
            int subMax = 0;
            for (int j = 0; j < i; j++) {
                // 递增约束
                if (nums[j] < nums[i]) {
                    if (status[j] > subMax) {
                        subMax = status[j];
                    }
                }
            }
            System.out.print(subMax + " ");
            status[i] = subMax + 1;
            if (status[i] > allMax) {
                allMax = status[i];
            }
        }

        System.out.println();
        // 打印status
        System.out.println("- status:" + Arrays.toString(status));


        // 倒推出每个元素
        int tempMax = allMax;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (status[i] == tempMax) {
                System.out.print("->" + nums[i]);
                tempMax = tempMax - 1;
            }
        }
        System.out.println();
        return allMax;
    }

}
