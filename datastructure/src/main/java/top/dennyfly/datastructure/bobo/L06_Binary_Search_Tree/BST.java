package top.dennyfly.datastructure.bobo.L06_Binary_Search_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author DennyFly
 * @since 2021/9/15 13:39
 * <p>
 * 前序、中序、后序遍历参考  https://blog.csdn.net/xiaojinguniang/article/details/86502272
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        private E e;
        private Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e);
    }

    // 非递归前序遍历
    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    // 层序遍历
    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.e);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }

    }

    public E maximum() {
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E minimum() {
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

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

    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以node节点为根的二叉树中的e元素
    // 返回新的二叉树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
                // 找到比node节点大的最小节点，即右子树的最小节点，用这个节点替换待删除的节点
                Node minRight = minimum(node.right);
                minRight.right = removeMin(node.right);
                minRight.left = node.left;

                node.left = node.right = null;
                return minRight;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }
//
//    private void add(Node node, E e) {
//        if (e.equals(node.e)) {
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }

}
