package top.dennyfly.datastructure.bobo.L11_Union_Find;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/15 14:46
 */
public class Main {

    @Test
    public void testArrUF() {
        UF uf = new UnionFind1(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
    }

    @Test
    public void testParentArrUF() {
        UF uf = new UnionFind2(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
    }

    @Test
    public void testParentArrWithSizeUF() {
        UF uf = new UnionFind3(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
    }
}
