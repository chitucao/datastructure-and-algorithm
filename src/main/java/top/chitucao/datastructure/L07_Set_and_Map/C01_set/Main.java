package top.chitucao.datastructure.L07_Set_and_Map.C01_set;

import org.junit.Test;
import top.chitucao.tools.FileOperation;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/10/18 15:15
 * 测试用例
 */
public class Main {

    @Test
    public void testBSTSet() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            Set<String> set1 = new BSTSet<>();
            for (String word : words1) {
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            Set<String> set2 = new BSTSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }

    @Test
    public void testLinkedListSetAndBSTSet() {
        String filename = "pride-and-prejudice.txt";

        Set<String> set1 = new BSTSet<>();
        double time1 = testSet(set1, filename);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();

        Set<String> set2 = new LinkedListSet<>();
        double time2 = testSet(set2, filename);
        System.out.println("Linked List Set: " + time2 + " s");
    }

    private static double testSet(Set<String> set, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 10000.0;
    }
}
