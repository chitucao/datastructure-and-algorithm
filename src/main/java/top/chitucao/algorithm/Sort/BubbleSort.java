package top.chitucao.algorithm.Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 10:23
 * 冒泡排序
 * 写法1：基本写法
 * 写法2：
 * #思路
 * 内层循环相邻比较，外层共比较n-1轮
 * 冒泡的顶端（末端）元素是已排序部分
 * <p>
 * #特性
 * 时间复杂度O(n^2)   最好O(n)（已经排序好，无需交换位置）、最坏(O(n^2))、平均(O(n^2))
 * 空间复杂度O(1)
 * 原地排序
 * 稳定的排序算法
 * <p>
 * #优化
 * 1.增加标识，用于提前判断是否完全有序的；
 * <p>
 * #内部方法
 * 1.swap   交换两个元素的位置
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr){
        bubbleSort2(arr);
    }

    /**
     * 基本写法
     * 从小到大排序，相邻比较，执行数组的大小n次
     */
    public static void bubbleSort1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {          // 两两相邻比较，最多比较n-1次
            for (int j = 0; j < arr.length - i - 1; j++) {  // 比较的元素从后向前越来越少
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 优化1
     * #思路
     * 外层循环中增加是否完全有序的标识，如果内层循环没有发生数据交换，说明已经完全有序，可以提前退出外层循环
     * #Tip
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
