package top.dennyfly.datastructure.bobo.L12_AVL_Tree;

import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C02_map.Map;

/**
 * @author DennyFly
 * @since 2021/10/17 15:47
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
