package top.dennyfly.algorithm.B01_Sort;

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
 */
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 遍历未排序区间，从1开始
        for (int i = 1; i < arr.length; i++) {

            int num = arr[i];

            // j表示已排序区间的末尾
            int j = i - 1;

            // 从已排序区间的末尾开始，如果尾部元素大于插入元素，则将该元素后移一位
            for (; j >= 0 && arr[j] > num; j--) {
                arr[j + 1] = arr[j];
            }

            // 在合适的位置插入该元素（这里j可能为-1，所以j+1为0）
            arr[j + 1] = num;
        }
    }
}
