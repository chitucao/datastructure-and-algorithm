package elimination;

import org.junit.Test;

/**
 * @author DennyFly
 * @since 2021/10/19 13:33
 * 1.测试单链表实现的LRU K；
 * 2.测试哈希表和双向链表实现的LRU KV；
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

    @Test
    public void testDoublyLinkedListAndHashLRU() {
        LRU1<Integer, Integer> lru = new LRU1<>(5);
        for (int i = 0; i < 5; i++) {
            lru.put(i, i);
        }

        // LRU1 head: [0:0] [1:1] [2:2] [3:3] [4:4]  end
        System.out.println(lru);

        // LRU1 head: [0:0] [2:2] [3:3] [4:4] [1:1]  end
        lru.get(1);
        System.out.println(lru);

        // LRU1 head: [0:0] [2:2] [4:4] [1:1] [3:3]  end
        lru.put(3, 3);
        System.out.println(lru);

        // LRU1 head: [2:2] [4:4] [1:1] [3:3] [7:7]  end
        lru.put(7, 7);
        System.out.println(lru);
    }

}
