package top.chitucao.tmp.sort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 10:20
 */
public class SelectionSortT {

    public void selectionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = i;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }

            if (min != i) {
                swap(nums, min, i);
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
        new SelectionSortT().selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
