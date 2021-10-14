package top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/9/24 9:33
 */
public class SetTimeComparsion {

    public static void main(String[] args) {

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
