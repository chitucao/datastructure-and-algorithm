package top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue;

/**
 * @author DennyFly
 * @since 2021/10/22 15:08
 * 基于普通数组实现的队列
 * #有数组容量的限制，不支持扩容，不支持移动元素
 * #维护front和tail两个指针
 */
public class ArrayQueue2<E> implements Queue<E> {

    private E[] data;
    private int capacity;
    private int front, tail;


    @SuppressWarnings("unchecked")
    public ArrayQueue2(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.front = this.tail = 0;
    }

    @Override
    public int getSize() {
        return tail - front;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    // 注意在入队的时候统一触发扩容
    @Override
    public void enqueue(E e) {
        if (tail == capacity) {
//            throw new IllegalArgumentException("queue is full");
            if (tail - front == capacity) {
                throw new IllegalArgumentException("queue is full");
            } else {
                shiftLeft();
                System.out.println(this);
            }
        }
        data[tail] = e;
        tail++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E res = data[front];
        front++;
        return res;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return data[front];
    }

    private void shiftLeft() {
        for (int i = front; i < tail; i++) {
            data[i - front] = data[i];
        }
        // 注意先后顺序
        tail = tail - front;
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayQueue2 frontIndex:" + front + " tailIndex:" + tail + " \nfront ");
        for (int i = front; i < tail; i++) {
            sb.append(data[i] + "-> ");
        }
        sb.append("tail");
        return sb.toString();
    }
}
