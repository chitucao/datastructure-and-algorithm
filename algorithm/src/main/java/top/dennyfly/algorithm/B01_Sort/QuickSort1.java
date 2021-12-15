package top.dennyfly.algorithm.B01_Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 16:48
 * 快速排序
 * 包含分区O(n)的过程,每次选择的分区元素会处于正确的位置，并且前面分区的元素都是比他小的，后面分区的元素都是比他大的
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(1)
 * 原地排序
 * 非稳定的排序算法（涉及交换操作，可能会导致相同元素的相对顺序发生变化）
 * <p>
 * #思路#
 * 由上到下，先分区，再处理子问题
 * 退化到O(n^2)的概率非常小，可以选择合适的pivot来避免
 */
public class QuickSort1 {

    public static void quickSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int p, int q) {
        // 注意这里不能等于
        if (p >= q) {
            return;
        }

        int point = partition1(arr, p, q);
//        int point = partition2(arr, p, q);
        quickSort(arr, p, point - 1);
        quickSort(arr, point + 1, q);
    }


    // 这里选择p作为分区节点，循环完成后最终保证p元素处于正确的索引位置
    private static int partition1(int[] arr, int p, int q) {
        int pivot = arr[p];
        while (p < q) {
            while (p < q && arr[q] >= pivot) {
                q--;
            }
            arr[p] = arr[q];
            while (p < q && arr[p] <= pivot) {
                p++;
            }
            arr[q] = arr[p];
        }
        arr[p] = pivot;
        return p;
    }

    // 这里选择p作为分区节点，循环完成后最终保证p元素处于正确的索引位置
    private static int partition2(int[] arr, int p, int q) {
        int pivot = arr[p];
        while (p < q) {
            while (p < q && arr[q] >= pivot) {
                q--;
            }
            swap(arr, p, q);
            while (p < q && arr[p] <= pivot) {
                p++;
            }
            swap(arr, p, q);
        }
        return p;
    }

    // 交换两个元素的位置
    private static void swap(int[] arr, int p, int q) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    }
}
