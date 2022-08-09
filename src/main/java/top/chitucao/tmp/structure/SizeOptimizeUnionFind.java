package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/9 17:29
 */
public class SizeOptimizeUnionFind implements UFT {

    private int[] parent;

    private int[] sz;

    public SizeOptimizeUnionFind(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
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
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        int pSize = sz[pRoot];
        int qSize = sz[qRoot];

        if (pSize < qSize) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }


    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public static void main(String[] args) {
        UFT uft = new SizeOptimizeUnionFind(5);
        System.out.println("size：" + uft.getSize());

        uft.unionElements(1, 2);
        uft.unionElements(2, 4);

        System.out.println("isConnected：" + uft.isConnected(1, 3));
        System.out.println("isConnected：" + uft.isConnected(1, 4));
    }
}
