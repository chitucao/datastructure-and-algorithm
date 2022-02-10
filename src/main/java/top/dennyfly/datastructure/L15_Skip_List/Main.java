package top.dennyfly.datastructure.L15_Skip_List;

import org.junit.Test;

import java.util.TreeMap;

public class Main {

    /**
     * 测试跳表
     */
    @Test
    public void testSkipList() {
        SkipList<Integer, Integer> list = new SkipList<>();
        test(list, 30, 10);
    }

    @Test
    public void testTime() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        SkipList<Integer, Integer> list = new SkipList<>();
        int count = 1000_0000;
        int delta = 10;

        Times.test("SkipList", () -> {
            test(list, count, delta);
        });

        Times.test("TreeMap", () -> {
            test(map, count, delta);
        });
    }

    private void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
//		System.out.println(list);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.get(i) == i + delta);
        }
        Asserts.test(list.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.remove(i) == i + delta);
        }
        Asserts.test(list.size() == 0);
    }

    private void test(TreeMap<Integer, Integer> map, int count, int delta) {
        for (int i = 0; i < count; i++) {
            map.put(i, i + delta);
        }
        for (int i = 0; i < count; i++) {
            Asserts.test(map.get(i) == i + delta);
        }
        Asserts.test(map.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(map.remove(i) == i + delta);
        }
        Asserts.test(map.size() == 0);
    }
}
