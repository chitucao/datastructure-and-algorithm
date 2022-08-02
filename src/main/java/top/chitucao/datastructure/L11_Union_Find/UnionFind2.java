package top.chitucao.datastructure.L11_Union_Find;

/**
 * 基于父节点数组实现的并查集 QuickUnion
 * 查询复杂度 O(log(n)) 树的高度
 * 连接复杂度 O(log(n))
 * <p>
 * #思路
 * 初始化时，数组的索引处存储其父节点的值，默认每个节点的父节点指向它自己（指向自己表示其是根节点）；
 * 连接时，向上找到A，B这两个节点的父节点，然后将A节点的父节点指向B节点的父节点；
 * 查询时，判断两个节点的父节点元素是否相等；
 * 无论是连接还是查询，都需要先找到父节点的指向，所以时间复杂度都是O(log(n))，取决于树的高度；
 */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];
        // 初始化时指向自己，都是根节点
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

    /**
     * O(h)复杂度
     * 这个地方存在问题，例如按照以下顺序，0-1，0-2，0-3... 则查找的高度依次为（左边1...n,右边1）,其中一个树的一个高度明显高于右边；
     * 所以可以考虑平均一下两棵树的高度；
     * 这里是需要查询两次；
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
    }

    // 这里是找父节点的值，时间复杂度 O(h)
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
