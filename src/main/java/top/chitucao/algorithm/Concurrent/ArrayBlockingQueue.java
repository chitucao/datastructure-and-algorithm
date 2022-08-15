package top.chitucao.algorithm.Concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chitucao
 * @since 2022/8/12 16:07
 * 阻塞队列
 * 数组实现的有界循环队列
 * <p>
 * 参考资料
 * https://mp.weixin.qq.com/s/Vg3ccGEdmf8Qu1Mph7PF3w
 */
@Slf4j
public class ArrayBlockingQueue {

    private Object[] data;

    private int putIndex;

    private int takeIndex;

    private int size;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition notFull;

    private Condition notEmpty;

    public ArrayBlockingQueue(int capacity) {
        data = new Object[capacity];
        notEmpty = reentrantLock.newCondition();
        notFull = reentrantLock.newCondition();
    }

    public boolean put(Object obj) {

        reentrantLock.lock();

        try {

            while (size == data.length) {
                log.info("队列已满");
                notFull.await();    // 阻塞写线程
            }

            data[putIndex] = obj;

            if (++putIndex == data.length) {
                putIndex = 0;
            }
            size++;

            // 唤醒读线程
            notEmpty.signal();

            return true;
        } catch (InterruptedException e) {
            notEmpty.signal();  // 唤醒读线程
        } finally {
            reentrantLock.unlock();
        }

        return false;
    }

    public Object take() {
        // Inspection '在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
        // 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
        // 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
        // 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。' options
        reentrantLock.lock();

        try {
            while (size == 0) {
                System.out.println("队列为空");
                notEmpty.await();
            }

            Object obj = data[takeIndex];

            if (++takeIndex == data.length) {
                takeIndex = 0;
            }
            size--;

            // 唤醒写线程
            notFull.signal();

            return obj;
        } catch (Exception e) {
            notFull.signal();
        } finally {
            reentrantLock.unlock();
        }
        return null;
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        Random random = new Random(100);
        ArrayBlockingQueue queue = new ArrayBlockingQueue(5);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.put(i);
                System.out.println("生产者生产了：" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object take = queue.take();
                System.out.println("消费者消费了：" + take);
            }
        });

        thread1.start();
        thread2.start();
    }
}
