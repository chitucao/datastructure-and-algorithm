package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/2 17:48
 */
public class SegmentTreett<E> {

    // 三个成员变量，原始数组，线段树数组，merge算法
    private E[] data;

    private E[] tree;

    private Mergertt<E> mergertt;

    public SegmentTreett(E[] arr, Mergertt<E> mergertt) {
        this.mergertt = mergertt;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    public E query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        // 这里包含找到最后一个叶子节点的情况
        if (queryL == l && queryR == r) {
            return tree[treeIndex];
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        // 区间不拆分只需要从一侧查询的情况
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        // 区间拆分查两次然后归并两边结果
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, r);
        return mergertt.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int leftTreeIndex = leftChild(index);
        int rightTreeIndex = rightChild(index);

        int mid = l + (r - l) + 1;
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = mergertt.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 终止情况，到达最下面的叶子节点
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 处理当前节点，向上合并
        tree[treeIndex] = mergertt.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
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

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        // 最大值
        SegmentTreett<Integer> segTree = new SegmentTreett<>(nums, (a, b) -> Math.max(a, b));
        System.out.println(segTree);

        Integer max = segTree.query(0, 1);
        System.out.println(max);
    }

}
