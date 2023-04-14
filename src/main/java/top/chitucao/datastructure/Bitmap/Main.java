package top.chitucao.datastructure.Bitmap;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Main
 */
@Slf4j
public class Main {

    @Test
    public void testBasicBitmap() {
        Bitmap bitmap = new Bitmap(12);
        bitmap.set(0, true);
        bitmap.set(1, false);
        bitmap.set(2, true);
        System.out.println(bitmap);
    }

    @Test
    public void testToCharArray() {
        byte b = 0b1111100;
        String s = Integer.toBinaryString(b);
        char[] chars = s.toCharArray();
        System.out.println(s);
    }

    @Test
    public void testOr(){

    }

    @Test
    public void testDistinctAndSort() {
        List<Integer> numList = new ArrayList<>();

//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            numList.add(random.nextInt(10000 * 10000 * 10));
//        }
        numList.add(1);
        numList.add(2);
        numList.add(3);

        int[] nums = new int[numList.size()];
        for (int i = 0; i < numList.size(); i++) {
            nums[i] = numList.get(i);
        }
        NumDistinctAndSort numDistinctAndSort = new NumDistinctAndSort();
        int[] result = numDistinctAndSort.solution(nums);
        log.info("result {}", JSONUtil.toJsonStr(result));

        Collections.sort(numList);
        log.info("origin list {}", JSONUtil.toJsonStr(numList));
    }
}
