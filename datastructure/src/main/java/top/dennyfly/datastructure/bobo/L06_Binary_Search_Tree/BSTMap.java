package top.dennyfly.datastructure.bobo.L06_Binary_Search_Tree;

/**
 * @author DennyFly
 * @since 2021/10/18 11:10
 * 二叉树实现的map
 * <p>
 * 基本操作
 * 1.添加key、value；
 * 2.根据key获取value；
 * 3.是否包含key；
 * 4.根据key更新value；
 * 5.获取最小节点
 * 6.删除最小节点
 *
 * <p>
 * <p>
 * 内部方法
 * 1.根据key获取节点；
 */
public class BSTMap<K extends Comparable<K>, V> {

    class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        // 都没找到的情况
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key doesn't exist");
        }
        node.value = newValue;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public Node minimum() {
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public Node removeMin() {
        return removeMin(root);
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public Node remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node = remove(node.right, key);
            return node;
        } else {
            // 简单情况，删除当前节点的时候下面只有一个孩子
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {
                // 两个子孩子都不为空，应用hibbard deletion
                Node minRight = minimum(node.right);
                minRight.right = removeMin(node.right);
                minRight.left = node.left;
                node.left = node.right = null;
                return minRight;
            }
        }
    }


}
