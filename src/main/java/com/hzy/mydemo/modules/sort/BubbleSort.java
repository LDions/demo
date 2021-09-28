package com.hzy.mydemo.modules.sort;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @author: hzy
 * @time: 2021/9/7 9:51
 */
public class BubbleSort {

    public static void main(String[] args) {
        //        bubbleSort1_1();
        bubbleSort1_2();
    }

    public static void bubbleSort1_1() {
        int[] arr = { 3, 44, 38, 5, 47, 15, 36 };
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) { //外层控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) { //内层循环控制每一趟排多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                count++;
            }
        }
        System.out.println("一共比较了：" + count + "次");
        for (int num : arr) {
            System.out.println(num);
        }
    }

    //优化
    public static void bubbleSort1_2() {
        int a[] = { 3, 44, 38, 5, 47, 65, 76 };
        int count = 0;
        for (int i = 0; i < a.length - 1; i++) { //将最大值放到数组的最后面
            boolean flag = true; // 默认有序，如果发生交换说明无序。
            for (int j = 0; j < a.length - 1 - i; j++) { //每次结束后去掉已经找出的最大值
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                }
                count++;
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(a)); // [2, 3, 4, 5, 15, 19, 26, 27, 36, 38, 44, 46, 47, 48, 50]
        System.out.println("一共比较了：" + count + "次"); //一共比较了：95次
    }
}
