package top.chitucao.datastructure.L07_Set_and_Map.C01_set;

import top.chitucao.datastructure.L06_Binary_Search_Tree.BST;

/**
 * @author DennyFly
 * @since 2021/10/18 15:14
 * 基于二叉树实现的Set
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        this.bst = new BST<E>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
