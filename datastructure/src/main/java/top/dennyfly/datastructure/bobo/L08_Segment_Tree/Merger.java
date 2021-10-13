package top.dennyfly.datastructure.bobo.L08_Segment_Tree;

/**
 * @author DennyFly
 * @since 2021/10/13 14:03
 */
public interface Merger<E> {
    E merge(E a, E b);
}
