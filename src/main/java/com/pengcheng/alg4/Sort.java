package com.pengcheng.alg4;

import edu.princeton.cs.introcs.StdRandom;

public class Sort {
    public static boolean less(Comparable val1, Comparable val2){
        return val1.compareTo(val2) < 0;
    }

    public static void exchange(Comparable[] a,int index1,int index2){
        Comparable t = a[index1];
        a[index1] = a[index2];
        a[index2] = t;
    }
    public static Comparable[] genTestArray(){
        int N = 100;
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i].compareTo(a[i+1]) >= 0)
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
            for (int j = i; j >0 && less(a[j],a[j - 1]); j--) {
                exchange(a,j,j - 1);
            }
        }
    }
}
