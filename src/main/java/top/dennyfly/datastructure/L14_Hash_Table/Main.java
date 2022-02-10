package top.dennyfly.datastructure.L14_Hash_Table;

import org.junit.Test;
import top.dennyfly.datastructure.L07_Set_and_Map.C02_map.BSTMap;
import top.dennyfly.datastructure.L12_AVL_Tree.AVLTree;
import top.dennyfly.tools.FileOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author DennyFly
 * @since 2021/10/17 16:49
 */
public class Main {

    @Test
    public void testJavaLibHash() {
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println();

        Student student = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student2 = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student2.hashCode());
    }

    @Test
    public void testBSTMap() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
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
    public void testCountWords() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.top.dennyfly.algorithm.sort(words);
            long startTime = System.nanoTime();

            // Test BST
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
//
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
//            startTime = System.nanoTime();
//
//            RBTree<String, Integer> rbt = new RBTree<>();
//            for (String word : words) {
//                if (rbt.contains(word)) {
//                    rbt.set(word, rbt.get(word) + 1);
//                } else {
//                    rbt.add(word, 1);
//                }
//            }
//
//            for(String word: words) {
//                rbt.contains(word);
//            }
//
//            endTime = System.nanoTime();
//
//            time = (endTime - startTime) / 1000000000.0;
//            System.out.println("RBTree: " + time + " s");


            // Test HashTable
            startTime = System.nanoTime();

            HashTable1<String, Integer> ht = new HashTable1<>();
//            HashTable<String, Integer> ht = new HashTable<>(131071);
            for (String word : words) {
                if (ht.contains(word)) {
                    ht.set(word, ht.get(word) + 1);
                } else {
                    ht.add(word, 1);
                }
            }

            for (String word : words) {
                ht.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }

        System.out.println();
    }

    @Test
    public void testHashTable2(){
        HashTable2<Integer, Integer> table = new HashTable2<>();
        table.put(1,1);
        table.put(2,2);
        table.put(3,3);
        table.put(3,4);

        Integer get = table.get(4);
        System.out.println(get);

        table.remove(1);

        System.out.println(111);
    }
}
