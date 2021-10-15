package top.dennyfly.datastructure.bobo.L11_Union_Find;

/**
 * @author DennyFly
 * @since 2021/10/15 15:33
 * 数组实现的并查集（这里索引处存储的是当前索引根父节点的值）
 */
public class UnionFind2 implements UF {

    /**
     * parent[i]表示i索引处元素的父节点值
     */
    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];
        // 初始化的时候i索引处的父节点=i节点索引
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // O(h)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(h)复杂度
    // 这个地方存在问题，例如按照以下顺序，0-1，0-2，0-3... 则查找的高度依次为（左边1...n,右边1）,其中一个树的一个高度明显高于右边
    // 因为要查询两次，所以可以考虑平均一下两棵树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
    }

    // O(h)复杂度
    private int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("input is out of index");
        }

        // 根节点的特点是数组索引=数组索引处的值
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
