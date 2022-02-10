package top.dennyfly.tmp.A01_Array;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2021/11/19 15:10
 */
public class Main {

    @Test
    public void testArray() {
        int capacity = 10;
        Array<Integer> arr = new Array<>(capacity);
        for (int i = 0; i < 5; i++) {
            arr.add(i,i);
        }
        System.out.println(arr);

        arr.add(0,10);
        arr.add(2,5);
        System.out.println(arr);

        arr.add(0,1);
        arr.add(0,2);
        arr.add(0,3);
        System.out.println(arr);

//        arr.add(0,10);
    }

    @Test
    public void testDynamicArray(){
        DynamicArray<Integer> arr = new DynamicArray<>(4);
        for (int i = 0; i < 9; i++) {
            arr.add(i,i);
        }
        System.out.println(arr);

        for (int i = 0; i < 5; i++) {
            arr.remove(0);
        }
        System.out.println(arr);
    }
}
