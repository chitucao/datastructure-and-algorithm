package top.dennyfly.algorithm.sort;

/**
 * @author DennyFly
 * @since 2021/11/2 16:14
 * 堆排序
 * 数组的0下标存储数据
 * 包括建堆O(n)和排序(Onlog(n))过程
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(1)
 * 原地排序
 * 不稳定的排序算法（总是将最后一个元素和堆顶元素互换，可能会改变相同元素的相对顺序）
 * <p>
 * 内部方法
 * 1.buildHeap  建堆
 * 2.heapfiy    堆化
 * 3.swap       交换两个索引元素的位置
 */
public class HeapSort2 {

    public static void heapSort(int[] arr) {
        buildHeap(arr);

        int k = arr.length - 1;
        while (k > 0) {
            swap(arr, 0, k);
            k--;
            heapfiy(arr, k, 0);
        }
    }

    // 建堆 考虑第一个元素
    private static void buildHeap(int[] arr) {
        // 从n/2-1从后向前到0，依次siftDown
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapfiy(arr, arr.length, i);
        }
    }

    // 向下堆化 siftDown操作
    private static void heapfiy(int[] arr, int length, int parent) {
        while (true) {
            int maxPos = parent;
            int leftChild = 2 * parent + 1;
            if (leftChild < length && arr[leftChild] > arr[parent]) {
                maxPos = leftChild;
            }
            // 注意这里是和maxPos比较
            if (leftChild + 1 < length && arr[leftChild + 1] > arr[maxPos]) {
                maxPos = leftChild + 1;
            }
            if (maxPos == parent) {
                break;
            }
            swap(arr, maxPos, parent);
            parent = maxPos;
        }
    }

    private static void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

}
