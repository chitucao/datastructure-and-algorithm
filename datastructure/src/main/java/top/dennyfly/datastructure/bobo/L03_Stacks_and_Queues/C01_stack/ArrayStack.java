package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C01_stack;

import top.dennyfly.datastructure.bobo.L02_Arrays.Array;

/**
 * @author DennyFly
 * @since 2021/7/5 12:10
 * 基于数组实现的栈
 * 入栈和出栈操作数组尾部
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> dynamicArray;

    public ArrayStack() {
        dynamicArray = new Array<>();
    }

    public ArrayStack(int capacity) {
        dynamicArray = new Array<>(capacity);
    }


    @Override
    public int getSize() {
        return dynamicArray.getSize();
    }

    @Override
    public boolean isEmpty() {
        return dynamicArray.isEmpty();
    }

    @Override
    public void push(E e) {
        dynamicArray.addLast(e);
    }

    @Override
    public E pop() {
        return dynamicArray.removeLast();
    }

    @Override
    public E peek() {
        return dynamicArray.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < dynamicArray.getSize(); i++) {
            res.append(dynamicArray.get(i));
            if (i != dynamicArray.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
