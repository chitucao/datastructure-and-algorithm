/**
 * @author DennyFly
 * @since 2021/10/17 13:21
 * 排序算法
 * <li> 冒泡排序 {@link top.chitucao.algorithm.B01_Sort.BubbleSort}
 * <li> 插入排序 {@link top.chitucao.algorithm.B01_Sort.InsertionSort}
 * <li> 选择排序 {@link top.chitucao.algorithm.B01_Sort.SelectionSort}
 * <li> 归并排序 {@link top.chitucao.algorithm.B01_Sort.MergeSort}
 * <li> 快速排序 {@link top.chitucao.algorithm.B01_Sort.QuickSort}
 * <li> 堆排序 {@link top.chitucao.algorithm.B01_Sort.HeapSort}
 * <p>
 * #冒泡排序和插入排序的比较
 * -a.交换两个元素位置的时候，冒泡排序每次都需要一个中间变量三次赋值操作，而插入排序不需要临时变量，基于索引交换就好了；
 * -b.选择排序和插入排序类似，最终只需要交换两个元素的位置，但是每次都需要遍历n次找到最小值，插入排序可以提前终止；
 */
package top.chitucao.algorithm.B01_Sort;