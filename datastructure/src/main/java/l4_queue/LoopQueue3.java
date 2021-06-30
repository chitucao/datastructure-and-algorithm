package l4_queue;

/**
 * @author DennyFly
 * @since 2020/9/15 18:19
 * 数组实现队列，使用一个额外空间，不定义也不用维护size变量，size变量是实时算出来的
 */
@SuppressWarnings("ALL")
public class LoopQueue3<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;


    public LoopQueue3() {
        this(10);
    }

    public LoopQueue3(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
    }

    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return front < tail ? tail - front : tail - front + data.length;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //考虑队列满的情况 扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        // 考虑为空的情况不能出队
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;

        //考虑缩容的情况
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(front + 1) % data.length];
        }

        data = newData;
        front = 0;
        tail = getSize();
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue3<Integer> queue = new LoopQueue3<>();
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
