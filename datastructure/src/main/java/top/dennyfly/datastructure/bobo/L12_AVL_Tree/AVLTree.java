package top.dennyfly.datastructure.bobo.L12_AVL_Tree;

/**
 * @author DennyFly
 * @since 2021/10/17 10:18
 * AVL平衡二叉树，所有节点左子树和右子树高度不超过1
 * <p>
 * 需要维护树的高度和平衡因子
 * <p>
 * 基本操作
 * 1.添加
 * 2.根据key获取value
 * 3.是否包含key
 * 4.根据key更新value （如果找不到抛出异常）
 * 5.根据key删除，返回value（hibbard deletion）
 * 6.是否平衡
 * <p>
 * 内部方法
 * 1.计算高度、计算平衡因子
 * 2.根据key获取一个节点
 * 3.获取最小节点
 * 4.删除最小元素（考虑到最小节点对应的右节点不为空的情况，需要用右节点替代父节点）
 * 5.根据key删除输入节点中的一个节点，返回输入节点
 * 6.重写toString()方法
 */
public class AVLTree<K extends Comparable<K>, V> {

    class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        // 递归终止条件
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

        // 计算高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 判断平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }

        return node;
    }

    public V get(K key) {
        Node retNode = getNode(root, key);
        return retNode == null ? null : retNode.value;
    }

    private Node getNode(Node node, K key) {
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

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("node cann't find!");
        }
        node.value = value;
    }

    private Node minium(Node node) {
        if (node == null) {
            return null;
        }
        return minium(node.left);
    }

    // 这里返回值就是输入值
    private Node removeMin(Node node) {
        // 递归终止条件
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        // 递归终止条件
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 考虑到可能的简单场景，待删除节点的左子树或者右子树有一个为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 两个子节点都不为空，用右节点的最小节点替换待删除节点
            Node minRight = minium(node.right);
            minRight.right = removeMin(node.right);
            minRight.left = node.left;
            node.left = node.right = null;
            return minRight;
        }

    }

    // 计算平衡因子，可能为负
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 树的高度（对应的层次）
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }


}

