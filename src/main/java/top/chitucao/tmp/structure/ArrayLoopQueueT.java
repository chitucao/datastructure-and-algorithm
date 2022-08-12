package top.chitucao.tmp.structure;

import top.chitucao.datastructure.Stack_and_Queue.Queue.ArrayLoopQueue;
import top.chitucao.datastructure.Stack_and_Queue.Queue.Queue;

/**
 * @author chitucao
 * @since 2022/8/12 14:36
 */
public class ArrayLoopQueueT<E> implements Queue<E> {

    private E[] data;

    private int head, tail;

    private int size;

    public ArrayLoopQueueT() {
        this(10);
    }


    @SuppressWarnings("unchecked")
    public ArrayLoopQueueT(int capacity) {
        this.data = (E[]) new Object[capacity + 1]; // 浪费一个空间
        head = tail = 0;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public void enqueue(E e) {
        // 扩容
        if ((tail + 1) % data.length == head) {
            resize(2 * getCapacity());
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }


    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E ret = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            return null;
        }

        return data[head];
    }


    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + head) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }

    private int getCapacity() {
        return data.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue capacity : %d, size : %d \n", getCapacity(), getSize()));
        sb.append("front [");
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayLoopQueue<Integer> queue = new ArrayLoopQueue<>(10);
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
