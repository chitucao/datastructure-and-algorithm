package top.dennyfly.datastructure.L15_Skip_List;

import java.util.Comparator;

/**
 * 跳表
 * <p>
 * #成员变量
 * <li/>MAX_LEVEL   最大层数限制
 * <li/>P           向上抛硬币的随机数
 * <li/>level       有效层数
 * <p>
 * #基本方法
 * 0.size
 * 0.isEmpty
 * 1.get    根据key获取value O(log(n))
 * 2.put    添加key、value对 O(log(n))
 * 3.remove 根据key删除      O(log(n))
 * <p>
 * #内部方法
 * 1.randomLevel    获取随机层数
 * 2.keyCheck       校验key的有效性（不能为null）
 * 3.compare        比较两个key的大小
 */
@SuppressWarnings("unchecked")
public class SkipList<K, V> {

    /**
     * 跳表节点
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;  // 这里是一个数组（方便下标获取）

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }

        @Override
        public String toString() {
            return key + ":" + value + "_" + nexts.length;
        }
    }


    // 最大层数限制
    public static final int MAX_LEVEL = 32;
    // 抛硬币的概率
    public static final double P = 0.25;
    // 当前元素个数
    private int size;
    // 比较器，定义key的排序规则
    private Comparator<K> comparator;

    /**
     * 有效层数
     */
    private int level;

    /**
     * 头节点，不存放任何K、V
     */
    private Node<K, V> first;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        // 初始化头节点为最大层数
        this.first = new Node<>(null, null, MAX_LEVEL);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
            // 执行到这里，表示进入下一层
        }
        return null;
    }

    public V put(K key, V value) {
        keyCheck(key);

        Node<K, V> node = first;
        Node[] prevs = new Node[level]; // 计入插入位置的前驱节点
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }

            // 如果找到该元素，执行更新逻辑
            if (cmp == 0) {
                V oldV = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldV;
            }
            prevs[i] = node;
        }

        // 到这里说明到达最后一层都没有找到,新建节点插入
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                // first指向高出的层数
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }

        size++; //维护size

        // 更新有效层数
        level = Math.max(level, newLevel);

        return null;
    }

    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] prevs = new Node[level];
        boolean exist = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                exist = true;
            }
            prevs[i] = node;
        }

        // 到这里说明到达了最底层，分为找到该元素和没有找到该元素两种情况
        if (!exist) {
            return null;
        }

        // 找到该元素
        Node<K, V> removeNode = node.nexts[0];
        size--; // 维护size

        // 断开当前节点
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prevs[i].nexts[i] = removeNode.nexts[i];
        }

        // 更新有效层数（只需要考虑该节点层数比新的有效层数大的情况）
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removeNode.value;
    }

    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < P) {
            level++;
        }
        return level;
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable<K>) k1).compareTo(k2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
