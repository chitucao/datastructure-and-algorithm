package l3_stack;

import l1_array.DynamicArray;

/**
 * @author DennyFly
 * @since 2020/9/15 15:09
 * 数组实现栈，都是从尾端操作
 */
public class ArrayStack<E> implements Stack<E> {

    private DynamicArray<E> dynamicArray;

    public ArrayStack() {
        dynamicArray = new DynamicArray<>();
    }

    public ArrayStack(int capacity) {
        dynamicArray = new DynamicArray<>(capacity);
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

    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
