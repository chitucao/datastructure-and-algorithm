package top.chitucao.datastructure.UnionFind;

/**
 * QuickUnion优化1
 * 优化思路：元素数量少的树加到数量多的树后面，
 * <p>
 * 查询复杂度 O(log(n)) 树的高度
 * 连接复杂度 O(log(n))
 * 增加sz[]数组表示该索引处树中元素的数量，每次给一棵树增加元素数量，但是数量并不能准确代表高度；
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        int pParent = find(p);
        int qParent = find(q);

        // 将数量小的树节点的父节点指向数量大的节点的父节点，合并成一棵树后需要维护增加元素的数量
        if (sz[pParent] < sz[qParent]) {
            parent[pParent] = qParent;
            sz[qParent] += sz[pParent];
        } else {
            parent[qParent] = pParent;
            sz[pParent] += sz[qParent];
        }

    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("input out of index");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
