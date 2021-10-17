package top.dennyfly.datastructure.bobo.L12_AVL_Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DennyFly
 * @since 2021/10/17 10:18
 * AVL平衡二叉树，所有节点左子树和右子树高度不超过1
 * <p>
 * 需要维护树的高度和平衡因子
 * <p>
 * <p>
 * 基本操作
 * 1.添加节点（维护平衡因子，左旋转和右旋转）
 * 2.根据key获取value
 * 3.是否包含key
 * 4.根据key更新value （如果找不到抛出异常）
 * 5.根据key删除，返回value（hibbard deletion，+维护平衡）
 * 6.是否平衡
 * 7.是否是二分搜索树（不严谨）
 * <p>
 * <p>
 * 内部方法
 * 1.计算高度、计算平衡因子
 * 2.根据key获取一个节点
 * 3.获取最小节点
 * 4.删除最小节点（考虑到最小节点对应的右节点不为空的情况，需要用右节点替代父节点）
 * 5.根据key删除输入节点中的一个节点，返回输入节点
 * 6.重写toString()方法
 * 7.中序遍历
 * 8.左旋转和右旋转
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

    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        // 递归终止条件
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
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

        // 更新高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 判断平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced: " + balanceFactor);
//        }

        // 维护平衡，四种情况
        // LL 右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR 左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR 先对左孩子左旋转换成LL，然后右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL 先对右孩子右旋转换成RR，然后左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    // LL右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        // 维护x和y的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    // RR左旋
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        // 维护x和y的高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
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

    private Node minimum(Node node) {
        // 递归终止条件
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
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

        // 声明返回节点
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
//            return node;
            retNode = node;

        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
//            return node;
            retNode = node;
        } else {
            // 考虑到可能的简单场景，待删除节点的左子树或者右子树有一个为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
//                return rightNode;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
//                return leftNode;
                retNode = leftNode;
            } else {
                // 两个子节点都不为空，用右节点的最小节点替换待删除节点
                Node minRight = minimum(node.right);
//            minRight.right = removeMin(node.right);
                minRight.right = remove(node.right, minRight.key);
                minRight.left = node.left;
                node.left = node.right = null;
//            return minRight;
                retNode = minRight;
            }
        }


        if (retNode == null) {
            return null;
        }

        // 维护高度、平衡因子
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);

        // LL 执行右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR 执行左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR，先对左孩子执行左旋转换成LL，然后对该节点执行右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL,先对右孩子执行右旋转换成RR，然后对该节点执行左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return retNode;
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

