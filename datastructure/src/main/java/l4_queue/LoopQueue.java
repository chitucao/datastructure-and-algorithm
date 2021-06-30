package l4_queue;

/**
 * @author DennyFly
 * @since 2020/9/15 16:27
 * 用数组实现循环队列，这里的数组维护一个头节点的指针front和一个尾节点的指针tail
 * （注意尾结点总是指向一个空节点，但是那个空间不会被使用，尾结点的下一个节点为front的时候就表示队列满了）
 * <p>
 * 循环队列为什么总是空出一个位置？
 * 主要是为了区别队列为空和队列满这两种情况，仅front == tail是区分不出这两种情况的
 * 参考资料：https://blog.csdn.net/weixin_41845059/article/details/107222179
 */
@SuppressWarnings("ALL")
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;   //也可以不声明size实现所有逻辑，但是会复杂一点

    // 初始化的时候front==tail，代表队列为空
    // 这里注意初始化的时候需要多一个位置
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    // 这里注意front == tail的时候表示队列为空
    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    // 注意这里队列为空的判断是front == tail
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        // 考虑队列满的情况，这里是只剩下尾节点那个元素的时候 扩容两倍
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        // 1 将e赋值给尾部元素
        // 2 尾部元素的指针后移一位
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        // 1.找到首部作为待删除元素
        // 2.将待删除元素的位置设置为null
        // 3.front向后偏移一位
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
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];

        // 从循环队列的第一个元素依次加入新的数组
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;  //这里注意尾结点是最后一个元素的后一位
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
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

        LoopQueue<Integer> queue = new LoopQueue<>(5);
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
