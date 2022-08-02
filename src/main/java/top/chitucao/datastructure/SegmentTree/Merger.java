package top.chitucao.datastructure.SegmentTree;

/**
 * @author DennyFly
 * @since 2021/10/13 14:03
 */
public interface Merger<E> {
    E merge(E a, E b);
}
