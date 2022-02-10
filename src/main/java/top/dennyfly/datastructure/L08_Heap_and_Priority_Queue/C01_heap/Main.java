package top.dennyfly.datastructure.L08_Heap_and_Priority_Queue.C01_heap;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/18 15:09
 * 测试用例
 */
public class Main {

    @Test
    public void testMaxHeap() {
        int[] arr = {62, 41, 30, 28, 16, 22, 13, 19, 17, 15};

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i : arr) {
            maxHeap.add(i);
        }
        // 62, 41, 30, 28, 16, 22, 13, 19, 17, 15
        System.out.println(maxHeap);

        // 62, 52, 30, 28, 41, 22, 13, 19, 17, 15, 16
        maxHeap.add(52);
        System.out.println(maxHeap);

        // 52, 41, 30, 28, 16, 22, 13, 19, 17, 15
        maxHeap.removeMax();
        System.out.println(maxHeap);

        // 41, 28, 30, 19, 16, 22, 13, 15, 17
        maxHeap.removeMax();
        System.out.println(maxHeap);
    }

    @Test
    public void testMinHeap() {
        int[] arr = {10, 20, 15, 25, 50, 30, 40, 35, 45};

        MinHeap<Integer> minHeap = new MinHeap<>();
        for (int i : arr) {
            minHeap.add(i);
        }
        // [10, 20, 15, 25, 50, 30, 40, 35, 45]
        System.out.println(minHeap);

        // 10,16,15,25,20,30,40,35,45,50
        minHeap.add(16);
        System.out.println(minHeap);

        // 15, 16, 30, 25, 20, 50, 40, 35, 45
        minHeap.removeMin();
        System.out.println(minHeap);

        // 16, 20, 30, 25, 45, 50, 40, 35
        minHeap.removeMin();
        System.out.println(minHeap);
    }
}
