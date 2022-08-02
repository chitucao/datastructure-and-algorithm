package top.chitucao.datastructure.L11_Union_Find;

/**
 * QuickUnion优化2
 * 增加rank[]数组表示该索引处树的高度
 * 优化思路：高度低的树加在高度高的树的根节点上
 * <p>
 * 查询复杂度 O(log(n)) 树的高度
 * 连接复杂度 O(log(n))
 * 增加一个rank[]数组，用于记录树的高度，默认初始化为1，两棵树的高度相等时，才会维护其中一棵树的高度；
 */
public class UnionFind4 implements UF {


    private int[] parent;
    private int[] rank;

    public UnionFind4(int size) {
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
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // 注意只有rank相等的时候，其中一个高度才会增加
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("input is out of index");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
