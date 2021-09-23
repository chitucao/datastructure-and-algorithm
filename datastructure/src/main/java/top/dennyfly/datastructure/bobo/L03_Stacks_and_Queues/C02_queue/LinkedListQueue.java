package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue;

import top.dennyfly.datastructure.bobo.L04_LinkedList.Node;

/**
 * @author DennyFly
 * @since 2021/9/8 17:35
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> head, tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cannot dequeue from a empty queue");
        }
        Node<E> retNode = head;
        head = head.next;
        retNode.next = null;

        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue front：");
        Node<E> cur = head;
        while (cur != null) {
            sb.append(cur.e + " -> ");
            cur = cur.next;
        }
        sb.append("tail NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.toString());
    }
}
