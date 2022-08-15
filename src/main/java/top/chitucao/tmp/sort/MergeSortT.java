package top.chitucao.tmp.sort;

import top.chitucao.algorithm.Sort.MergeSort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 9:54
 */
public class MergeSortT {

    private void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
    }

    private void sort(int[] nums, int l, int r, int[] temp) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;

        sort(nums, l, mid, temp);
        sort(nums, mid + 1, r, temp);

        mergeTwoSortedArray(nums, l, mid + 1, r, temp);
    }

    private void mergeTwoSortedArray(int[] nums, int l, int mid, int r, int[] temp) {
        int tempIdx = l;
        int lEnd = mid-1;
        int rStart = mid ;

        int len = r - l + 1;

        while (l <= lEnd && rStart <= r) {
            if (nums[l] < nums[rStart]) {
                temp[tempIdx++] = nums[l++];
            } else {
                temp[tempIdx++] = nums[rStart++];
            }
        }

        while (l <= lEnd) {
            temp[tempIdx++] = nums[l++];
        }

        while (rStart <= r) {
            temp[tempIdx++] = nums[rStart++];
        }

        for (int i = 0; i < len; i++) {
            nums[r - i] = temp[r - i];
        }

    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 9, 12, 8, 2, 2};
        new MergeSortT().mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
