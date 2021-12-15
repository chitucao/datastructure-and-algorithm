package top.dennyfly.algorithm.B01_Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 16:48
 * 快速排序
 * 包含分区O(n)的过程,每次选择的分区元素会处于正确的位置
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(1)
 * 原地排序
 * 非稳定的排序算法
 * <p>
 * #思路#
 * 由上到下，先分区，再处理子问题
 * 退化到O(n^2)的概率非常小，可以选择合适的pivot来避免
 */
public class QuickSort2 {

    public static void quickSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int p, int q) {
        // 注意这里是>=
        if (p >= q) {
            return;
        }
        int point = partition(arr, p, q);
        quickSort(arr, p, point - 1);
        quickSort(arr, point + 1, q);
    }

    /**
     * p到i-1为已处理区间，i为已处理区间的末尾，i+1到q为未处理区间
     * 时间复杂度O(n)
     *
     * @return 最终i即为正确索引位置的元素，值就是我们选择的pivot
     */
    private static int partition(int[] arr, int p, int q) {
        int pivot = arr[q];

        // i表示已处理区间的末尾（i<=j），最终i元素之前的元素都小于pivot
        int i = p;
        for (int j = p; j < q; j++) {
            if (arr[j] < pivot) {
                // 如果j处的元素小于pivot，将j处的元素放到已处理元素的位置，相应的已处理元素的末尾i+1
                swap(arr, i, j);
                i = i + 1;
            }
        }

        // 最后将分区元素放到已处理区间的末尾，那么这个分区元素就是处于正确的位置了（前面的元素都小于他）
        swap(arr, i, q);
        return i;
    }

    // 交换两个元素的位置
    private static void swap(int[] arr, int p, int q) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    }
}
