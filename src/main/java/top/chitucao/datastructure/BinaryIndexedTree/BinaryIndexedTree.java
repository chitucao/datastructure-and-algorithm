package top.chitucao.datastructure.BinaryIndexedTree;

/**
 * 树状数组
 * <p>
 * 构造时间复杂度 O(nlog(n))
 * update时间复杂度 O(log(n))
 * sumRange时间复杂度 O(log(n))
 * 空间复杂度 O(n)
 * <p>
 * 参考资料
 * https://blog.csdn.net/qq_40941722/article/details/104406126
 */
public class BinaryIndexedTree {

    private int[] nums;
    private int[] tree;

    public BinaryIndexedTree(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        add(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private void add(int index, int val) {
        while (index < tree.length) {
            tree[index] += val;
            index += lowBit(index);
        }
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lowBit(index);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 7, 2, 5, -4};
        BinaryIndexedTree bit = new BinaryIndexedTree(arr);
        System.out.println(bit);

        int sum = bit.sumRange(0, 2);
        System.out.println(sum);

        bit.update(0, 2);
        int sum2 = bit.sumRange(0, 2);
        System.out.println(sum2);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != 0) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i != tree.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
