package top.chitucao.datastructure.RedBlackTree;

/**
 * @author DennyFly
 * @since 2021/10/28 9:54
 * 红黑树
 * <p>
 * 基本性质
 * 1.节点不是红色就是黑色；
 * 2.根节点是黑色；
 * 3.所有叶子节点都是黑色的空节点（NIL，不存储数据）；
 * 4.如果一个节点是红色，那么他的孩子节点都是黑色（任何相邻的节点不能同时为红色，也就是说红色节点是被黑色节点分隔开的）；
 * 5.从任意一个节点到其可达叶子节点，经过的黑色节点个数是相同的；
 * <p>
 * 基本操作
 * 1.get        根据key获取value；
 * 2.contains   是否包含key；
 * 3.set        根据key设置value；
 * 4.add        根据key、value添加元素(左旋，右旋，翻转颜色)；
 * 5.remove     根据key删除，返回value；
 * <p>
 * 内部方法
 * 1.isRed          判断节点颜色；
 * 2.leftRotate     左旋 node.right = red,node.left != red；
 * 3.rightRotate    右旋 node.left = red, node.left.left = red；
 * 4.flipColors     翻转颜色 node.left = red, node.right = red；
 * 5.getNode        根据key获取节点；
 * 6.minmum         获取最小节点；
 * 7.removeMin      删除最小节点；
 * 8.remove         根据key删除，并返回删除节点；
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {

        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            // 初始化节点的时候默认为红色
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        // 初始化时为空节点
        root = null;
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        // 设置根节点为黑色
        root.color = BLACK;
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
            // 这是属于根据key更新value的情况
            node.value = value;
        }

        // 根绝条件节点后的逻辑链条，编写处理情况
        // 这3个判断条件不是if-else的关系，判断顺序也不能变
        // 由于是递归代码，假如返回的node是红色，则node返回给调用者后，还会执行上面的3个判断

        // 是否左旋
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 是否右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 是否翻转颜色
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }


    // 判断节点的颜色
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    // 返回node为节点的二叉树的最小节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除node为节点的二叉树的最小节点，返回node节点
    private Node removeMin(Node node) {
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
                Node minRight = minimum(node.right);
                minRight.right = removeMin(node.right);
                minRight.left = node.left;
                node.left = node.right = null;
                return minRight;
            }
        }
    }


    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


}
