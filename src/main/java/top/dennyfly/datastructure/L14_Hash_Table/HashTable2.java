package top.dennyfly.datastructure.L14_Hash_Table;

/**
 * @author dennyfly
 * @since 2021/12/13 15:40
 * 基于链表法解决冲突的散列表
 * <p>
 * #基本操作#
 * put      添加或更新元素
 * remove   根据key删除
 * get      根据key查找
 * <p>
 * #内部方法#
 * hash()   哈希函数
 * resize() 扩容缩容
 */
public class HashTable2<K, V> {

    /**
     * 散列表的默认长度
     */
    private static final int DEFAULT_INITAL_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * 散列表数组
     */
    private Entry<K, V>[] table;

    /**
     * 所有元素数量
     */
    private int size;

    /**
     * 使用的索引数量
     */
    private int used;


    /**
     * 链表节点
     */
    class Entry<K, V> {

        private K key;

        private V value;

        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    @SuppressWarnings("unchecked")
    public HashTable2() {
        table = new Entry[DEFAULT_INITAL_CAPACITY];
        this.size = 0;
        this.used = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);

        // 如果为空，创建一个哨兵节点
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> temp = table[index];

        // 新增节点
        if (temp.next == null) {
            temp.next = new Entry<>(key, value, null);
            used++;
            size++;
            // 扩容2倍
            if (used >= table.length * LOAD_FACTOR) {
                resize(table.length * 2);
            }
        } else {
            // 解决散列冲突
            do {
                temp = temp.next;

                // 覆盖
                if (temp.key == key) {
                    temp.value = value;
                    return;
                }

            } while (temp.next != null);

            temp.next = new Entry<>(key, value, temp);
            size++;
        }
    }

    public void remove(Object key) {
        int index = hash(key);
        Entry<K, V> pre = table[index];
        if (pre == null || pre.next == null) {
            return;
        }
        Entry<K, V> headNode = table[index];
        do {
            if (pre.next.key == key) {
                pre.next = pre.next.next;
                size--;
                if (headNode.next == null) {
                    used--;
                }
                if (size == table.length / 4 && table.length / 2 != 0) {
                    resize(table.length / 2);
                }
                return;
            }
            pre = pre.next;
        } while (pre.next != null);
    }

    public V get(Object key) {
        int index = hash(key);
        Entry<K, V> pre = table[index];
        if (pre == null || pre.next == null) {
            return null;
        }
        while (pre.next != null) {
            if (pre.next.key == key) {
                return pre.next.value;
            }
            pre = pre.next;
        }
        return null;
    }

    /**
     * 哈希函数
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16) % table.length;
    }

    /**
     * 扩容缩容操作
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        table = new Entry[newCapacity];
        used = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null) {
                continue;
            }
            Entry<K, V> entry = oldTable[i];
            while (entry.next != null) {
                entry = entry.next;
                int index = hash(entry.key);
                if (table[index] == null) {
                    table[index] = new Entry<>(null, null, null);
                    used++;
                }
                table[index].next = new Entry<>(entry.key, entry.value, table[index].next);
            }
        }
    }
}
