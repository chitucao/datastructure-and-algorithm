package top.dennyfly.datastructure.L03_Stacks_and_Queues.C02_queue;

/**
 * @author DennyFly
 * @since 2021/9/6 15:38
 * 基于动态数组实现的循环队列
 * #维护front和tail两个指针
 * #会浪费一个空间，front==tail表示队列为空，tail本身不存储数据，tail+1%length == front表示队列满
 * <p>
 * 基本操作
 * 1.getSize        数据大小
 * 2.isEmpty        是否为空
 * 3.enqueue        入队
 * 4.dequeue        出队
 * 5.getCapacity    获取容量
 * <p>
 * 内部方法
 * 1.resize         扩容（*2，操作前）缩容（1/4，操作后）操作
 * 2.getCapacity    获取队列的容量
 * 3.toString       打印操作
 */
public class ArrayLoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;  // 注意这里手动维护一个size，而不是根据front和tail的指针判断

    public ArrayLoopQueue() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayLoopQueue(int capacity) {
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
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot getFront from an empty queue.");
        }
        return data[front];
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        // +1
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 循环次数
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
