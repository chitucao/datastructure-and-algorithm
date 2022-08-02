package top.chitucao.algorithm.T04_Back_Tracking;

/**
 * @author dennyfly
 * @since 2021/11/26 15:58
 * 全排列
 * 参考资料  https://www.cnblogs.com/cugb-2013/p/3634531.html
 */
public class FullArray {

    public static void perm(char[] buf, int start, int end) {
        char temp = ' ';
        // 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
        if (start == end) {
            for (int i = 0; i <= end; i++) {
                System.out.print(buf[i] + " ");
            }
            System.out.println();
        } else {
            // 多个字母全排列
            for (int i = start; i <= end; i++) {
                // 当前元素可能是当前元素或者后面所有元素的排列组合
                temp = buf[start];
                buf[start] = buf[i];
                buf[i] = temp;

                // 后续元素递归全排列
                perm(buf, start + 1, end);

                // 将交换后的数组还原(这里类似于数独问题,数独里如果回头还需要给当前格还原)
                buf[i] = buf[start];
                buf[start] = temp;
            }
        }
    }

    // TODO 2021-11-26
    public static void perm(int[] num, int i) { // i为第几层
        if (i < num.length) { // 小于总层就旋转,旋转间隔从0开始
            for (int j = i; j < num.length; j++) {
                int tmp = num[j];
                // 旋转该区段最右边的数字到最左边
                for (int k = j; k > i; k--) {
                    num[k] = num[k - 1];
                }
                num[i] = tmp;
                perm(num, i + 1);

                // 还原
                for (int k = i; k < j; k++) {
                    num[k] = num[k + 1];
                }
                num[j] = tmp;
            }
        } else { // 最后一层的时候打印
            // 显示此次排序
            for (int j = 0; j < num.length; j++) {
                System.out.print(num[j] + " ");
            }
            System.out.println();
        }
    }

}
