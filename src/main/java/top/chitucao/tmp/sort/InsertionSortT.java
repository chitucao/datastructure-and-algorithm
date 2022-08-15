package top.chitucao.tmp.sort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 10:07
 */
public class InsertionSortT {

    public void insertionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int num = nums[i];

            int j = i - 1;
            for (; j >= 0 && nums[j] > num; j--) {
                nums[j + 1] = nums[j];
            }

            nums[j + 1] = num;
        }

    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 9, 12, 8, 2, 2};
        new InsertionSortT().insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
