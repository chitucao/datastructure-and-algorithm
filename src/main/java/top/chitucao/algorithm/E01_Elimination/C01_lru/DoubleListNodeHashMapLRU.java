package top.chitucao.algorithm.E01_Elimination.C01_lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/10/19 13:55
 * 基于双向链表和哈希表实现LRU
 * <p>
 * 这里用一个哈希表存储key和双向链表节点的关系，用于快速判断节点是否存在和获取该节点
 * 双向链表的意义在于删除和移动元素都比较方便
 * 最新的节点放到链表尾部
 * 这里的链表节点同时有key和value属性
 * <p>
 * 基本操作
 * 1.get    根据key获取value（如果key存在，会移动当前节点到链表尾部）；
 * 2.put    存入key、value键值对；
 * <p>
 * 内部方法
 * moveNodeToLast       移动节点到尾部（先删除后插入）；
 * removeNode           删除一个节点；
 * addNodeToLast        尾部添加节点；
 * toString             重写打印方法；
 */
public class DoubleListNodeHashMapLRU<K, V> {

    class Node {
        private K key;
        private V value;
        private Node prev, next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + ":" + value + "]";
        }
    }

    private int capacity;
    private Node head, tail;
    private Map<K, Node> map;

    public DoubleListNodeHashMapLRU(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        // 初始化双向链表
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 根据key获取value
     */
    public V get(K key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveNodeToLast(node);
            return node.value;
        } else {
            return null;
        }
    }

    public void put(K key, V value) {
        // 如果包含该节点，执行更新逻辑，同时移动到尾部
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveNodeToLast(node);
            return;
        }

        // 容量满的时候，删除头部节点
        if (map.size() == capacity) {
            map.remove(head.next.key);
            removeNode(head.next);
        }

        // 新节点直接添加到尾部
        Node node = new Node(key, value);
        map.put(key, node);
        addNodeToLast(node);
    }

    /**
     * 移动节点到尾部
     * 这里默认该节点存在
     */
    public void moveNodeToLast(Node node) {
        removeNode(node);
        addNodeToLast(node);
    }

    /**
     * 删除一个节点
     */
    private void removeNode(Node node) {
        // 维护和前后节点的关系
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 插入链表尾部
     * 这里并不会替代尾节点，是放到尾节点的前面
     */
    private void addNodeToLast(Node node) {
        // 维护和前一个节点的关系
        node.prev = tail.prev;
        node.prev.next = node;

        // 维护和尾节点的关系
        node.next = tail;
        tail.prev = node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LRU1 head: ");
        Node cur = head;
        while (!Objects.equals(cur.next, tail)) {
            sb.append(cur.next + " ");
            cur = cur.next;
        }
        sb.append(" end");
        return sb.toString();
    }
}
