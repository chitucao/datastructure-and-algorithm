package top.dennyfly.datastructure.bobo.L11_Union_Find;

/**
 * @author DennyFly
 * @since 2021/10/15 14:08
 * 基于id数组实现的并查集 (查询O(1),连接O(n))
 * <p>
 * 这里的p和q对应数组的索引
 * <p>
 * 基本操作
 * 1.根据索引查询；
 * 2.连接两个索引；
 * 3.根据索引判断是否连接；
 */
public class UnionFind1 implements UF {

    private int[] id;


    public UnionFind1(int size) {
        id = new int[size];
        // 初始化，每一个id[i]指向自己，没有合并的元素
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 判断两个索引处的值是否相等
     * O(1)复杂度
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(n) 复杂度
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            // 将所有的等于pID的索引处的值，改成qID
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }

    /**
     * 根据索引查找元素
     * O(1) 复杂度
     *
     * @param p 数组索引
     * @return 索引位置的值
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("index is illegal! p is out of index");
        }
        return id[p];
    }
}
