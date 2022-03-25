package top.dennyfly.datastructure.L11_Union_Find;

/**
 * QuickUnion优化3.2
 * 基于循环路径压缩优化，rank可能不表示树的层数
 * <p>
 * 查询复杂度 O(log(n)) 树的高度
 * 连接复杂度 O(log(n))
 * 基于递归路径压缩优化，同3.1，注意这里的rank并不是实际的树的高度；
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (rank[pRoot] < qRoot) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }

    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("input is illegal");
        }
//        if (p != parent[p]) {
//            p = find(parent[p]);
//        }
//        return p;
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}
