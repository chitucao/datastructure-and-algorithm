package top.chitucao.tmp;

//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
//
// 示例：
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
//
//
// 提示：
//
//
// 0 <= len(arr) <= 100000
// 0 <= k <= min(100000, len(arr))
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 189 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] smallestK(int[] arr, int k) {
        return solution1(arr, k);
    }

    /**
     * 小顶堆
     * 时间复杂度 O(nlog(n))
     * 空间复杂度 O(1)
     */
    public int[] solution1(int[] arr, int k) {

        buildMinHeap(arr);

        // 打印小顶堆
//        int len = arr.length - 1;
//        for (int i = len; i > 0; i--) {
//            System.out.println(arr[0]);
//            swap(arr, i, 0);
//            minHeapify(arr, len, 0);
//            len--;
//        }

        int[] ans = new int[k];
        int j = 0;

        int len = arr.length - 1;
        for (int i = len; i >= arr.length - k; i--) {
            ans[j++] = arr[0];

            swap(arr, i, 0);
            minHeapify(arr, len, 0);
            len--;
        }

        return ans;
    }

    private void buildMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(arr, n, i);
        }
    }

    private void minHeapify(int[] arr, int len, int parent) {
        while (true) {
            int minPos = parent;
            int leftChild = 2 * parent + 1;
            if (leftChild < len && arr[leftChild] < arr[minPos]) {
                minPos = leftChild;
            }

            int rightChild = 2 * parent + 2;
            if (rightChild < len && arr[rightChild] < arr[minPos]) {
                minPos = rightChild;
            }

            if (minPos == parent) {
                break;
            }

            swap(arr, minPos, parent);
            parent = minPos;
        }
    }

    private void swap(int[] arr, int p, int q) {
        int temp = arr[p];
        arr[p] = arr[q];
        arr[q] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
