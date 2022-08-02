package top.chitucao.datastructure.Stack_and_Queue.C02_queue;

import org.junit.Test;

import java.util.Random;

/**
 * @author DennyFly
 * @since 2021/10/18 15:42
 */
public class Main {

    @Test
    public void testArrayQueue2() {
        Queue<Integer> queue = new ArrayQueue2<>(10);

        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 2; i++) {
            queue.dequeue();
        }
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        queue.enqueue(1);
        System.out.println(queue);
    }

    @Test
    public void testDiffQueue() {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        ArrayLoopQueue<Integer> loopQueue = new ArrayLoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");
    }

    @Test
    public void testArrayQueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    @Test
    public void testLinkedListQueue() {
        Queue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.toString());
    }

    @Test
    public void testLoopQueue() {
        ArrayLoopQueue<Integer> queue = new ArrayLoopQueue<>(10);
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
