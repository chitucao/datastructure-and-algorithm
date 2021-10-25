package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue;

/**
 * @author DennyFly
 * @since 2021/10/25 9:11
 */
public class ArrayLoopQueueTemp<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    // 注意这里手动维护一个
    private int size;

    public ArrayLoopQueueTemp() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayLoopQueueTemp(int capacity) {
        this.data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
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
            resize(2 * size);
        }
        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length;
    }


    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cann't dequeque from empty queue");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cann't getFront from empty queue");
        }
        return data[front];
    }

    private int getCapacity() {
        return data.length - 1;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayLoopQueueTemp front ");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if(i!=size-1){
                sb.append(",");
            }
        }
        sb.append(" tail");
        return sb.toString();
    }
}
