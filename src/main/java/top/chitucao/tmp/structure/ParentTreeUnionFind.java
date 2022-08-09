package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/9 17:30
 */
public class ParentTreeUnionFind implements UFT {

    private int[] parent;

    public ParentTreeUnionFind(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        return pRoot == qRoot;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public static void main(String[] args) {
        UFT uft = new ParentTreeUnionFind(5);
        System.out.println("size：" + uft.getSize());

        uft.unionElements(1, 2);
        uft.unionElements(2, 4);

        System.out.println("isConnected：" + uft.isConnected(1, 3));
        System.out.println("isConnected：" + uft.isConnected(1, 4));
    }
}
