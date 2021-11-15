package top.dennyfly.algorithm.sort;

/**
 * @author DennyFly
 * @since 2021/10/25 16:48
 * 快速排序
 * #时间复杂度O(nlogn)，非稳定的排序算法，空间复杂度O(1)，原地排序
 * #原地排序，非稳定排序
 * #由上到下，先分区，再处理子问题
 * #退化到O(n^2)的概率非常小，可以选择合适的pivot来避免
 */
public class QuickSort {

    public static void quickSort1(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort1(arr, 0, arr.length - 1);
    }

    public static void quickSort2(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort1(int[] arr, int p, int q) {
        // 注意这里不能等于
        if (p >= q) {
            return;
        }
        int point = partition1(arr, p, q);
        quickSort1(arr, p, point - 1);
        quickSort1(arr, point + 1, q);
    }

    private static void quickSort2(int[] arr, int p, int q) {
        // 注意这里不能等于
        if (p >= q) {
            return;
        }
        int point = partition3(arr, p, q);
        quickSort1(arr, p, point - 1);
        quickSort1(arr, point + 1, q);
    }

    private static int partition1(int[] arr, int p, int q) {
        // 这里选择p作为分区节点，循环完成后最终保证p元素处于正确的索引位置
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

    private static int partition2(int[] arr, int p, int q) {
        // 这里选择p作为分区节点，循环完成后最终保证p元素处于正确的索引位置
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

    // p到i-1为已处理区间，i为已处理区间的末尾，i+1到q为未处理区间
    private static int partition3(int[] arr, int p, int q) {
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

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}
