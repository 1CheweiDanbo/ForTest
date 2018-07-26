package code;

import java.util.Scanner;

public class Fell {
    public static long calcFell(int n){
        long arr[] = new long[n+1];
        arr[1] = 1;
        for(int i=2;i<n+1;i++){
            arr[i] = 2*arr[i-1]+arr[i-2];
        }
        return arr[n];
    }

    public static long calcFellRecursive(int n){
        if(n == 1 || n == 2){
            return n;
        }
        return 2*calcFellRecursive(n-1)+calcFellRecursive(n-2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long value;
        int a[] = new int[n];
        for(int i = 0;i<n;i++){
            int k = sc.nextInt();
            a[i] = k;
        }
        for(int i = 0;i<n;i++){
            value = calcFell(a[i])%32767;
            System.out.println(value);
        }
    }
}
