package top.chitucao.algorithm.RateLimit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author dennyfly
 * @since 2022/6/28 14:04
 */
@Slf4j
public class Main {

    @Test
    public void testCounter() {
        Counter counter = new Counter();
        while (true) {
            boolean grant = counter.grant();
            log.info("result:{}", grant);
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
