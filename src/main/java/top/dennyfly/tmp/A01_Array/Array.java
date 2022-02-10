package top.dennyfly.tmp.A01_Array;

import java.util.Arrays;

/**
 * @author DennyFly
 * @since 2021/11/19 14:45
 * 实现一个大小固定的有序数组，支持动态增删改操作
 * #不支持扩容
 */
public class Array<E> {

    private E[] data;

    private int size;

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 指定索引获取
     */
    public E find(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[index];
    }

    /**
     * 指定索引插入
     */
    public void add(int index, E e) {
        if (size == data.length) {
            throw new IllegalArgumentException("array is full");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add index is illegal");
        }

        // 后面的元素依次向后移动一位
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    /**
     * 指定索引删除
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove index is illegal");
        }

        E ret = data[index];
        // 后面的元素依次向前移动一位
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return ret;
    }

    /**
     * 指定位置更新
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set index is illegal");
        }
        data[index] = e;
    }


    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
