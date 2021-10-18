package top.dennyfly.datastructure.bobo.L04_LinkedList;

import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/9/6 17:33
 * 带虚拟头节点的链表
 */
public class LinkedList<E> {

    public Node<E> dummyHead;
    public int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public LinkedList() {
        dummyHead = new Node<E>();
        size = 0;
    }

    public void add(int index, E e) {
        // 这里注意是可以等于size的，此时相当于从末尾添加
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, illegal index");
        }
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, illegal index");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed, illegal index");
        }
        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            if (Objects.equals(cur.e, e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, illegal index");
        }

        Node<E> prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node<E> delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;

        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        Node<E> prev = dummyHead;
        while (prev.next != null) {
            if (Objects.equals(prev.next.e, e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node<E> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
            sb.append(cur.e).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }

}
