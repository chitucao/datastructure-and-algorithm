package top.dennyfly.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author DennyFly
 * @since 2021/10/25 10:31
 * <p>
 * 内部方法
 * 1.print 打印数组
 */
public class Main {

    // 执行6次后有序
    private static int[] arr1 = {4, 5, 6, 3, 2, 1};

    // 执行4次后提前有序
    private static int[] arr2 = {3, 5, 4, 1, 2, 6};

    // 堆排序数组
    private static int[] arr3 = {7, 4, 5, 6, 3, 2, 1};

    private static int[] arr4 = {7,1,2,3,4,5,6};

    @Test
    public void testBubbleSort() {
        int[] arr = arr2;
        BubbleSort.bubbleSort2(arr);
        print(arr, "bubbleSort2");
    }

    @Test
    public void testInsertionSort() {
        int[] arr = arr1;
        InsertionSort.insertionSort1(arr);
        print(arr, "insertionSort1");
    }

    @Test
    public void testSelectionSort() {
        int[] arr = arr1;
        SelectionSort.selectionSort1(arr);
        print(arr, "selectionSort1");
    }

    @Test
    public void testMergeSort() {
        int[] arr = arr1;
        MergeSort.mergeSort1(arr1);
        print(arr, "mergeSort1");
    }

    @Test
    public void testQuickSort() {
        int[] arr = arr1;
        QuickSort.quickSort1(arr1);
        print(arr, "quickSort1");
    }

    @Test
    public void testHeapSort() {
        int[] arr = arr3;
        HeapSort.heapSort1(arr);
        print(arr, "heapSort1");

        HeapSort.heapSort2(arr);
        print(arr, "heapSort2");
    }


    /**
     * 单位：毫秒
     * <p>
     * 10万级别
     * JDK自带排序：24
     * 选择排序1：3415
     * 冒泡排序1：12042
     * 冒泡排序2：11838
     * 插入排序1：516
     * 归并排序1：15
     * <p>
     * 1亿级别
     * JDK自带排序：9951
     * 归并排序1：13572
     * 快速排序1：9126
     * 快速排序2：9080
     */
    @Test
    public void compareSort() {
        int num = 100000000;
        int[] arr = new int[num];
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            arr[i] = r.nextInt(num);
        }

        long startTime;
        long endTime;

        // java 自带的排序算法
        // 1亿（100000000）的数据大约需要10秒（9645毫秒）
        startTime = System.currentTimeMillis();
        Arrays.sort(Arrays.copyOf(arr, arr.length));
        endTime = System.currentTimeMillis();
        System.out.println("JDK自带排序：" + (endTime - startTime));


        // 选择、冒泡、插入排序十万以内的时间复杂度还可以接受，达到百万级别的时候非常慢
//        startTime = System.currentTimeMillis();
//        SelectionSort.selectionSort1(Arrays.copyOf(arr, arr.length));
//        endTime = System.currentTimeMillis();
//        System.out.println("选择排序1：" + (endTime - startTime));
//
//        startTime = System.currentTimeMillis();
//        BubbleSort.bubbleSort1(Arrays.copyOf(arr, arr.length));
//        endTime = System.currentTimeMillis();
//        System.out.println("冒泡排序1：" + (endTime - startTime));
//
//        startTime = System.currentTimeMillis();
//        BubbleSort.bubbleSort2(Arrays.copyOf(arr, arr.length));
//        endTime = System.currentTimeMillis();
//        System.out.println("冒泡排序2：" + (endTime - startTime));
//
//        startTime = System.currentTimeMillis();
//        InsertionSort.insertionSort1(Arrays.copyOf(arr, arr.length));
//        endTime = System.currentTimeMillis();
//        System.out.println("插入排序1：" + (endTime - startTime));


        startTime = System.currentTimeMillis();
        MergeSort.mergeSort1(Arrays.copyOf(arr, arr.length));
        endTime = System.currentTimeMillis();
        System.out.println("归并排序1：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        QuickSort.quickSort1(Arrays.copyOf(arr, arr.length));
        endTime = System.currentTimeMillis();
        System.out.println("快速排序1：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        QuickSort.quickSort2(Arrays.copyOf(arr, arr.length));
        endTime = System.currentTimeMillis();
        System.out.println("快速排序2：" + (endTime - startTime));
    }


    private void print(int[] arr, String method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method + " ");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}
