package top.chitucao.algorithm.Sort;

/**
 * @author DennyFly
 * @since 2021/10/25 16:48
 * 快速排序
 * 包含分区O(n)的过程,每次选择的分区元素会处于正确的位置，并且前面分区的元素都是比他小的，后面分区的元素都是比他大的
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(logn)
 * 原地排序
 * 非稳定的排序算法（涉及交换操作，可能会导致相同元素(被交换了)的相对顺序发生变化）
 * <p>
 * #思路
 * 选择一个分区，找到这个分区的合适位置，然后从这个合适位置向左和向右扩展，分解成子问题
 * 退化到O(n^2)的概率非常小，可以选择合适的pivot来避免
 * <p>
 * #内部方法
 * 1.partition1 左右逼近
 * 2.partition2 左右逼近（使用swap操作）
 * 3.partition3 从左排序（左边是已处理空间的位置，找到合适的i位置）
 */
public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int p, int q) {
        if (p >= q) {   // 注意判断边界
            return;
        }

        int point = partition1(arr, p, q);
        quickSort(arr, p, point - 1);
        quickSort(arr, point + 1, q);
    }


    // 这里选择p作为分区节点，循环完成后最终保证p元素处于正确的排序位置
    private static int partition1(int[] arr, int p, int q) {
        int pivot = arr[p];
        while (p < q) {
            while (p < q && arr[q] >= pivot) {  // 注意判断边界
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

    /**
     * p到i-1为已处理区间，i为已处理区间的末尾(即分区元素)，i+1到q为未处理区间
     * 时间复杂度O(n)
     *
     * @return 最终i即为正确索引位置的元素，值就是我们选择的pivot
     */
    private static int partition3(int[] arr, int p, int q) {
        // 取右边元素作为比较分区的值
        int pivot = arr[q];

        // i表示已处理区间的末尾（i<=j），最终i元素之前的元素都小于pivot
        int i = p;
        for (int j = p; j < q; j++) {   // 因为q作为分区元素，所以这里是小于
            if (arr[j] < pivot) {   // 注意判断边界
                // 如果j处的元素小于pivot，将j处的元素放到已处理元素的位置，相应的已处理元素的末尾i+1
                swap(arr, i, j);
                i++;
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
