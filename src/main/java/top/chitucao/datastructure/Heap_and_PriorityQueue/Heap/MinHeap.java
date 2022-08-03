package top.chitucao.datastructure.Heap_and_PriorityQueue.Heap;

import top.chitucao.datastructure.Array.Array;

/**
 * @author dennyfly
 * @since 2021/12/15 13:36
 * 小顶堆
 * <p>
 * 基于动态数组
 * 是一棵完全二叉树，每一个父节点需要小于等于子节点的值
 * 这里堆顶的元素索引是0
 * <p>
 * 基本操作
 * 1.add        添加元素；
 * 2.findMin    查询最小元素；
 * 3.removeMin  删除最小元素；
 * 4.replace    替换操作；
 * <p>
 * 内部方法
 * 1.siftUp     堆化，从下向上；
 * 2.siftDown   堆化，从上到下（取两个子孩子中比较大的那个替换）；
 * 3.parent     当前节点的父节点；
 * 4.leftChild  左孩子；
 * 5.rightChild 右孩子；
 * 6.size       使用空间；
 * 7.isEmpty    是否为空；
 */
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap() {
        this.data = new Array<>();
    }

    public MinHeap(E[] arr) {
        // 建堆
        for (int i = parent(data.getSize() - 1) / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(size() - 1);
    }

    public E findMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cannot findMin when heap is empty");
        }
        return data.getFirst();
    }

    public E removeMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("cannot removeMin when heap is empty");
        }
        E ret = findMin();
        data.swap(0, size() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    public E replace(E e) {
        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j++;
            }
            // 提前终止
            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }
            data.swap(j, k);
            k = j;
        }
    }

    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index must > 0");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must >= 0");
        }
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must >= 0");
        }
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
