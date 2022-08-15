package top.chitucao.tmp.sort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 10:52
 */
public class HeapSortT {

    public void heapSort(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        buildHeap(nums);

        int len = nums.length - 1;
        for (int i = 0; i < nums.length - 1; i++) {
            swap(nums, 0, len);
            maxHeapify(nums, 0, len);
            len--;
        }
    }

    private void buildHeap(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, n);
        }
    }

    private void maxHeapify(int[] nums, int parent, int n) {
        int maxPos = parent;
        while (true) {
            int leftChild = parent * 2 + 1;
            if (leftChild < n && nums[leftChild] > nums[maxPos]) {
                maxPos = leftChild;
            }

            int rightChild = parent * 2 + 2;
            if (rightChild < n && nums[rightChild] > nums[maxPos]) {
                maxPos = rightChild;
            }

            if (maxPos == parent) {
                break;
            }

            swap(nums, maxPos, parent);
            maxPos = parent;
        }
    }

    private void swap(int[] nums, int p, int q) {
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 9, 12, 8, 2, 2};
        new HeapSortT().heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
