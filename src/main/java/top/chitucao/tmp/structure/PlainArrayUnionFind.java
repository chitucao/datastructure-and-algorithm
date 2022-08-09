package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/9 17:15
 * quickfind
 */
public class PlainArrayUnionFind implements UFT {

    private int[] data;

    public PlainArrayUnionFind(int size) {
        this.data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pVal = find(p);
        int qVal = find(q);
        if (pVal == qVal) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] == pVal) {
                data[i] = qVal;
            }
        }
    }

    private int find(int p) {
        if (p < 0 || p >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[p];
    }

    public static void main(String[] args) {
        UFT uft = new PlainArrayUnionFind(5);
        System.out.println("size：" + uft.getSize());

        uft.unionElements(1, 2);
        uft.unionElements(2, 4);

        System.out.println("isConnected：" + uft.isConnected(1, 3));
        System.out.println("isConnected：" + uft.isConnected(1, 4));
    }
}
