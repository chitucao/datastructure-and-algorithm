package top.dennyfly.tmp.A02_LinkedList;

import java.util.Objects;

/**
 * @author dennyfly
 * @since 2021/11/19 17:15
 * 实现单链表，支持增删操作
 * #不带虚拟头节点（所以不需要构造方法初始化头节点），维护size（按索引操作的时候可以校验范围）
 * <p>
 * #基本操作#
 * findByValue      根据值查询
 * findByIndex      根据索引查询
 * set              根据索引更新值
 * contains         是否包含指定元素
 * --
 * removeByValue    根据value删除
 * removeByIndex    根据索引删除
 * removeFirst      删除头部节点
 * removeLast       删除尾部节点
 * --
 * addByIndex       根据索引添加
 * addFirst         添加到头节点位置
 * addLast          添加到尾节点位置
 * <p>
 * -- 有争议的方法
 * removeByNode     根据node删除（感觉这个没必要，存在相同节点的问题）
 * addAfter         在一个节点后面添加（存在相同节点的问题）
 * addBefore        在一个节点前面添加（需要遍历找到该节点的前一个节点，存在相同节点的问题）
 * --
 * toString         打印链表
 * <p>
 * <p>
 * #链表问题#
 * 1.reverseNode    反转链表
 * 2.midNode        求中间节点
 */
public class LinkedList<E> {

    class Node {
        public E value;
        public Node next;

        public Node(E value) {
            this.value = value;
            this.next = null;
        }

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    /**
     * 根据value获取
     */
    public Node findByValue(E value) {
        Node cur = head;
        while (cur != null && !Objects.equals(cur.value, value)) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 根据index获取
     */
    public Node findByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("find failed,index is illegal");
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 根据索引设置值
     */
    public void set(int index, E e) {
        Node node = findByIndex(index);
        node.value = e;
    }

    /**
     * 是否包含指定元素
     */
    public boolean contains(E e) {
        return findByValue(e) != null;
    }

    /**
     * 头节点位置添加
     */
    public void addFirst(E e) {
        head = new Node(e, head);
        size++;
    }

    /**
     * 尾节点位置添加
     */
    public void addLast(E e) {
        addByIndex(size, e);
    }

    /**
     * 指定节点后面添加
     */
    public void addAfter(Node node, E e) {
        node.next = new Node(e, node.next);
    }

    /**
     * 指定节点前面添加
     */
    public void addBefore(Node node, E e) {
        // 考虑头节点的情况
        if (Objects.equals(node, head)) {
            head = new Node(e, head);
            return;
        }

        // 遍历找到前一个节点
        Node prev = head;
        while (prev.next != null) {
            if (Objects.equals(node, prev.next)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node tmp = prev.next;
            prev.next = new Node(e, prev.next);
            tmp.next = null;
            size++;
        }
    }

    /**
     * 根据索引添加
     */
    public void addByIndex(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed,illegal index");
        }
        // 考虑到头节点的情况
        if (index == 0) {
            head = new Node(e, head);
            size++;
            return;
        }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 删除头部节点
     */
    public void removeFirst() {
        head = head.next;
        size--;
    }

    /**
     * 删除尾部节点
     */
    public void removeLast() {
        removeByIndex(size - 1);
    }

    /**
     * 根据value删除
     * #需遍历整个链表，找到待删除节点后需要断开待删除节点的next
     */
    public void removeByValue(E value) {
        // 链表为空直接返回
        if (head == null) {
            return;
        }

        // 考虑head节点为所查找节点的情况
        if (Objects.equals(value, head.value)) {
            head = head.next;
            size--;
            return;
        }

        // 外层遍历，内层比较退出循环
        Node prev = head;
        while (prev.next != null) {
            if (Objects.equals(value, prev.next.value)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node tmp = prev.next;
            prev.next = tmp.next;
            tmp.next = null;
            size--;
        }
    }

    /**
     * 根据节点删除
     */
    public void removeByNode(Node node) {
        if (head == null || node == null) {
            return;
        }

        // 考虑head节点为所查找节点的情况
        if (Objects.equals(head, node)) {
            head = head.next;
            size--;
            return;
        }

        Node prev = head;
        while (prev.next != null) {
            if (Objects.equals(node, prev.next)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node tmp = prev.next;
            prev.next = tmp.next;
            tmp.next = null;
            size--;
        }
    }

    /**
     * 根据索引删除
     */
    public void removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed,index is illegal");
        }

        // 考虑头节点的情况
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        // 这里会找到前一个节点，同时会再次校验index范围
        Node node = findByIndex(index - 1);

        Node tmp = node.next;
        node.next = tmp.next;
        tmp.next = null;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList Head:");
        Node tmp = head;
        while (tmp != null) {
            sb.append(tmp.value + "->");
            tmp = tmp.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    /**
     * 反转链表
     * 基于递归
     */
    public void reverseNode1() {
        head = reverseNode(head);
    }

    private Node reverseNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node next = head.next;
        Node newHead = reverseNode(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转链表
     * 依次遍历修改相邻引用
     */
    public void reverseNode2() {
        if (head == null) {
            return;
        }
        Node prev = head;
        Node cur = head.next;
        Node temp;
        while (cur != null) {
            temp = cur.next;

            cur.next = prev;

            prev = cur;
            cur = temp;
        }
        head.next = null;
        head = prev;
    }

    /**
     * 中间节点
     */
    public Node midNode() {
        if (head == null) {
            return head;
        }
        Node quick = head;
        Node slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
