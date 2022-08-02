package top.chitucao.datastructure.LinkedList;

import org.junit.Test;
import top.chitucao.datastructure.Set_and_Map.C01_set.LinkedListSet;
import top.chitucao.datastructure.Set_and_Map.C01_set.Set;
import top.chitucao.datastructure.Set_and_Map.C02_map.LinkedListMap;
import top.chitucao.datastructure.Set_and_Map.C02_map.Map;
import top.chitucao.tools.FileOperation;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author DennyFly
 * @since 2021/10/18 15:24
 */
public class Main {

    // 测试反转链表
    @Test
    public void testReverseNode() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            list.addFirst(i);
        }
        System.out.println(list);

        list.reverseNode();
        System.out.println(list);

        list.reverseNode2();
        System.out.println(list);
    }

    @Test
    public void testMidNode() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            list.addFirst(i);
        }
        System.out.println(list);
        System.out.println(list.midNode().e);

        list.removeFirst();
        System.out.println(list);
        System.out.println(list.midNode().e);
    }


    @Test
    public void testLinkedListMap() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            Map<String, Integer> map = new LinkedListMap<>();
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
    public void testLinkedListSet() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            Set<String> set1 = new LinkedListSet<>();
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

            Set<String> set2 = new LinkedListSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }

    @Test
    public void testListNode() {
        Integer[] arr = {1, 2, 3, 4, 5};
        ListNode<Integer> listNode = new ListNode<Integer>(arr);
        System.out.println(listNode.toString());
    }

    @Test
    public void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
        }
        System.out.println(list.toString());
        list.set(1, 10);
        System.out.println(list.get(1));
        System.out.println(list);

        System.out.println(list.contains(1));
        System.out.println(list.contains(10));

        list.removeElement(10);
        System.out.println(list);
    }

    @Test
    public void testRemoveWithDepth() {

        Integer[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode<Integer> head = new ListNode<Integer>(nums);
        System.out.println(head);

        ListNode<Integer> res = removeElements(head, 6, 0);
        System.out.println(res);
    }

    public ListNode<Integer> removeElements(ListNode<Integer> head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode<Integer> res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode<Integer> ret;
        if (Objects.equals(head.val, val)) {
            head.next = res;
            ret = head;
        } else {
            ret = res;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
