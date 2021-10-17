package top.dennyfly.datastructure.bobo.L12_AVL_Tree;

import org.junit.Test;
import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C01_set.FileOperation;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/10/17 11:25
 */
public class Main {

    @Test
    public void testDiffCount(){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
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
}
