package top.chitucao.datastructure.Stack_and_Queue.C01_stack;

import top.chitucao.datastructure.Array.Array;

/**
 * @author DennyFly
 * @since 2021/7/5 12:10
 * 基于动态数组实现的顺序栈
 * #操作数组尾部
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack() {
        array = new Array<>();
    }

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 入栈O(1)
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    // 出栈O(1)
    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("ArrayStack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
