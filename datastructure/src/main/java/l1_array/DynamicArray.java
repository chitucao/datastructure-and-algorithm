package l1_array;


/**
 * @author DennyFly
 * @since 2020/9/11 18:40
 * 动态数组内存连续，从尾部添加速度较快，这里是线程不安全的实现
 */
@SuppressWarnings("ALL")
public class DynamicArray<E> {

    private E[] data;
    private int size;

    public DynamicArray() {
        this(10);
    }

    public DynamicArray(int capacity) {
        // 注意这里Object和泛型的转换
        data = (E[]) new Object[capacity];
        size = 0;
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

    // 平均复杂度是On  最好情况是尾部索引size添加，最差是首部索引0添加
    public void add(int index, E e) {
        // 注意这里是允许在末尾添加元素的，也就是index = size 的时候
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        // 在index = size 的时候触发扩容
        if (size == data.length) {
            resize(2 * data.length);
        }

        // index -> size  -1 赋值 index +1
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;

        size++;
    }

    // 这里是O1复杂度
    public void addLast(E e) {
        add(size, e);
    }

    // 这里是On复杂度
    public void addFirst(E e) {
        add(0, e);
    }

    // 根据索引查询 O1复杂度
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    // O1复杂度
    public E getFirst() {
        return get(0);
    }

    // O1复杂度
    public E getLast() {
        return get(size - 1);
    }

    // 根据索引赋值 O1复杂度
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    // 查询是否包含某元素 On复杂度
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查询并返回某个元素的索引 On复杂度
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 从数组中删除index位置的元素，并返回被删除的元素  最好的情况是尾部删除O1，最差的是首部删除On
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        E ret = data[index];

        // index+1 -> size 赋值 index
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        // 注意末尾的元素要设置为null，同时这里也可以触发垃圾回收
        data[size] = null;

        // 这里resize是1/4是为了和扩容不一样，防止复杂度震荡
        // 后面的 data.length / 2 != 0 是为了防止缩容后数组容量为空（这里其实是length=1的时候）
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    //根据元素的值删除元素 先找到该元素O1 再删除该元素平均复杂度On
    public void removeElement(E e) {
        int index = find(e);
        remove(index);
    }

    // On复杂度
    public E removeFirst() {
        return remove(0);
    }

    // O1复杂度
    public E removeLast() {
        return remove(size - 1);
    }

    //  用一个新的大小的数组接收后重新赋值 On复杂度
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
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
