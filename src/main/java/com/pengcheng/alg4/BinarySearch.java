package com.pengcheng.alg4;

import java.util.Arrays;

public class BinarySearch implements Find {
/*思路
   1、排序
   2、三个下标
       low:
       high
       mid
   3、key 与 mid 比较
       key > mid : range(mid + 1 --> high)
       key < mid : range(low --> mid - 1)
       key = mid : return mid
       else      : return -1
*/


    public int find(int key, int[] a){
        Arrays.sort(a);
        int mid = a.length/2;
        if(key == a[mid])
            return mid;
        else if(key > a[mid])
            return findByRange(key,a,mid+1,a.length);
        else if(key < a[mid])
            return findByRange(key,a,0,mid -1);
        return -1;
    }

    public int find(int key, RandomAccessContainer container) {

        return 0;
    }

    private static int findByRange(int key, int[] a, int low, int high){
        int mid;
        while(low <= high){
            mid = (low + high)/2;
            if(key == a[mid])
                return mid;
            else if(key > a[mid])
                low = mid + 1;
            else if(key < a[mid])
                high = mid - 1;
        }
        return -1;
    }
}
