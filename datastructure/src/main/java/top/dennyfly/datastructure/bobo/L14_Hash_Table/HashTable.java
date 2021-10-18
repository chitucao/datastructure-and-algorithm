package top.dennyfly.datastructure.bobo.L14_Hash_Table;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author DennyFly
 * @since 2021/10/18 9:23
 * 哈希表
 * <p>
 * 基本操作
 * 1.添加key、value；
 * 2.根据key删除；
 * 3.根据key更新value（找不到对应的key会抛出异常）；
 * 4.是否包含key；
 * 5.根据key获取value；
 * <p>
 * <p>
 * 内部方法
 * 1.计算key的hashCode
 * 2.扩容缩容
 */
public class HashTable<K, V> {

    private static final int lowerTol = 2;
    private static final int upperTol = 10;
    private static final int initCapacity = 7;

    private TreeMap<K, V>[] hashTable;
    private int size;
    // 数组大小
    private int M;

    @SuppressWarnings({"SortedCollectionWithNonComparableKeys", "unchecked"})
    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<K, V>();
        }
    }

    public HashTable() {
        this(initCapacity);
    }

    private int hash(K key) {
        // 这里保证取模操作之前是一个正数
        // 0x7fffffff表示int最大值，二进制最高位为0，与操作肯定为0，所以为正数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        // 只有不包含该key的时候才添加
        if (!map.containsKey(key)) {
            map.put(key, value);
            size++;
            // 扩容
            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            // 缩容
            if (size <= lowerTol * M && M > initCapacity) {
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("key doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    @SuppressWarnings({"unchecked", "SortedCollectionWithNonComparableKeys"})
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        for (int i = 0; i < size; i++) {
            for (Map.Entry<K, V> entry : hashTable[i].entrySet()) {
                newHashTable[hash(entry.getKey())].put(entry.getKey(), entry.getValue());
            }
        }
        this.M = newM;
        this.hashTable = newHashTable;
    }
}
