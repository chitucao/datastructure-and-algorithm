package top.dennyfly.datastructure.bobo.L11_Union_Find;

/**
 * @author DennyFly
 * @since 2021/10/15 16:33
 * 使用sz[]数组表示该索引处树中元素的个数
 * 优化思路：元素数量少的树加到数量多的树后面
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
