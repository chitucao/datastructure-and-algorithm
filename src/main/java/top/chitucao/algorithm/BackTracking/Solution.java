package top.chitucao.algorithm.BackTracking;

import java.util.Arrays;

/**
 * @author dennyfly
 * @since 2021/12/10 17:15
 */
public class Solution {

    private int[] data;
    private int size;

    public Solution(int k) {
        this.size = 0;
        this.data = new int[k];
    }

    public boolean insertFront(int value) {
        return add(0, value);
    }

    public boolean insertLast(int value) {
        return add(size, value);
    }

    public boolean deleteFront() {
        return delete(0);
    }

    public boolean deleteLast() {
        return delete(size - 1);
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[0];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    private boolean delete(int index) {
        if (size == 0) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        return true;
    }

    private boolean add(int index, int value) {
        if (size == data.length) {
            return false;
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;

        return true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution(3);
        solution.insertFront(1);
        solution.insertLast(2);
        solution.insertFront(3);

        System.out.println(Arrays.toString(solution.data));

        solution.deleteFront();

        System.out.println(solution.isEmpty());
        System.out.println(solution.isFull());
    }
}
