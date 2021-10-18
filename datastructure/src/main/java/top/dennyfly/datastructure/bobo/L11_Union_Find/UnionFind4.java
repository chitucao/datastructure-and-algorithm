package top.dennyfly.datastructure.bobo.L11_Union_Find;

/**
 * @author DennyFly
 * @since 2021/10/15 17:10
 * 维护一个rank[]数组，用于记录树的高度，来判断两个树的连接规则
 * <p>
 * 这里的p和q对应根节点的索引
 * <p>
 * 基本操作
 * 1.根据索引查询；
 * 2.连接两个索引；
 * 3.根据索引判断是否连接；
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
