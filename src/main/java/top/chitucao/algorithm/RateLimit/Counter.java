package top.chitucao.algorithm.RateLimit;

/**
 * @author dennyfly
 * @since 2022/6/28 14:00
 * 计数器限流
 */
public class Counter {

    private long timeStamp;
    public int reqCount = 0;
    public final int limit = 100;
    public final long interval = 1000;

    public Counter() {
        timeStamp = getNowTime();
    }

    public boolean grant() {
        long now = getNowTime();
        if (now < timeStamp + interval) {
            reqCount++;
            return reqCount <= limit;
        } else {
            // 进入新的计数周期
            timeStamp = now;
            reqCount = 1;
            return true;
        }
    }

    public Long getNowTime() {
        return System.currentTimeMillis();
    }
}
