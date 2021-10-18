package top.dennyfly.datastructure.bobo.L08_Heap_and_Priority_Queue.C01_heap;

import top.dennyfly.datastructure.bobo.L02_Arrays.Array;

/**
 * @author DennyFly
 * @since 2021/10/18 15:05
 * 最大堆
 * 基于compare的方式，也可以实现最小堆
 * <p>
 * <p>
 * 基本操作
 * 1.添加元素；
 * 2.查询最大元素；
 * 3.删除最大元素；
 * 4.替换操作（替换最大元素）
 * <p>
 * 内部方法
 * 1.siftUp；
 * 2.siftDown；
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;


    public MaxHeap() {
        data = new Array<>();
    }

//    // 将任意数组转换成堆，heapify操作，nlogn
//    public MaxHeap(E[] arr) {
//        data = new Array<>(arr);
//        for (int i = parent(arr.length - 1); i >= 0; i--) {
//            siftDown(i);
//        }
//    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 添加元素（末尾添加，会执行shift up操作）
    public void add(E e) {
        data.addLast(e);
        siftUp(size() - 1);
    }

    // 查询最大元素
    public E findMax() {
        if (size() == 0) {
            throw new IllegalArgumentException("cann't findMax when heap is empty");
        }
        return data.get(0);
    }

    // 删除最大元素 （交换首尾元素后末尾删除，会执行shift down操作）
    public E removeMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 比较并替换第一个元素和下面元素的位置
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // j取两个孩子节点中的最大值
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    // 比较并替换最后一个元素和上面元素的位置
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 最大元素替换操作 （最大元素替换后siftdown 复杂度：logn）
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    // 最大元素替换操作2 （removeMax后 siftDown，再添加元素 siftUp 复杂度：2xlogn）
//    public E replace2(E e) {
//        E ret = removeMax();
//        add(e);
//        return ret;
//    }

    // 获取当前节点的父节点
    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index <=0 doesn't have parent!");
        }
        return (index - 1) / 2;
    }

    // 当前节点左孩子
    private int leftChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must > 0!");
        }
        return index * 2 + 1;
    }

    // 当前节点右孩子
    private int rightChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must > 0!");
        }
        return 2 * index + 2;
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
