package top.dennyfly.tmp.A02_LinkedList;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2021/11/23 9:44
 */
public class Main {
    private static LinkedList<Integer> linkedList;

    static {
        linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
        }
    }


    @Test
    public void testLinkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
        }
        System.out.println(linkedList);
        for (int i = 0; i < 3; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);

        linkedList.removeByIndex(2);
        System.out.println(linkedList);

        linkedList.removeByValue(4);
        System.out.println(linkedList);
        ;

        linkedList.addByIndex(2, 7);
        System.out.println(linkedList);

        linkedList.set(0, 12);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }

    @Test
    public void testReverseNode() {
        linkedList.reverseNode1();
        System.out.println(linkedList);
        linkedList.reverseNode2();
        System.out.println(linkedList);
    }

    @Test
    public void testMidNode() {
        System.out.println(linkedList);
        LinkedList<Integer>.Node node = linkedList.midNode();
        System.out.println("中间节点：" + node.value);
    }
}
