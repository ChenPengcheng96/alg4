package com.pengcheng.alg4;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;

public class Sort {
    public static double sortConsume(String sortName,int N,int number){
        Comparable[][] a = new Comparable[number][N];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.uniform();
            }
        }
        Stopwatch time = new Stopwatch();
        for (int i = 0; i < number; i++) {
            if ("mergeSort".equals(sortName))
                mergeSort(a[i]);
            else if("mergeBUSort".equals(sortName))
                mergeBUSort(a[i]);
            else if("quickSort".equals(sortName))
                quickSort(a[i]);
            else if("insertSort".equals(sortName))
                insertSort(a[i]);
            else if("selectSort".equals(sortName))
                selectSort(a[i]);
            else if("shellSort".equals(sortName))
                shellSort(a[i]);
        }
        return time.elapsedTime();
    }

    public static void sortCompare(String[] sortNames,int N, int number){
        for (String sortName:sortNames) {
            double time = sortConsume(sortName,N,number);
            System.out.println(sortName + ":" + time + "s");
        }
    }
    private static boolean less(Comparable val1, Comparable val2){
        return val1.compareTo(val2) < 0;
    }

    private static void exchange(Comparable[] a, int index1, int index2){
        Comparable t = a[index1];
        a[index1] = a[index2];
        a[index2] = t;
    }
    public static Comparable[] genTestArray(int N){
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i].compareTo(a[i+1]) > 0)
                return false;
        }
        return true;
    }
    public static void selectSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if(less(a[j],a[min]))
                    min = j;
            }
            exchange(a,min,i);
        }
    }

    public static void insertSort(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j],a[j - 1]); j--) {
                exchange(a,j,j - 1);
            }
        }
    }

    public static void shellSort(Comparable[] a){
        int N = a.length;
        //1、构造递增数列
        int h = 1;
        while(h < N/3) h = 3 * h +1;

        //2、外层控制开始位置，及趟数
        while(h >= 1){
            for (int i = h; i < N; i++) {
                //3、内层按间隔进行插入排序
                for (int j = i; j >= h  && less(a[j],a[j - h]) ; j -= h) {
                    exchange(a,j,j-h);
                }
            }
            h /= 3;
        }
    }

    //将左右两个有序的数组归并排序
    private static void merge(Comparable[] a,int low, int mid, int high){
        //1、复制数组
        int N = high - low + 1;
        Comparable[] aux = new Comparable[N];
        System.arraycopy(a, low, aux, 0, N);
        //2、归并到a
        int i = 0;
        int j = mid + 1 - low;
        for (int k = low; k < high + 1; k++) {
            if(i > mid - low )//防止越界，要先判断
                a[k] = aux[j++];
            else if(j > high - low )//防止越界，要先判断
                a[k] = aux[i++];
            else if(less(aux[i],aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }

    private static void mergeSort(Comparable[] a,int low, int high){
        int mid = (low + high) / 2;
        if(low >= high)
            return;
        mergeSort(a,low,mid);//将左半部分排序
        mergeSort(a,mid+1,high);//将右半部分排序
        merge(a,low,mid,high);//归并
    }

    //自顶向下归并排序
    public static void mergeSort(Comparable[] a){
        mergeSort(a,0,a.length - 1);
    }

    //自下向顶归并排序
    public static void mergeBUSort(Comparable[] a){
        //1、以步长为2^k （k = 1,2,3,4,......）归并排序
        int N = a.length;
        for (int size = 1; size < N; size*=2) {//控制步长
            for (int low = 0; low < N - size; low+=2*size) {//控制每个数组开始位置
                int tem = low + size + size -1;
                int high =  tem < N - 1 ? tem : N - 1;
               merge(a,low,low + size - 1,high);
            }
        }
    }

//        if((N & (N-1)) != 0){ //判断N是否为2的整数次幂
//            int bit =(int)(Math.log(N)/Math.log(2));//
//            merge(a,0, (int) Math.pow(2,bit) - 1,N-1);
//        }
        //           log[a]x
        //log[b]x = ---------   换底公式
        //           log[a]b

    public static void quickSort(Comparable[] a){
        //1、找到切分元素，相当于隔板，将左右两边分开比较
        //2、从1开始递增，找一个比切分元素大的元素
        //3、从N-1开始递减，找一个比切分元素小的元素
        //4、交换2、3中的元素
        int N = a.length;
        quickSort(a,0,N-1);
    }

    private static void quickSort(Comparable[] a,int low,int high){
        if(low >= high) return;
        int part = partition(a,low,high);//将隔板左右有序化
        quickSort(a,low,part - 1);//将隔板左边排序
        quickSort(a,part + 1,high);//将隔板右边排序
    }

    private static int partition(Comparable[] a, int low ,int high){
        Comparable key = a[low];
        int i = low + 1;
        int j = high;
        while(true){
            while(i < high && less(a[i],key))
                i++;
            while(j >= low && less(key,a[j]))
                j--;
            if(i >= j) break;
            exchange(a,i++,j--);
        }
        exchange(a,low,j);
        return j;
    }

    public static void bubbleSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if(less(a[j+1],a[j]))
                    exchange(a,j,j+1);
            }
        }
    }
}
