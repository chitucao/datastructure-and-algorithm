package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C01_stack;

/**
 * @author DennyFly
 * @since 2021/10/20 16:17
 * 基于普通数组实现的栈
 * #入栈出栈都是操作数组的尾部；
 * #普通数组实现的栈本身有capacity的容量限制
 */
public class ArrayStack2<E> implements Stack<E> {

    private E[] data;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack2(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 入栈O(1)
    @Override
    public void push(E e) {
        if (size == capacity) {
            throw new IllegalArgumentException("cann't push,stack is full");
        }
        data[size] = e;
        size++;
    }

    // 出栈O(1)
    @Override
    public E pop() {
        if (size == 0) {
            return null;
        }
        E ret = data[size - 1];
        size--;
        return ret;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return data[size - 1];
    }
}
