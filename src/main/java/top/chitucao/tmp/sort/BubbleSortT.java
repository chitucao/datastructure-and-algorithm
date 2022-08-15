package top.chitucao.tmp.sort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 10:39
 */
public class BubbleSortT {

    public void bubbleSort(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public void bubbleSortEarly(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    private void swap(int[] nums, int p, int q) {
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 9, 12, 8, 2, 2};
        new BubbleSortT().bubbleSortEarly(nums);
        System.out.println(Arrays.toString(nums));
    }


}
