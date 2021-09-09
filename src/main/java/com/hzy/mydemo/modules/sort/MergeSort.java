package com.hzy.mydemo.modules.sort;

/**
 * @description: 归并排序
 * @author: hzy
 * @time: 2021/9/7 14:15
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = { 2, 15, 9, 1, 23, 13 };
        mergeSort(a);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) { //功能：使L~R这个范围里的排序好
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L - 1]; //辅助空间，谁小就会被拷贝进这个数组
        int index = 0;
        int p1 = L; //指着左区域第一个，之后往右走
        int p2 = M + 1; //指着右区域第一个，之后往右走
        while (p1 <= M && p2 <= R) { //如果两个指针都不越界
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]; //这里发生了两件事，1丢数2移动。两指针都往右走，能把部分数据排序并填到辅助数组的。而且，这个是经典的归并排序，所以当两指针的相同时左边的数据会被优先丢入进辅助数组，也因为这个操作保障了这个排序具有稳定性，假如你改成arr[p1]>[p1],那么就是把相同时，把右区域的值优先丢入辅助数组，会破坏稳定性
        }
        //因为上一步操作只是考虑了一部分数据，还有一部分数据没有处理。其实当一边的数据处理好了之后，另一边的数据本身是有序的，我们直接复制出来即可
        while (p1 <= M) { //看是不是左区域还有数据
            help[index++] = arr[p1++];
        }
        while (p2 <= R) { //看是不是右区域还有数据
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) { //把辅助空间里的数据归还给原数组
            arr[L + i] = help[i];
        }
    }
}
