package top.dennyfly.datastructure.L11_Union_Find;

/**
 * @author DennyFly
 * @since 2021/10/15 13:58
 */
public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}