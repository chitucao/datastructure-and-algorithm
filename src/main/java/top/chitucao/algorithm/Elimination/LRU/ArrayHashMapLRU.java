package top.chitucao.algorithm.Elimination.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DennyFly
 * @since 2021/10/19 14:42
 * 基于数组和哈希表实现的LRU
 * <p>
 * 最新的元素放在数组头部
 * 使用一个hash表维护元素的值和在数组上索引的对应关系
 * 这里的数组只有value属性
 * <p>
 * 基本操作
 * 1.offer 添加一个元素；
 * <p>
 * 内部方法
 * 1.removeLast     删除末尾元素，其实只是维护了size的大小和map关系；
 * 2.moveToHead     将节点移动到数组头部；
 * 3.addToHead      将元素添加到头部，这里需要将0到末尾（size-1）的元素全部右移；
 * 4.rightSift      将0到指定索引的前一位元素右移，同时维护map中的元素值和索引的对应关系；
 */
public class ArrayHashMapLRU<E> {

    private static final int DEFAULT_CAPACITY = 7;

    private int capacity;
    private int size;
    private E[] data;
    private Map<E, Integer> map;

    @SuppressWarnings("unchecked")
    public ArrayHashMapLRU(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (E[]) new Object[capacity];
        this.map = new HashMap<>();
    }

    public void offer(E e) {
        if (e == null) {
            throw new IllegalArgumentException("null value is not support");
        }
        Integer index = map.get(e);
        // 如果包含该元素，移动到数组头部
        if (index != null) {
            moveToHead(e, index);
        } else {
            // 如果不包含元素，同时数组满了，删除最后一个元素，然后放到头部
            if (size == capacity) {
                removeLast();
                addToHead(e);
            } else {
                // 直接添加到数组头部
                addToHead(e);
            }
        }
    }

    /**
     * 删除最后一个元素，维护下map的对应关系和size大小
     */
    private void removeLast() {
        map.remove(data[size - 1]);
        size--;
    }

    /**
     * 将节点添加到数组的头部
     */
    private void addToHead(E e) {
        rightSift(size);
        data[0] = e;
        map.put(e, 0);
        size++;
    }

    /**
     * 将节点移动到数组头部
     */
    private void moveToHead(E e, int index) {
        rightSift(index);
        data[0] = e;
        map.put(e, 0);
    }

    /**
     * 0到index-1的元素右移，同时更新map中的值和索引关系
     */
    private void rightSift(Integer index) {
        // 注意这里需要从后向前
        for (int i = index - 1; i >= 0; i--) {
            data[i + 1] = data[i];
            map.put(data[i], i + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LRU2: ");
        for (E e : data) {
            sb.append(e + " ");
        }
        sb.append("end");
        return sb.toString();
    }
}
