package top.dennyfly.datastructure.bobo.L07_Heap_and_Priority_Queue.C02_priority_queue;

import top.dennyfly.datastructure.bobo.L03_Stacks_and_Queues.C02_queue.Queue;
import top.dennyfly.datastructure.bobo.L07_Heap_and_Priority_Queue.C01_heap.MaxHeap;

/**
 * @author DennyFly
 * @since 2021/10/12 17:28
 * 基于最大堆实现的优先队列
 * 取最大元素出队，从数组的尾部入队然后执行siftUp操作
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
