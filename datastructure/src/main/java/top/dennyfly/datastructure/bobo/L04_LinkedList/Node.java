package top.dennyfly.datastructure.bobo.L04_LinkedList;

/**
 * @author DennyFly
 * @since 2021/9/6 17:29
 */
public class Node<E> {

    public E e;
    public Node<E> next;

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
