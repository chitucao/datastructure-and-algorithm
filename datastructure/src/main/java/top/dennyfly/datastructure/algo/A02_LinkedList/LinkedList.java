package top.dennyfly.datastructure.algo.A02_LinkedList;

import java.util.Objects;

/**
 * @author dennyfly
 * @since 2021/11/19 17:15
 * 实现单链表，支持增删操作
 * #带头节点，不维护size变量，支持按index操作
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

    // 根据value获取节点
    public Node findByValue(E value) {
        Node p = head.next;
        while (p != null && !Objects.equals(p.value, value)) {
            p = p.next;
        }
        return p;
    }

    /**
     * 根据value删除节点
     * #需遍历整个链表，找到待删除节点后需要断开待删除节点的next
     */
    public void removeByValue(E value) {
        Node pre = head;

        // 注意循环是遍历链表所有元素，也就是pre.next!=null
        while (pre.next != null) {
            if (Objects.equals(pre.next.value, value)) {
                break;
            }
            pre = pre.next;
        }

        if (pre.next != null) {
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
        }
    }


}
