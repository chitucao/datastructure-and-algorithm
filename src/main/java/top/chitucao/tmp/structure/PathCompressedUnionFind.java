package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/9 17:50
 */
public class PathCompressedUnionFind implements UFT {

    private int[] parent;

    private int[] rank;

    public PathCompressedUnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
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

        int pRank = rank[p];
        int qRank = rank[q];

        if (pRank < qRank) {
            parent[pRoot] = qRoot;
        } else if (qRank < pRank) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    private int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

//    private int find(int p) {
//        if (p != parent[p]) {
//            parent[p] = find(parent[p]);
//        }
//        return parent[p];
//    }

    public static void main(String[] args) {
        UFT uft = new ParentTreeUnionFind(5);
        System.out.println("size：" + uft.getSize());

        uft.unionElements(1, 2);
        uft.unionElements(2, 4);

        System.out.println("isConnected：" + uft.isConnected(1, 3));
        System.out.println("isConnected：" + uft.isConnected(1, 4));
    }
}
