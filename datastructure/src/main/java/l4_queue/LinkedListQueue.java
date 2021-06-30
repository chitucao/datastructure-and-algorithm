package l4_queue;

/**
 * @author DennyFly
 * @since 2020/9/15 15:34
 * 用单向链表实现队列，这里的链表有头结点和尾结点，这里没有用到虚拟头结点
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 当head == tail == 0的时候，表示链表为空
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void enqueue(E e) {
        // 从尾部入队，这里考虑链表为空的情况
        if (tail == null) {
            tail = new Node(e);
            head = tail;    //一个元素的时候 head == tail
        } else {
            // 1.tail.next指向新的节点
            // 2.tail节点位置向后偏移一位
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        // 考虑链表为空的情况
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        //从首部出队
        // 1.将head节点标记为出队节点
        // 2.将head向后偏移一位
        // 3.出队节点的next指向null
        Node retNode = head;
        head = head.next;
        retNode.next = null;

        // 这里考虑链表只有一个元素的时候，删除后链表为空，需要同时将尾结点指向null
        if (head == null) {
            tail = null;
        }

        size--;

        return retNode.e;
    }

    @Override
    public E getFront() {
        // 考虑链表为空的情况
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
