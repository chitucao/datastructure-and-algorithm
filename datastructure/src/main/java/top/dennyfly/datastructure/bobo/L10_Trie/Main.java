package top.dennyfly.datastructure.bobo.L10_Trie;

import org.junit.Test;
import top.dennyfly.datastructure.bobo.L06_Binary_Search_Tree.BSTSet;
import top.dennyfly.datastructure.bobo.Other.FileOperation;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/10/18 14:21
 * 测试用例
 * <p>
 * 1.测试TreeMap、HashMap、单词数组实现字典树统计单词的性能差异；
 * 2.测试二叉树、字典树统计单词的性能差异；
 */
public class Main {

    @Test
    public void testDiffTries() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words) &&
                FileOperation.readFile("a-tale-of-two-cities.txt", words)) {

            // Test BST
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            // Test TreeMap Trie
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("TreeMap Trie: " + time + " s");

            // ---

            // Test HashMap Trie
            startTime = System.nanoTime();

            Trie2 trie2 = new Trie2();
            for (String word : words) {
                trie2.add(word);
            }
            for (String word : words) {
                trie2.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("HashMap Trie: " + time + " s");

            // ---
            // Test Array(Map) Trie
            startTime = System.nanoTime();

            Trie3 trie3 = new Trie3();
            for (String word : words) {
                trie3.add(word);
            }
            for (String word : words) {
                trie3.contains(word);
            }

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Array(Map) Trie: " + time + " s");
        }
    }

    @Test
    public void testBSTAndTrie() {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            // 基于二叉树
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // --- 基于前缀树

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
