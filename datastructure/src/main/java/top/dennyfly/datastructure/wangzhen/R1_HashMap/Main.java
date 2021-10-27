package top.dennyfly.datastructure.wangzhen.R1_HashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author DennyFly
 * @since 2021/10/27 15:23
 */
public class Main {

    @Test
    public void testHashMap() {
        // HashMap默认按照key的大小排序遍历
        HashMap<Integer, Integer> n = new HashMap<>();
        n.put(3, 11);
        n.put(1, 12);
        n.put(5, 23);
        n.put(2, 22);
        n.forEach((key, value) -> System.out.print(key));
        System.out.println();

        // LinkedHashMap默认按照插入顺序遍历
        HashMap<Integer, Integer> m = new LinkedHashMap<>();
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);

        m.put(3, 26);
        m.get(5);
        m.forEach((key, value) -> System.out.print(key));
        System.out.println();

        // LinkedHashMap 中的“Linked”实际上是指的是双向链表，并非指用链表法解决散列冲突。
        // 按照访问时间排序的 LinkedHashMap 本身就是一个支持 LRU 缓存淘汰策略的缓存系统
        // LinkedHashMap默认按照访问顺序从前向后遍历
        // 1235
        HashMap<Integer, Integer> t = new LinkedHashMap<>(10, 0.75f, true);
        t.put(3, 11);
        t.put(1, 12);
        t.put(5, 23);
        t.put(2, 22);

        t.put(3, 26);
        t.get(5);
        t.forEach((key, value) -> System.out.print(key));
        System.out.println();
    }
}
