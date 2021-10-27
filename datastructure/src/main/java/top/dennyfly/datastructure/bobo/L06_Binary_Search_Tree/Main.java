package top.dennyfly.datastructure.bobo.L06_Binary_Search_Tree;

import org.junit.Test;
import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C02_map.BSTMap;
import top.dennyfly.datastructure.bobo.L07_Set_and_Map.C02_map.Map;
import top.dennyfly.datastructure.bobo.Other.FileOperation;

import java.util.ArrayList;

/**
 * @author DennyFly
 * @since 2021/10/18 15:21
 */
public class Main {

    @Test
    public void testToString() {
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        BST<Integer> bst = new BST<>();
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
        BST<Integer> bst = new BST<>();
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
        BST<Integer> bst = new BST<>();
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
        BST<Integer> bst = new BST<>();
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
}
