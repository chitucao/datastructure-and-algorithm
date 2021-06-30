package l3_stack;

import l2_linkedlist.DummyLinkedList;

/**
 * @author DennyFly
 * @since 2020/9/15 15:18
 * 链表实现栈，都是从首部操作
 */
public class LinkedListStack<E> implements Stack<E> {

    private DummyLinkedList<E> dummyLinkedList;

    public LinkedListStack() {
        dummyLinkedList = new DummyLinkedList<>();
    }

    @Override
    public int getSize() {
        return dummyLinkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return dummyLinkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        dummyLinkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return dummyLinkedList.removeFirst();
    }

    @Override
    public E peek() {
        return dummyLinkedList.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(dummyLinkedList);
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
