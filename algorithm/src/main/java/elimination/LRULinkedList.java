package elimination;

/**
 * @author DennyFly
 * @since 2021/10/19 11:08
 * 基于链表实现LRU缓存策略
 * #本链表带虚拟头节点，维护capacity容量字段；
 * #最新的节点放到链表头部
 * <p>
 * 插入一个元素的几种情况
 * 1.链表中包含该元素 ||删除该元素，然后将该元素加入到链表头部；
 * 2.链表中没有该元素
 * -a.链表未满，直接加入到链表头部
 * -b.链表已满，删除链表尾部元素，然后加入到链表头部
 * <p>
 * 基本操作
 * 1.lru 最新的节点放在链表头部
 * <p>
 * 内部方法
 * 1.findPreNode        查找元素的前一个节点；
 * 2.deleteByPreNode    根据前一个节点删除当前节点；
 * 3.addToHead          插入到头节点
 * 4.deleteEndNode      删除最后节点
 * 5.toString           重写该方法
 */
public class LRULinkedList<E> {

    class Node<E> {
        private E e;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    private static final int DEFAULT_CAPACITY = 10;

    private Node<E> dummyHead;
    private int capacity;
    private int size;

    public LRULinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public LRULinkedList(int capacity) {
        this.dummyHead = new Node<>();
        this.size = 0;
        this.capacity = capacity;
    }

    public void lru(E e) {
        Node<E> preNode = findPreNode(e);
        if (preNode != null) {
            deleteByPreNode(preNode);
            addToHead(e);
        } else {
            if (size >= capacity) {
                deleteEndNode();
            }
            addToHead(e);
        }
    }

    /**
     * 查找前一个节点
     */
    private Node<E> findPreNode(E e) {
        Node<E> preNode = dummyHead;
        while (preNode.next != null) {
            if (preNode.next.e == e) {
                return preNode;
            }
            preNode = preNode.next;
        }
        return null;
    }

    /**
     * 根据前一个节点删除当前节点
     */
    private void deleteByPreNode(Node<E> preNode) {
        if (preNode != null) {
            Node<E> temp = preNode.next;
            preNode.next = temp.next;

            temp.next = null;
            size--;
        }
    }

    /**
     * 插入到头节点
     */
    private void addToHead(E e) {
        dummyHead.next = new Node<>(e, dummyHead.next);
        size++;
    }

    /**
     * 删除尾节点
     */
    private void deleteEndNode() {
        Node<E> cur = dummyHead;
        if (cur.next == null) {
            return;
        }

        while (cur.next.next != null) {
            cur = cur.next;
        }

        cur.next = null;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LRUListNode: ");
        Node<E> cur = dummyHead;
        while (cur.next != null) {
            sb.append(cur.next.e + "-> ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
