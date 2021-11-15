package top.dennyfly.algorithm.sort;

/**
 * @author DennyFly
 * @since 2021/10/25 13:11
 * 插入排序
 * 分为已排序区间和未排序区间，遍历未排序区间的元素在已排序区间找到对应的位置插入
 * #外层遍历n-1轮，空间复杂度O(1)，原地排序，稳定的排序算法
 * #最好O(n)（已经排序好，无需找到插入位置）、最坏(O(n^2))、平均(O(n^2))
 */
public class InsertionSort {
    public static void insertionSort1(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        // 遍历未排序区间，从1开始
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];

            // 在已排序区间找到相应位置插入
//            int j = i - 1;
//            for (; j >= 0; j--) {
//                if (arr[j] > value) {
//                    arr[j + 1] = arr[j];
//                } else {
//                    break;
//                }
//            }

            int j = i - 1;
            for (; j >= 0 && arr[j] > value; j--) {
                // 注意这里是value的索引，也就是j+1
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = value;
        }

    }
}
