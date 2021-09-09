package com.hzy.mydemo.modules.sort;

import java.util.Arrays;

/**
 * @description: 插入排序
 * @author: hzy
 * @time: 2021/9/6 17:30
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] ins = { 2, 3, 5, 1, 23, 6, 78, 34 };
        //        int[] ins2 = sort(ins);
        //        int[] ins2 = sort2(ins);
        //        int[] ins2 = selectSort(ins);
        //        for (int in : ins2) {
        //            System.out.println(in);
        //        }
        shellSort_04();
    }

    public static int[] sort(int[] ins) {
        for (int i = 1; i < ins.length; i++) {
            for (int j = i; j > 0; j--) {
                if (ins[j] < ins[j - 1]) {
                    int temp = ins[j - 1];
                    ins[j - 1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        return ins;
    }

    //优化
    public static int[] sort2(int[] ins) {
        //先从第二个位置遍历数组，用该位置的值循环比较该位置之前的数组的值，如果当前位置的值比前边的小就往前挪
        for (int i = 1; i < ins.length; i++) {
            int temp = ins[i]; //保存每次需要插入的那个数
            int j;
            for (j = i; j > 0 && ins[j - 1] > temp; j--) { //这个较上面有一定的优化
                ins[j] = ins[j - 1]; //吧大于需要插入的数往后移动。最后不大于temp的数就空出来j
            }
            ins[j] = temp; //将需要插入的数放入这个位置
        }
        return ins;
    }

    //希尔排序：插入排序的升级
    public static void shellSort_04() {
        int a[] = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        int count = 0; //比较次数
        for (int gap = a.length / 2; gap > 0; gap = gap / 2) {
            //将整个数组分为若干个子数组
            for (int i = gap; i < a.length; i++) {
                //遍历各组的元素
                for (int j = i - gap; j >= 0; j = j - gap) {
                    //交换元素
                    if (a[j] > a[j + gap]) {
                        int temp = a[j];
                        a[j] = a[j + gap];
                        a[j + gap] = temp;
                        count++;
                    }
                }
            }
        }
        System.out.println(count); //16
        System.out.println(Arrays.toString(a)); //[2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50]
    }

    //选择排序：循环数组，取数组中的某个值与数组从后往前循环遍历对比找到最小的交换
    public static int[] selectSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
        return numbers;
    }
}
