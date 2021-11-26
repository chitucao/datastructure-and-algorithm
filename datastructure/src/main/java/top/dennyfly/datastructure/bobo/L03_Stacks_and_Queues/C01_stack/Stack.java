package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C01_stack;

/**
 * @author DennyFly
 * @since 2021/9/13 15:56
 * 关联leetcode文件
 * S20
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();

    void clear();
}
