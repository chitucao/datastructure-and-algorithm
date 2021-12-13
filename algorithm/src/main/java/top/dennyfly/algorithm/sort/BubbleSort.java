package top.dennyfly.algorithm.sort;

/**
 * @author DennyFly
 * @since 2021/10/25 10:23
 * 冒泡排序
 * 冒泡的顶端（末端）元素是已排序部分，外层共需要遍历n-1轮（两两交换的次数）
 * <p>
 * #特性#
 * 时间复杂度O(n^2)   最好O(n)（已经排序好，无需交换位置）、最坏(O(n^2))、平均(O(n^2))
 * 空间复杂度O(1)
 * 原地排序
 * 稳定的排序算法
 * <p>
 * #算法优化#
 * 1.增加标识，用于提前判断是否完全有序的；
 * <p>
 * #内部方法#
 * 1.swap   交换两个元素的位置
 */
public class BubbleSort {

    /**
     * 基本的冒泡排序 O(n^2)
     * 从小到大排序，相邻比较，执行数组的大小n次
     */
    public static void bubbleSort1(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
//        System.out.println("执行冒泡的第" + (i + 1) + "次");
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 优化提前退出的冒泡排序 O(n^2)
     * 每次冒泡前增加是否完全有序(根据是否有数据交换)的标识，单次冒泡结束后判断该标识决定是否提前跳出循环
     * 因为每次循环加了是否可以提前退出的判断，也许可能会更慢
     */
    public static void bubbleSort2(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
//            System.out.println("执行冒泡的第" + (i + 1) + "次");
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
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
