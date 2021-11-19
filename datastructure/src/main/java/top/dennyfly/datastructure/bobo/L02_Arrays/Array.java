package top.dennyfly.datastructure.bobo.L02_Arrays;

/**
 * @author DennyFly
 * @since 2021/10/18 15:49
 * 基于数组实现的动态数组
 * <p>
 * 特性
 * 1.连续内存，大小不可变，随机访问；
 * 2.插入删除O(n)，基于索引查询O(1)，基于值查找O(n)；
 * <p>
 * <p>
 * 1.根据索引添加元素（顺序后移O(n)，特殊场景也可以将原索引处元素放到最后然后插入O(1)）；
 * 2.
 * <p>
 * 特殊场景
 * 1.添加操作如果不考虑顺序性可以将原有元素放到最后，然后插入新元素O(1)；
 * 2.删除操作可以先记录需要删除的元素，等到数组的空间不够的时候再统一删除，避免频繁的数据移动，举例：JVM标记清除算法；
 * 3.
 * <p>
 * 一些问题
 * 1.为什么索引从0开始而不是从1开始？
 * 解答：1.因为下标更准确的表示是offset，计算内存地址的时候，如果从1开始，计算首地址等需要多出一次减法运算；2.C语言历史原因；
 * 2.
 */
public class Array<E> {

    private E[] data;

    private int size;

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;

        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Require index >=0 and index < size");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Require index >=0 and index < size");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    // 将数组空间的容量变成newCapacity大小
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    // 交换两个位置的元素
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("index must >0 && < size");
        }
        if (i == j) {
            return;
        }

        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d \n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
