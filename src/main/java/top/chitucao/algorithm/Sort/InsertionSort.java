package top.chitucao.algorithm.Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 13:11
 * 插入排序
 * #思路
 * 分为已排序区间（前）和未排序区间（后），遍历未排序区间的元素(外层)在已排序区间（从后向前遍历）找到对应的位置插入
 * <p>
 * 时间复杂度O(n^2)  最好O(n)（已经排序好，无需找到插入位置）、最坏(O(n^2))、平均(O(n^2))
 * 空间复杂度O(1)
 * 原地排序
 * 稳定的排序算法
 * <p>
 * #要点
 * 1、外层从1开始遍历；
 * 2、处理区间从i-1开始向前遍历移动，记录j；
 * 3、最后交换的是j+1 和 i的元素
 */
public class InsertionSort {
    public static void insertionSort(int[] nums) {

//        if (nums == null || nums.length <= 1) {
//            return;
//        }
//        int n = nums.length;
//
//        for (int i = 1; i < n; i++) {
//            int num = nums[i];  // 待插入元素
//            int j = i - 1;  // 已排序区间的最后位置，待插入元素的前一个元素
//
//            for (; j >= 0 && nums[j] > num; j--) {  // 所有比当前元素大的元素后移一位空出位置，这里比较的核心元素是相邻的后一个元素
//                nums[j + 1] = nums[j];
//            }
//            nums[j + 1] = num;  // 注意j=0并且j--之后j=-1，所以这里使用j+1  // 注意边界
//        }
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int num = nums[i];

            int tail = i - 1;

            for (; tail >= 0 && nums[tail] > num; tail--) {
                nums[tail + 1] = nums[tail];
            }

            nums[tail + 1] = num;
        }

    }
}
