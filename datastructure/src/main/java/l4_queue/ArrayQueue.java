package l4_queue;

import l1_array.DynamicArray;

/**
 * @author DennyFly
 * @since 2020/9/15 15:27
 * 数组实现队列，队列是双端操作，数组实现是从尾部入队，从首部出队
 * 存在的问题是当首部有剩余空间的时候，还是只能从尾部添加，浪费了空间，可以用数组实现的循环队列解决这个问题
 */
public class ArrayQueue<E> implements Queue<E> {

    private DynamicArray<E> dynamicArray;

    public ArrayQueue(int capacity) {
        dynamicArray = new DynamicArray<>(capacity);
    }

    public ArrayQueue() {
        dynamicArray = new DynamicArray<>();
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
    public void enqueue(E e) {
        dynamicArray.addLast(e);
    }

    @Override
    public E dequeue() {
        return dynamicArray.removeFirst();
    }

    @Override
    public E getFront() {
        return dynamicArray.getFirst();
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < dynamicArray.getSize(); i++) {
            res.append(dynamicArray.get(i));
            if (i != dynamicArray.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
