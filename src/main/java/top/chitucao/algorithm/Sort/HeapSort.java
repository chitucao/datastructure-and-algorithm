package top.chitucao.algorithm.Sort;

/**
 * @author DennyFly
 * @since 2021/11/2 16:14
 * 堆排序
 * #思路
 * 1.建堆：（构造一个大顶堆）只需要将0到length/2-1的元素siftDown就行了(注意从下面元素到上面)，时间复杂度O(n)；
 * 2.排序：依次将堆顶元素和数组末尾交换，然后对堆顶元素执行siftDown构造成新的大顶堆；
 * <p>
 * 时间复杂度O(nlogn)
 * 空间复杂度O(1)
 * 原地排序
 * 不稳定的排序算法（总是将最后一个元素和堆顶元素互换，可能会改变相同元素的相对顺序）
 * <p>
 * 内部方法
 * 1.buildHeap  建堆
 * 2.siftDown   向下堆化
 * 3.swap       交换两个索引元素的位置
 */
public class HeapSort {

    public static void heapSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        buildMaxHeap(nums);

        int len = nums.length - 1;
        for (int i = 0; i < nums.length - 1; i++) {
            swap(nums, 0, len);
            maxHeapify(nums, len, 0);
            len--;
        }
    }

    // 建堆 O(n)
    private static void buildMaxHeap(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, n, i);
        }
    }

    // 向下堆化，注意这里的n是最后一个元素的位置
    private static void maxHeapify(int[] nums, int n, int parent) {
        while (true) {
            int maxPos = parent;
            int left = parent * 2 + 1;
            if (left < n && nums[left] > nums[maxPos]) {
                maxPos = left;
            }
            int right = parent * 2 + 2;
            if (right < n && nums[right] > nums[maxPos]) {
                maxPos = right;
            }

            if (maxPos == parent) {
                break;
            }

            swap(nums, maxPos, parent);
            parent = maxPos;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
