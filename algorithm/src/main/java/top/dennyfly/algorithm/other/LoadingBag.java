package top.dennyfly.algorithm.other;

/**
 * @author DennyFly
 * @since 2021/11/15 9:47
 * 0-1背包问题
 * #回溯算法解决
 *
 * 参考资料
 * https://blog.csdn.net/seagal890/article/details/79336594
 *
 */
public class LoadingBag {

    private static int[] p;  // 物品价值数组
    private static int[] w;  // 物品重量数组
    private static int c;    // 最大可以拿的重量
    private static int count;// 物品的个数

    private static int cw;   // 当前的重量
    private static int cp;   // 当前的价值
    private int bestp;       // 目前最优装载的价值
    private static int r;    // 剩余物品的价值

    private static int[] cx;    // 存放当前解
    private static int[] bestx; // 存放最终解



}
