package top.chitucao.datastructure.L04_LinkedList;

import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/9/6 17:33
 * 带虚拟头节点的链表
 * <p>
 * 扩展操作
 * 1.midNode        求链表的中间节点（快慢指针）；
 * 2.reverseNode    反转链表（递归）；
 * 3.reverseNode2   反转链表（遍历swap）
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

    public void clear() {
        dummyHead.next = null;
        size = 0;
    }

    public Node<E> midNode() {
        if (dummyHead.next == null || dummyHead.next.next == null) {
            return dummyHead.next;
        }
        Node<E> quickNode;
        Node<E> slowNode;
        quickNode = slowNode = dummyHead.next;
        // 这里注意偶数的情况是想返回中间的左边还是右边，这里是返回左边
        while (quickNode.next != null && quickNode.next.next != null) {
            slowNode = slowNode.next;
            quickNode = quickNode.next.next;
        }
        return slowNode;
    }

    public void reverseNode() {
        Node<E> head = reverseNode(dummyHead.next);
        dummyHead = new Node<>(null, head);
    }

    public void reverseNode2() {
        Node<E> head = reverseNode2(dummyHead.next);
        dummyHead = new Node<>(null, head);
    }

    // 递归反转 从后向前
    // 注意这里的head就是第一个节点而不是虚拟头节点
    private Node<E> reverseNode(Node<E> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<E> next = head.next;
        Node<E> newNode = reverseNode(next);
        next.next = head;
        head.next = null;
        return newNode;
    }

    // 遍历交换反转
    // 从前到后
    private Node<E> reverseNode2(Node<E> head) {
        if (head == null) {
            return head;
        }
        Node<E> pre = head;
        Node<E> cur = head.next;
        Node<E> temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = null;
        return pre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: ");
        for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
            sb.append(cur.e).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }

}
