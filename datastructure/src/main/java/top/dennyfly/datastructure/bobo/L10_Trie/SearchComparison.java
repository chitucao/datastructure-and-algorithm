package top.dennyfly.datastructure.bobo.L10_Trie;

import org.junit.Test;
import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set.BSTSet;
import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set.FileOperation;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/10/14 17:10
 */
public class SearchComparison {

    @Test
    public void testAddAndSearch() {

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
