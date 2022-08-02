package top.chitucao.datastructure.Set_and_Map.C02_map;

import top.chitucao.datastructure.AVLTree.AVLTree;

/**
 * @author DennyFly
 * @since 2021/10/17 15:47
 * 基于AVL树实现的Map
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> tree;

    public AVLMap() {
        this.tree = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        tree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return tree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return tree.contains(key);
    }

    @Override
    public V get(K key) {
        return tree.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        tree.set(key, newValue);
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
