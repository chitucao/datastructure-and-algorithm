package top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set;

import top.dennyfly.datastructure.bobo.L04_LinkedList.LinkedList;

/**
 * @author DennyFly
 * @since 2021/9/24 9:25
 * 基于链表实现的Set
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!linkedList.contains(e)) {
            linkedList.addFirst(e);
        }
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public int getSize() {
        return linkedList.size;
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
