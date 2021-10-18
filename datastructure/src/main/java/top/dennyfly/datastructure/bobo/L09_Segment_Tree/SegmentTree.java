package top.dennyfly.datastructure.bobo.L09_Segment_Tree;

/**
 * @author DennyFly
 * @since 2021/10/13 13:24
 * 线段树
 * 维护两个数组，一个存储原始数据，一个存储树中的合并数据，同时维护一个merge操作
 * <p>
 * 基本操作
 * 1.基于数组初始化线段树；
 * 2.根据索引查询value；
 * 3.区间查询merge value；
 * 4.根据索引索引更新value；
 * <p>
 * <p>
 * 内部方法
 * 1.计算左孩子索引；
 * 2.计算右孩子索引；
 */
public class SegmentTree<E> {

    // 树的数据
    private E[] tree;
    // 原始数据
    private E[] data;
    // 树的数据创建逻辑
    private Merger<E> merger;

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归终止条件，切割至左右孩子相等
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeeIndex = rightChild(treeIndex);

        // 分别构建左右两个孩子树
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeeIndex, mid + 1, r);

        // 对当前节点的值应用merger操作
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeeIndex]);
    }

    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("index is illegal!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 递归终止条件1（提前终止）
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 提前终止条件
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal!");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        // 递归终止条件
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }

        // 调整当前节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    // 返回一个节点的左孩子索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回一个节点的右孩子索引
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

}
