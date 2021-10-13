package top.dennyfly.datastructure.bobo.L08_Segment_Tree;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/13 14:31
 */
public class Main {

    @Test
    public void testCreate() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        // [-3, 1, -4, -2, 3, -3, -1, -2, 0, null, null, -5, 2, null, null, null, null, null, null, null, null, null, null, null]
        System.out.println(segTree);
    }

    @Test
    public void testQuery() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree);

        // 1
        System.out.println(segTree.query(0, 2));
        // -1
        System.out.println(segTree.query(2, 5));
        // -3
        System.out.println(segTree.query(0, 5));
    }

    @Test
    public void testSet() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        // [-3, 1, -4, -2, 3, -3, -1, -2, 0, null, null, -5, 2, null, null, null, null, null, null, null, null, null, null, null]
        System.out.println(segTree);

        segTree.set(1, 2);
        // [-1, 3, -4, 0, 3, -3, -1, -2, 2, null, null, -5, 2, null, null, null, null, null, null, null, null, null, null, null]
        System.out.println(segTree);
    }

}
