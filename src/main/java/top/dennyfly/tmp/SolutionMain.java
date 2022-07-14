package top.dennyfly.tmp;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author dennyfly
 * @since 2022/7/11 16:29
 */
@Slf4j
public class SolutionMain {

    private static final int[] NUMS = {1, 3, 5, 7, 2, 4, 6, 8};

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[] result = solution.smallestK(NUMS, 4);
        log.info("result:{}", JSONUtil.toJsonStr(result));
    }
}
