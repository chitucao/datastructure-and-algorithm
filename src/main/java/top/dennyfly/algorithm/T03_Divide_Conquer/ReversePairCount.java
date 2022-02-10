package top.dennyfly.algorithm.T03_Divide_Conquer;

/**
 * @author DennyFly
 * @since 2021/11/12 10:40
 * 借助归并排序的思想求逆序对的个数
 * #这里是统计数组中右边比左边小的元素个数
 * <p>
 * <p>
 * 基本操作
 * 1.count      求逆序对的个数
 * <p>
 * 内部方法
 * 1.mergeSortCounting  拆分计算过程
 * 2.merge              对拆分的数据段进行处理的过程，包含排序和统计逆序对的个数
 */
public class ReversePairCount {

    static int num = 0;

    public static int count(int[] arr) {
        mergeSortCounting(arr, 0, arr.length - 1);
        return num;
    }

    private static void mergeSortCounting(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) / 2;
        mergeSortCounting(arr, p, q);
        mergeSortCounting(arr, q + 1, r);
        merge(arr, p, q, r);
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;

        // 排序后的数组
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                // 统计左边 p~q之间，比arr[j]大的元素
                num += (q - i + 1);
                tmp[k++] = arr[j++];
            }
        }
        while (i <= q) {
            tmp[k++] = arr[i++];
        }
        while (j <= r) {
            tmp[k++] = arr[j++];
        }
        for (int l = 0; l <= r - p; l++) {
            arr[p + l] = tmp[l];
        }
    }
}
