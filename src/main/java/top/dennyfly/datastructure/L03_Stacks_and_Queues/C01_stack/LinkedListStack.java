package top.dennyfly.datastructure.L03_Stacks_and_Queues.C01_stack;

import top.dennyfly.datastructure.L04_LinkedList.LinkedList;

/**
 * @author DennyFly
 * @since 2021/9/8 15:21
 * 基于虚拟头节点链表实现的链式栈
 * #操作链表头部
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public void clear() {
        linkedList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(linkedList);
        return sb.toString();
    }

}