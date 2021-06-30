package l2_linkedlist;

public class Main {

    public static void main(String[] args) {
//        generalTest();

        dummyTest();

    }

    public static void generalTest(){
        GeneralLinkedList<Integer> generalLinkedList = new GeneralLinkedList<>();
        for (int i = 0; i < 5; i++) {
            generalLinkedList.addFirst(i);
            System.out.println(generalLinkedList);
        }

        generalLinkedList.add(2, 666);
        System.out.println(generalLinkedList);
    }

    public static void dummyTest(){
        DummyLinkedList<Integer> dummyLinkedList = new DummyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            dummyLinkedList.addFirst(i);
            System.out.println(dummyLinkedList);
        }

        dummyLinkedList.add(2, 666);
        System.out.println(dummyLinkedList);

        dummyLinkedList.remove(2);
        System.out.println(dummyLinkedList);

        dummyLinkedList.removeFirst();
        System.out.println(dummyLinkedList);

        dummyLinkedList.removeLast();
        System.out.println(dummyLinkedList);
    }

}
