package top.chitucao.datastructure.Benchmark;

import org.junit.Test;
import top.chitucao.datastructure.AVLTree.AVLTree;
import top.chitucao.datastructure.RedBlackTree.RBTree;
import top.chitucao.datastructure.Set_and_Map.C02_map.BSTMap;
import top.chitucao.utils.FileOperation;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author chitucao
 * @since 2022/8/16 17:42
 * 1.对于完全随机的数据，普通的二分搜素树很好用；
 * 2.对于查询很多的树，AVL树很好用；
 * 3.红黑树牺牲了平衡性（影响了查询性能），综合性能比较好（添加删除比AVL快）；
 */
public class TreeBenchmark {

    /**
     * 综合测试
     */
    @Test
    public void testCRUD() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                } else {
                    bst.add(word, 1);
                }
            }

            for (String word : words) {
                bst.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word)) {
                    avl.set(word, avl.get(word) + 1);
                } else {
                    avl.add(word, 1);
                }
            }

            for (String word : words) {
                avl.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();

            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word)) {
                    rbt.set(word, rbt.get(word) + 1);
                } else {
                    rbt.add(word, 1);
                }
            }

            for (String word : words) {
                rbt.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");
        }

        System.out.println();
    }

    /**
     * 这里红黑树最慢，可能是实现方式的原因
     */
    @Test
    public void testAdd() {
        int n = 2000000;

        Random random = new Random(n);
        ArrayList<Integer> testData = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // Test BST
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> bst = new BSTMap<>();
        for (Integer x : testData) {
            bst.add(x, null);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");


        // Test AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x : testData) {
            avl.add(x, null);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");


        // Test RBTree
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x : testData) {
            rbt.add(x, null);
        }

        endTime = System.nanoTime();

        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }
}
