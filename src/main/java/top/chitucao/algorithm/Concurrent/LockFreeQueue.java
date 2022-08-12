package top.chitucao.algorithm.Concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author chitucao
 * @since 2022/8/11 18:18
 * 无锁队列
 * <p>
 * 数组实现的有界队列
 * 这里用到的是数组我用的是AtomicReferenceArray类，当然你也可以用AtomicIntegerArray
 */
public class LockFreeQueue {

    private AtomicReferenceArray<Integer> data;

    private AtomicInteger head, tail;

    private static final Integer EMPTY = null;

    public LockFreeQueue(int capacity) {
        data = new AtomicReferenceArray<>(capacity + 1);
        head = new AtomicInteger(0);
        tail = new AtomicInteger(0);
    }

    public boolean enqueue(Integer element) {
        int index = (tail.get() + 1) % data.length();

        // 校验队列是否已满
        if (index == head.get() % data.length()) {
            System.out.println("当前队列已满，" + element + "无法入队");
            return false;
        }

        // CAS加入
        while (!data.compareAndSet(index, EMPTY, element)) {
            return enqueue(element);
        }

        // 移动尾指针
        tail.incrementAndGet();

        return true;
    }

    public Integer poll() {
        if (head.get() == tail.get()) {
            System.out.println("队列为空");
            return null;
        }

        int index = (head.get() + 1) % data.length();
        Integer element = data.get(index);
        if (element == null) {
            return poll();
        }

        while (!data.compareAndSet(index, element, EMPTY)) {
            return poll();
        }

        // 移动头指针
        return element;
    }

    public static void main(String[] args) {

        LockFreeQueue queue = new LockFreeQueue(5);
        queue.enqueue(1);
        queue.enqueue(2);
        Integer poll = queue.poll();
        System.out.println(poll);

    }
}
