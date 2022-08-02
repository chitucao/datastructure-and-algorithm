package top.chitucao.algorithm.T03_Divide_Conquer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author DennyFly
 * @since 2021/11/12 13:05
 * <p>
 * 参考资料1（超时） https://blog.csdn.net/lizhichao410/article/details/107286718
 */
public class ReversePairList {

    // 分治
    public static List<Integer> solution1(int[] nums) {
        // 结果接收
        int[] res = new int[nums.length];

        // index表示排序好的索引下标
        int[] index = new int[res.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }

        mergeSorting(nums, index, 0, nums.length - 1, res);

        // 处理返回结果
        List<Integer> list = new LinkedList<>();
        for (int re : res) {
            list.add(re);
        }
        return list;
    }

    private static void mergeSorting(int[] nums, int[] index, int p, int r, int[] res) {
        if (p >= r) {
            return;
        }
        int q = p + (r - p) / 2;
        mergeSorting(nums, index, p, q, res);
        mergeSorting(nums, index, q + 1, r, res);
        merge2(nums, index, p, q, r, res);
    }

    private static void merge1(int[] nums, int[] index, int p, int q, int r, int[] res) {
        int i = p;
        int j = q + 1;

        // 临时数组，存放排序好的下标
        int[] tmp = new int[r - p + 1];
        int k = 0;

        while (i <= q && j <= r) {
            if (nums[index[i]] > nums[index[j]]) {
                // 说明从i到q的元素都比右边的元素大，遍历+1
                for (int t = i; t <= q; t++) {
                    res[index[t]]++;
                }
                tmp[k++] = index[j++];
            } else {
                tmp[k++] = index[i++];
            }
        }

        while (i <= q) {
            tmp[k++] = index[i++];
        }
        while (j <= r) {
            tmp[k++] = index[j++];
        }

        for (int f = 0; f <= r - p; f++) {
            index[p + f] = tmp[f];
        }
    }

    private static void merge2(int[] nums, int[] index, int p, int q, int r, int[] res) {
        int i = p;
        int j = q + 1;

        // 临时数组，存放排序好的下标
        int[] tmp = new int[r - p + 1];
        int k = 0;

        // 右边小于该值的元素计数
        int count = 0;

        while (i <= q && j <= r) {
            if (nums[index[i]] > nums[index[j]]) {
                count++;
                tmp[k++] = index[j++];
            } else {
                res[index[i]] += count;
                tmp[k++] = index[i++];
            }
        }

        while (i <= q) {
            res[index[i]] += count;
            tmp[k++] = index[i++];
        }

        while (j <= r) {
            tmp[k++] = index[j++];
        }

        for (int f = 0; f <= r - p; f++) {
            index[p + f] = tmp[f];
        }
    }
}
