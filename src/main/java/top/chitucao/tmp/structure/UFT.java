package top.chitucao.tmp.structure;

/**
 * @author chitucao
 * @since 2022/8/9 17:14
 */
public interface UFT {
    int getSize();
    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
