package top.dennyfly.datastructure.L11_Union_Find;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/15 14:46
 *
 */
public class Main {

    @Test
    public void testArrUF() {
        UF uf = new UnionFind1(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        uf.unionElements(3,9);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
        System.out.println(uf.isConnected(1,9));
    }

    @Test
    public void testParentArrUF() {
        UF uf = new UnionFind2(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        uf.unionElements(3,9);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
        System.out.println(uf.isConnected(1,9));
    }

    @Test
    public void testParentArrWithSizeUF() {
        UF uf = new UnionFind3(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
    }

    @Test
    public void testParentArrWithRankUF() {
        UF uf = new UnionFind4(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
    }

    @Test
    public void testParentArrWithCompressIfUF() {
        UF uf = new UnionFind5(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        uf.unionElements(2,9);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
        System.out.println(uf.isConnected(1,9));
    }

    @Test
    public void testParentArrWithCompressRecursionUF() {
        UF uf = new UnionFind6(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 3);
        uf.unionElements(2,9);
        System.out.println(uf.isConnected(1, 3));
        System.out.println(uf.isConnected(1,4));
        System.out.println(uf.isConnected(1,9));
    }
}
