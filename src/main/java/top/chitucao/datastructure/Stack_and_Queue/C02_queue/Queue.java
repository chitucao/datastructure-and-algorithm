package top.chitucao.datastructure.Stack_and_Queue.C02_queue;

/**
 * @author DennyFly
 * @since 2021/9/13 15:56
 * 队列
 * #要求从队尾入队，队首出队
 * #数组实现的叫顺序队列，链表实现的叫链式队列
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
