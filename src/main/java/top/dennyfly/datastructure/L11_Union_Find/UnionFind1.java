package top.dennyfly.datastructure.L11_Union_Find;

/**
 * 基于数组实现的并查集 QuickFind
 * 查询复杂度 O(1)
 * 连接复杂度 O(n)
 * <p>
 * #思路
 * 初始化时，数组的值存储数组的索引，保证各不相同，（如果值相同则表示连接），这个p和q都是指的索引；
 * 连接的时候，将下标值为A的一组元素对应索引值全部改成B的值，最终全部是等于B；
 * 查询的时候只需要判断两个索引p、q对应的元素值是否相等；
 */
public class UnionFind1 implements UF {

    private int[] data;


    public UnionFind1(int size) {
        data = new int[size];
        // 初始化，每一个id[i]指向自己，没有合并的元素
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    /**
     * 判断两个索引处的值是否相等
     * O(1)复杂度
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接元素
     * O(n) 复杂度
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            if (data[i] == pID) {
                data[i] = qID;
            }
        }
    }

    // 这里是找当前节点的值，时间复杂度 O(1)
    private int find(int p) {
        if (p < 0 || p >= data.length) {
            throw new IllegalArgumentException("index is illegal! p is out of index");
        }
        return data[p];
    }
}
