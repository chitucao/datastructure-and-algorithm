package top.chitucao.algorithm.Math;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static Object lock = new Object();
    static AtomicInteger num = new AtomicInteger(1);


    public static void main(String[] args) {
        Runnable r1 = () -> {
            synchronized (lock) {
                try {

                    if (num.get() <= 4) {
                        System.out.println(Thread.currentThread().getName() + "打印：" + num);
                        num.getAndIncrement();
                    }
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.notify();
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (lock) {
                try {
                    if (num.get() <= 4) {
                        System.out.println(Thread.currentThread().getName() + "打印：" + num);
                        num.getAndIncrement();
                    }
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.notify();
                }
            }
        };

        while (true) {
            new Thread(r1, "奇数线程").start();
            new Thread(r2, "偶数线程").start();
        }
    }
}
