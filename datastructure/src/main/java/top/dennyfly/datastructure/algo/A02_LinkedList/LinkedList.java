package top.dennyfly.datastructure.algo.A02_LinkedList;

import java.util.Objects;

/**
 * @author dennyfly
 * @since 2021/11/19 17:15
 * 实现单链表，支持增删操作
 * #不带虚拟头节点，不维护size变量，支持按index操作
 * <p>
 * #基本操作#
 * findByValue  根据值查询
 * findByIndex  根据索引查询
 * removeByValue    根据value删除
 * removeByNode     根据node
 * removeByIndex    根据索引删除
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

    public LinkedList() {
        this.head = new Node(null);
    }

    /**
     * 根据value获取
     */
    public Node findByValue(E value) {
        Node p = head;
        while (p != null && !Objects.equals(p.value, value)) {
            p = p.next;
        }
        return p;
    }

    /**
     * 根据index获取
     */
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && index != pos) {
            p = p.next;
            pos++;
        }
        return p;
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
        }
    }

    /**
     * 根据索引删除
     */
    public void removeByIndex(int index) {
        if (index == 0) {
            head = head.next;
        }
        Node node = findByIndex(index - 1);
        if (node == null) {
            return;
        }

        Node tmp = node.next;
        node.next = tmp.next;
        tmp = null;
    }

}
