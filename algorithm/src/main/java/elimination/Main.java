package elimination;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/19 13:33
 */
public class Main {

    @Test
    public void testLRULinkedList() {
        LRULinkedList<Integer> lruList = new LRULinkedList<>(5);
        for (int i = 0; i < 5; i++) {
            lruList.lru(i);
        }
        // LRUListNode: 4-> 3-> 2-> 1-> 0-> NULL
        System.out.println(lruList);

        // LRUListNode: 4-> 3-> 2-> 1-> 0-> NULL
        lruList.lru(1);
        System.out.println(lruList);

        // LRUListNode: 10-> 1-> 4-> 3-> 2-> NULL
        lruList.lru(10);
        System.out.println(lruList);
    }

}
