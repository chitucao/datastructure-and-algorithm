package top.dennyfly.algorithm.B01_Sort;

/**
 * @author DennyFly
 * @since 2021/11/2 16:14
 * 堆排序
 * 数组的0下标不存储数据
 * 包括建堆O(n)和排序(Onlog(n))过程
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(1)
 * 原地排序
 * 不稳定的排序算法（总是将最后一个元素和堆顶元素互换，可能会改变相同元素的相对顺序）
 * <p>
 * 内部方法
 * 1.buildHeap  建堆
 * 2.heapfiy    向下堆化
 * 3.swap       交换两个索引元素的位置
 */
public class HeapSort1 {

    public static void heapSort(int[] arr) {
        // 构建大顶堆
        buildHeap(arr);

        // 排序过程，从后向前，时间复杂度O(nlog(n))
        // 每次将堆顶元素（索引1）交换到数组的末尾，然后对堆顶的暂时元素（索引1）实现向下堆化，保证堆顶是最大元素
        int k = arr.length - 1;
        while (k > 1) {
            swap(arr, 1, k);
            k--;
            siftDown(arr, k, 1);
        }
    }

    /**
     * 建堆 数组第一个下标元素不考虑，建堆只需要考虑非叶子节点，所以从n/2开始到数组开头
     * 时间复杂度 O(n)
     * 每个元素的堆化复杂度是O(logn),但是n/2个元素的时间复杂度加一起是O(n)
     */
    private static void buildHeap(int[] arr) {
        // 从n/2从后向前到1，依次siftDown
        for (int i = arr.length / 2; i >= 1; i--) {
            siftDown(arr, arr.length, i);
        }
    }

    /**
     * 向下堆化 siftdown
     *
     * @param parent 执行堆化的索引
     */
    private static void siftDown(int[] arr, int length, int parent) {
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

    private static void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }

}
