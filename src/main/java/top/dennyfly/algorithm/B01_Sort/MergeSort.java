package top.dennyfly.algorithm.B01_Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 15:08
 * 归并排序
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(n)
 * 非原地排序
 * 稳定的排序算法
 * <p>
 * #思路#
 * 基于分治思想，递归编程技巧
 * 由下到上，先解决子问题再合并
 * 涉及到合并两个有序数组，合并两个有序数组的时候可以使用到哨兵的思想简化
 */
public class MergeSort {

    // 归并排序
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int[] tempArr = new int[arr.length];
        mergeSort(arr, tempArr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] tempArr, int p, int q) {
        // 注意这里是>=
        if (p >= q) {
            return;
        }
        int mid = p + (q - p) / 2;
        mergeSort(arr, tempArr, p, mid);
        mergeSort(arr, tempArr, mid + 1, q);
        // 注意这里是mid+1，如果是不加1，可能会导致下面代码mid<p=q的情况
        merge(arr, tempArr, p, mid + 1, q);
    }

    // 合并两个有序数组
    private static void merge(int[] arr, int[] tempArr, int p, int mid, int q) {
        int tempIndex = p;
        int tempLength = q - p + 1;
        int pEnd = mid - 1;
        int qStart = mid;

        // 遍历两个原始数组插入到临时数组，直到其中一个原始数组元素迭代完
        while ((p <= pEnd) && (qStart <= q)) {
            if (arr[p] < arr[qStart]) {
                tempArr[tempIndex++] = arr[p++];
            } else {
                tempArr[tempIndex++] = arr[qStart++];
            }
        }

        // 左边原始数组未遍历完的情况
        while (p <= pEnd) {
            tempArr[tempIndex++] = arr[p++];
        }

        // 右边原始数组未遍历完的情况
        while (qStart <= q) {
            tempArr[tempIndex++] = arr[qStart++];
        }

        // 给原始数组赋值，注意是从q从后向前
        // 这里为什么需要反过来，一直很不理解，是不是因为tempIndex没有增加
        for (int i = 0; i < tempLength; i++) {
            arr[q - i] = tempArr[q - i];
        }
    }

}
