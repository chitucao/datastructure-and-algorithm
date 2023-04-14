package top.chitucao.algorithm.Search;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/26 17:14
 */
public class Main {

    private static final int[] arr = {1, 3, 7, 8, 11, 24, 27, 32, 34, 67, 123, 156};

    private static final int[] arr2 = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};

    private static final int[] arr3 = {3, 4, 6, 7, 10};

    private static final int[] arr4 = {3, 5, 6, 8, 9, 10};

    @Test
    public void testTravesal() {
        int index = BinarySearch.binarySearch1(arr, 11);
        System.out.println("binarySearch1: " + index);
    }

    @Test
    public void testRecurse() {
        int index = BinarySearch.binarySearch2(arr, 11);
        System.out.println("binarySearch2: " + index);
    }

    @Test
    public void testFindEleFirst() {
        int index = BinarySearch.binarySearch3(arr, 11);
        System.out.println("binarySearch3: " + index);

        int index2 = BinarySearch.binarySearch2(arr2, 8);
        System.out.println("binarySearch3: " + index2);

        int index3 = BinarySearch.binarySearch3(arr2, 8);
        System.out.println("binarySearch3: " + index3);

        int index4 = BinarySearch.binarySearch4(arr2, 8);
        System.out.println("binarySearch4: " + index4);
    }

    @Test
    public void testFindEleLast() {
        int index = BinarySearch.binarySearch5(arr2, 8);
        System.out.println("binarySearch5: " + index);

        int index2 = BinarySearch.binarySearch6(arr2, 8);
        System.out.println("binarySearch6: " + index2);
    }

    @Test
    public void testFindGEFirst() {
        int index = BinarySearch.binarySearch7(arr3, 6);
        System.out.println("binarySearch7: " + index);
    }

    @Test
    public void testFindLELast() {
        int index = BinarySearch.binarySearch8(arr4, 9);
        System.out.println("binarySearch8: " + index);
    }
}
