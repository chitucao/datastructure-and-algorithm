package top.chitucao.algorithm.B02_Search;

/**
 * @author DennyFly
 * @since 2021/10/26 17:08
 * 二分查找法
 * <p>
 * 应用场景
 * 1.值等于给定值更倾向于哈希表和二叉树，适合于实现值近似问题；
 * <p>
 * 二分查找的局限性
 * 1.依赖顺序表，也就是数组；
 * 2.适合有序的静态数据，对于频繁插入删除操作的数据不合适；
 * 3.数据量太小不适合二分查找，但是比较操作耗时的适合二分查找，能够减少比较次数；
 * 4.数据量太大不适合二分查找，很难给排序的数组找到一片连续的内存空间；
 * <p>
 * 二分查找的变种
 * 1.查找第一个值等于给定值的元素；
 * 2.查找最后一个值等于给定值的元素；
 * 3.查找第一个大于等于该值的元素；
 * 4.查找最后一个小于等于该值的元素；
 * <p>
 * Plus
 * 1.寻找第一个小于该值的元素   leetcode300
 */
public class BinarySearch {

    /**
     * 非递归实现
     * 注意点1，循环条件推出条件  <=；
     * 注意点2，mid的取值  求中点的时候数据范围和位运算优化；
     * 注意点3，low和high的更新  low+1和high-1
     */
    public static int binarySearch1(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (k == arr[mid]) {
                return mid;
            } else if (k > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     * 注意点同上
     */
    public static int binarySearch2(int[] arr, int k) {
        return binarySearch2(arr, 0, arr.length - 1, k);
    }

    private static int binarySearch2(int[] arr, int low, int high, int k) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (arr[mid] == k) {
            return mid;
        } else if (arr[mid] < k) {
            return binarySearch2(arr, mid + 1, high, k);
        } else {
            return binarySearch2(arr, low, high - 1, k);
        }
    }

    /**
     * 变形的二分查找1.1
     * 查找第一个等于给定值的元素
     * #比较容易理解的写法
     */
    public static int binarySearch3(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (k > arr[mid]) {
                low = mid + 1;
            } else if (k < arr[mid]) {
                high = mid - 1;
            } else {
                // mid为第一个元素或者mid-1不等于该元素
                if (mid == 0 || arr[mid - 1] != k) {
                    return mid;
                } else {
                    // 否则high-1
                    high = high - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变形的二分查找1.2
     * 查找第一个等于给定值的元素
     * #简洁写法，注意循环判断条件和推出条件
     */
    public static int binarySearch4(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            // 这里是最终high会一直回退，直到到达low，也就是等于该值的第一个元素
            if (k <= arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < arr.length && arr[low] == k) {
            return low;
        } else {
            return -1;
        }
    }

    /**
     * 变形的二分查找2.1
     * 查找最后一个等于给定值的元素
     * #容易理解的写法
     */
    public static int binarySearch5(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (k < arr[mid]) {
                high = mid - 1;
            } else if (k > arr[mid]) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != k) {
                    return mid;
                } else {
                    low = low + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变形的二分查找2.2
     * 查找最后一个等于给定值的元素
     * #简洁写法，注意循环判断条件和推出条件
     */
    public static int binarySearch6(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            // 这里是最终low会一直前进，直到到达high，也就是等于该值的最后一个元素
            if (k >= arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (high > 0 && arr[high] == k) {
            return high;
        } else {
            return -1;
        }
    }

    /**
     * 变形的二分查找3
     * 查找第一个大于等于该值的元素
     */
    public static int binarySearch7(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= k) {
                if (mid == 0 || arr[mid - 1] < k) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变形的二分查找4
     * 查找最后一个小于等于该值的元素
     */
    public static int binarySearch8(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (arr[mid] <= k) {
                if (mid == arr.length - 1 || arr[mid + 1] > k) {
                    return mid;
                } else {
                    mid = mid + 1;
                }
            } else {
                high = high - 1;
            }
        }
        return -1;
    }
}
