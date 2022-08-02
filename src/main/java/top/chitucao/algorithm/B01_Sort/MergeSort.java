package top.chitucao.algorithm.B01_Sort;

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
 * #思路
 * 基于分治思想，递归编程技巧，先拆解成子问题，然后合并
 * 涉及到合并两个有序数组，合并两个有序数组的时候可以使用到哨兵的思想简化
 * <p>
 * #内部方法
 * 1.mergeSort  排序一段区间
 * 2.merge      合并两个有序数组
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 用于存放合并数据的临时数组
        int[] tempArr = new int[arr.length];
        sort(arr, tempArr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int[] tempArr, int p, int q) {
        // 下标相等表示同一个元素，也不用比较
        if (p >= q) {
            return;
        }

        int mid = p + ((q - p) >> 1);

        // 分 divide
        sort(arr, tempArr, p, mid);
        sort(arr, tempArr, mid + 1, q);

        // 合 conquer
        // 注意这里mid的取值和下面mid的取值
        mergeTwoSortedArr(arr, tempArr, p, mid + 1, q);
    }

    /**
     * 合并两个有序数组
     */
    private static void mergeTwoSortedArr(int[] arr, int[] tempArr, int p, int mid, int q) {
        int tempIndex = p;
        int tempLength = q - p + 1;
        int pEnd = mid - 1;
        int qStart = mid;


        // 从左到右循环遍历两个有序数组，选择最小值加入临时数组
        while ((p <= pEnd) && (qStart <= q)) {
            if (arr[p] < arr[qStart]) {
                tempArr[tempIndex++] = arr[p++];
            } else {
                tempArr[tempIndex++] = arr[qStart++];
            }
        }

        // 左边未遍历完的部分加入临时数组
        while (p <= pEnd) {
            tempArr[tempIndex++] = arr[p++];
        }

        // 右边未遍历完的部分加入临时数组
        while (qStart <= q) {
            tempArr[tempIndex++] = arr[qStart++];
        }

        // 给原始数组赋值，注意是从q从后向前（不能取q，因为q作为起始点会有++操作）
        for (int i = 0; i < tempLength; i++) {
            arr[q - i] = tempArr[q - i];
        }
    }

}
