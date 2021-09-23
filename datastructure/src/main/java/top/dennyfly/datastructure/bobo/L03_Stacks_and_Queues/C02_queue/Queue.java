package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue;

/**
 * @author DennyFly
 * @since 2021/9/13 15:56
 * 关联leetcode问题
 * M102
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
