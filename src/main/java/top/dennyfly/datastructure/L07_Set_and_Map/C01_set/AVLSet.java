package top.dennyfly.datastructure.L07_Set_and_Map.C01_set;

import top.dennyfly.datastructure.L12_AVL_Tree.AVLTree;

/**
 * @author DennyFly
 * @since 2021/10/17 15:50
 * 基于AVL树实现的Set
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
