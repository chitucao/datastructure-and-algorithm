package l3_stack;

/**
 * @author DennyFly
 * @since 2020/9/15 15:10
 * 栈是先进后出，添加和删除都是从一端操作
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
