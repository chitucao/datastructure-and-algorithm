package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue;

/**
 * @author DennyFly
 * @since 2021/9/6 15:38
 * 基于数组实现的循环队列
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        data = (E[]) new Object[(capacity + 1)];
        front = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {

        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        return null;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue capacity : %d, size : %d \n", getCapacity(), getSize()));
        sb.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }
}
