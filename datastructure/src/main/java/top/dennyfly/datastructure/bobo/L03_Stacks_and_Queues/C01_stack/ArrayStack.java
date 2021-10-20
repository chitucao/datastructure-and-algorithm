package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C01_stack;

import top.dennyfly.datastructure.bobo.L02_Arrays.Array;

/**
 * @author DennyFly
 * @since 2021/7/5 12:10
 * 基于动态数组实现的栈（顺序栈）
 * #入栈和出栈操作数组尾部
 * #基于动态数组的方式可以自动扩容
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
