package top.chitucao.tmp.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author dennyfly
 * @since 2021/11/19 15:15
 * 实现一个支持动态扩容的数组
 */
@Slf4j
public class DynamicArray<E> {

    private E[] data;

    private int size;

    public DynamicArray(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add index is illegal");
        }

        // 扩容在前
        if (size == data.length) {
            resize(2 * data.length);
        }

        // 后面的元素向后移动一位
        for (int i = size; i > index; i++) {
            data[i] = data[i - 1];
        }

        data[index] = e;
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove index is illegal");
        }

        E ret = data[index];

        // 后面的元素依次向前移动一位
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size] = null;
        size--;

        // 缩容在后
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    private void resize(int newCapacity) {
        log.info("触发扩容缩容操作 原始容量：{} 新的容量：{}", data.length, newCapacity);
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
