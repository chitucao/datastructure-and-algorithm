package top.chitucao.datastructure.Bitmap;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * NumDistinctAndSort
 * 有随机生成10亿的0-10亿大小的正整数，请使用Java实现BitMap进行去重和排序
 * 参考地址  https://www.cnblogs.com/woyujiezhen/p/16435909.html
 */
@Slf4j
public class NumDistinctAndSort {

    /**
     * #思路
     * 表示一共10亿种可能，最大10亿的数需要 10 * 10000 * 10000 / 8的空间，也就是120M左右
     * 如果是一个hash结构，直接存原始的数，最大可能需要 4 * 10 * 10000 * 10000 大小的空间
     */
    public int[] solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        // 这里的每一位表示一个数
        byte[] bytes = new byte[10 * 10000 * 10000 / 8];

        // 加入所有的数
        for (int num : nums) {
            int index = num >> 3;
            int offset = num & 7;
            bytes[index] |= 1 << offset;
            log.debug("number: " + num + "index: " + index + "bitOffset: " + offset + "bitMap[i]" + bytes[index]);
        }

        // 遍历组装所有的数
        List<Integer> list = new ArrayList<>();
        char[] negative128 = new char[]{'1', '0', '0', '0', '0', '0', '0', '0'};

        for (int i = 0; i < bytes.length; i++) {
            char[] bits = bytes[i] == -128 ? negative128 : Integer.toBinaryString(bytes[i]).toCharArray();
            for (int j = bits.length - 1; j >= 0; j--) {    // 注意需要从低位开始
                if (bits[j] == '1') {   // 判断等于1的才需要加上去
                    list.add(i << 3 | (bits.length - j - 1));   // 注意这里还原原来的数的操作，其实等于相加
                }
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


}
