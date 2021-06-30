package l4_queue;

/**
 * @author DennyFly
 * @since 2020/9/15 15:25
 * 队列是双端操作，一般是从尾部入队，从首部出队
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
