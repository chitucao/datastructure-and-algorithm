package l2_linkedlist;

/**
 * @author DennyFly
 * @since 2020/9/15 14:20
 */
public class DummyLinkedList<E> {

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

    // 注意虚拟头结点本身不存储元素，也不是相当于链表的索引0号位置
    private Node dummyHead;
    private int size;

    public DummyLinkedList() {
        // 注意这里的无参构造将dummyHead初始化了，虽然没有赋值
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表的指定索引处添加元素 并不常用 平均复杂度On 在头部添加复杂度O1，在尾部添加复杂度On
    public void add(int index, E e) {

        // 注意这里可以是等于size，相当于从末尾添加
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node prev = dummyHead;

        // 1.找到当前节点的前一个节点
        // 2.将当前节点的next指向前一个节点的后一个节点
        // 3.将前一个节点的next指向当前节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 平均复杂度On
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
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

    // On复杂度
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 是否包含某个元素
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 根据索引删除一个元素 平均复杂度On
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 1.找出待删除节点的前一个节点
        // 2.将前一个节点的next指向删除节点的next
        // 3.将删除节点的next指向null
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;    // 断开被删除节点和链表的所有联系
        size--;

        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // 根据元素的值删除一个元素
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        // 如果prev.next == null 说明是遍历到末尾也没有找到该元素
        // 这里考虑找到该元素的情况
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;    // 断开被删除节点和链表的所有联系
            size--;
        }
    }




    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");

        return res.toString();
    }
}
