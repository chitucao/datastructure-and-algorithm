package top.chitucao.tmp.sort;

import java.util.Arrays;

/**
 * @author chitucao
 * @since 2022/8/15 9:02
 */
public class QuickSortT {

    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = partition3(nums, l, r);
        sort(nums, l, pivot - 1);
        sort(nums, pivot + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        int num = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= num) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] <= num) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = num;
        return l;
    }

    private int partition2(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);

        return i;
    }

    private int partition3(int[] nums, int l, int r) {
        int num = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= num) {
                r--;
            }
            swap(nums, l, r);
            while (l < r && nums[l] <= num) {
                l++;
            }
            swap(nums, l, r);
        }
        nums[l] = num;
        return l;
    }

    private void swap(int[] nums, int p, int q) {
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 9, 12, 8, 2, 2};
        new QuickSortT().quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
