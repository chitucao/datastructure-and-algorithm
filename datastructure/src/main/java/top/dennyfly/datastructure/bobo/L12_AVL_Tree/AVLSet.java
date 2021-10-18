package top.dennyfly.datastructure.bobo.L12_AVL_Tree;

import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set.Set;

/**
 * @author DennyFly
 * @since 2021/10/17 15:50
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> tree;

    public AVLSet() {
        this.tree = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        tree.add(e, null);
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }
}
