package top.chitucao.tmp;

import cn.hutool.json.JSONUtil;

/**
 * @author dennyfly
 * @since 2022/7/22 10:15
 */
public class MergeSort {

    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
    }

    private static void sort(int[] nums, int p, int q, int[] temp) {
        if (p >= q) {
            return;
        }

        int mid = p + (q - p) / 2;
        sort(nums, p, mid, temp);
        sort(nums, mid + 1, q, temp);

        mergeTwoSortedArr(nums, p, mid + 1, q, temp);
    }

    private static void mergeTwoSortedArr(int[] nums, int p, int mid, int q, int[] temp) {
        int pEnd = mid - 1;
        int qStart = mid;

        int tempPos = p;
        int tempLen = q - p + 1;

        while (p <= pEnd && qStart <= q) {
            if (nums[p] < nums[qStart]) {
                temp[tempPos++] = nums[p++];
            } else {
                temp[tempPos++] = nums[qStart++];
            }
        }

        while (p <= pEnd) {
            temp[tempPos++] = nums[p++];
        }

        while (qStart <= q) {
            temp[tempPos++] = nums[qStart++];
        }

        for (int i = 0; i < tempLen; i++) {
            nums[q - i] = temp[q - i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 1, 9, 8};
        mergeSort(nums);
        System.out.println(JSONUtil.toJsonStr(nums));
    }
}
