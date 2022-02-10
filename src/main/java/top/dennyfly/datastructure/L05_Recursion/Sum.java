package top.dennyfly.datastructure.L05_Recursion;

/**
 * @author DennyFly
 * @since 2021/9/15 10:29
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    // 计算arr[l...n)这个区间内所有数字的和
    public static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};

        System.out.println(sum(arr));
    }
}
