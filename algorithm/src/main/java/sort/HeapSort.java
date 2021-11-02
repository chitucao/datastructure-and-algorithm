package sort;

/**
 * @author DennyFly
 * @since 2021/11/2 16:14
 * 堆排序
 * 数组的0下标不存储数据
 * <p>
 * 基本操作
 * 1.heapSort1      堆排序,考虑第一个元素为空的情况
 * 2.heapSort2      考虑第一个元素
 * <p>
 * 内部方法
 * 1.buildHeap  建堆
 * 2.heapfiy    堆化
 * 3.swap       交换两个索引元素的位置
 */
public class HeapSort {


    public static void heapSort1(int[] arr) {
        buildHeap1(arr);
        int k = arr.length - 1;
        while (k > 1) {
            swap(arr, 1, k);
            k--;
            heapfiy1(arr, k, 1);
        }
    }

    public static void heapSort2(int[] arr) {
        buildHeap2(arr);
        int k = arr.length - 1;
        while (k > 0) {
            swap(arr, 0, k);
            k--;
            heapfiy2(arr, k, 0);
        }
    }

    // 建堆 数组第一个下标元素不考虑
    private static void buildHeap1(int[] arr) {
        // 从n/2从后向前到1，依次siftDown
        for (int i = arr.length / 2; i >= 1; i--) {
            heapfiy1(arr, arr.length, i);
        }
    }

    // 堆化 siftDown操作
    private static void heapfiy1(int[] arr, int length, int parent) {
        while (true) {
            int maxPos = parent;
            int leftChild = 2 * parent;
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

    // 建堆2 考虑第一个元素
    private static void buildHeap2(int[] arr) {
        // 从n/2-1从后向前到0，依次siftDown
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapfiy2(arr, arr.length, i);
        }
    }

    // 堆化2 siftDown操作
    private static void heapfiy2(int[] arr, int length, int parent) {
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
