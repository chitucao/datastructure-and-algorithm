package top.chitucao.tmp.structure;

import org.junit.Test;
import top.chitucao.datastructure.Set_and_Map.C02_map.BSTMap;
import top.chitucao.datastructure.Set_and_Map.C02_map.Map;
import top.chitucao.utils.FileOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author chitucao
 * @since 2022/8/11 9:43
 */
public class BSTT<E extends Comparable<E>> {

    class Node {
        E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = this.right = null;
        }
    }

    Node root;
    int size;

    public BSTT() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public Node find(E e) {
        return find(root, e);
    }

    private Node find(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            return find(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return find(node.right, e);
        } else {
            return node;
        }
    }

    public boolean contains(E e) {
        return find(e) != null;
    }

    public void add(E e) {
        root = add(root, e);
    }

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

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void inOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.println(cur.e);
            cur = cur.right;
        }

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
        System.out.println(node.e);
    }

    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.e);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

    public E maximum() {
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E minimum() {
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public E removeMin() {
        E e = minimum();
        root = removeMin(root);
        return e;
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
        E e = maximum();
        root = removeMax(root);
        return e;
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

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node minRight = minimum(node.right);
                minRight.right = removeMin(node.right);
                minRight.left = node.left;
                node.left = node.right = null;
                size--;
                retNode = minRight;
            }
        }

        return retNode;
    }

    public Node parent(Node node) {
        return parent(root, node);
    }

    private Node parent(Node root, Node node) {
        // 1.父节点为空没找到；
        // 2.找到对应孩子的父节点；
        if (root == null || root.left == node || root.right == node) {
            return root;
        }

        // 可以理解为回溯
        Node left = parent(root.left, node);
        if (left != null) {
            return left;
        }
        Node right = parent(root.right, node);
        if (right != null) {
            return right;
        }

        return null;
    }

    public Node prev(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        }

        Node parent = parent(node);

        while (parent != null && node != parent.right) {
            node = parent;
            parent = parent(node);
        }

        return parent;
    }

    public Node next(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }

        Node parent = parent(node);

        while (parent != null && node != parent.left) {
            node = parent;
            parent = parent(node);
        }
        return parent;
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

    @Test
    public void testToString() {
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        BSTT<Integer> bst = new BSTT<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);
    }

    @Test
    public void testOrder() {
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        BSTT<Integer> bst = new BSTT<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        // 前序 532468
        bst.preOrder();
        System.out.println();

        // 非递归前序
        bst.preOrderNR();
        System.out.println();

        // 中序 234568
        bst.inOrder();
        System.out.println();

        // 后序 243865
        bst.postOrder();
        System.out.println();

        // 层序 536248
        bst.levelOrder();
        System.out.println();
    }

    @Test
    public void testRemoveMost() {
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        BSTT<Integer> bst = new BSTT<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);

        bst.removeMax();
        System.out.println(bst);

        bst.removeMin();
        System.out.println(bst);
    }

    @Test
    public void testRemoveAny() {
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        BSTT<Integer> bst = new BSTT<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);

        bst.remove(5);
        System.out.println(bst);

//        bst.remove(3);
//        System.out.println(bst);

//        bst.remove(6);
//        System.out.println(bst);
    }

    @Test
    public void testBSTMap() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            Map<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

    @Test
    public void testParentPreNext() {
        BSTT<Integer> bst = new BSTT<>();
        int[] nums = {6, 1, 7, 5, 9, 3, 8, 10, 2, 4};
        for (int num : nums) {
            bst.add(num);
        }

        // 父节点
        BSTT<Integer>.Node node = bst.find(5);
        BSTT<Integer>.Node parent = bst.parent(node);
        System.out.println("父节点 " + parent.e);

        // 前驱节点
        BSTT<Integer>.Node node1 = bst.find(6);
        BSTT<Integer>.Node prev = bst.prev(node1);
        System.out.println("前驱节点 " + prev.e);

        // 后继节点
        BSTT<Integer>.Node node2 = bst.find(2);
        BSTT<Integer>.Node next = bst.next(node2);
        System.out.println("后继节点 " + next.e);
    }

}
