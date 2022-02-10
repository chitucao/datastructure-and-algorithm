package top.dennyfly.algorithm.B01_Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 13:50
 * 选择排序
 * 分为已排序区间和未排序区间，遍历未排序区间找到最小元素放到已排序区间的末尾
 * <p>
 * 时间复杂度O(n^2)  最好O(n^2)、最坏(O(n^2))、平均(O(n^2))
 * 空间复杂度O(1)
 * 原地排序
 * 不稳定的排序算法(例如 5，8，5，2，9，第一个5会和2交换位置，导致相对顺序发生变化)
 */
public class SelectionSort {

    public static void selectionSort1(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        // 共遍历n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            // 当前下标定义为最小元素下标
            int min = i;
            // 找到未排序部分最小元素
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            // 交换当前元素和最小元素
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }

    // 交换两个元素的位置
    private static void swap(int[] arr, int p, int q) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    }

}
