package code.Sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {3,5,7,2,6,3,9,10};
        quicksort(arr,0,arr.length-1);
        for (int val:arr){
            System.out.println(val);
        }
    }


    static void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right); //找到固定第一个元素的位置
            quicksort(n, left, dp - 1); //分开递归
            quicksort(n, dp + 1, right);

        }
    }

    static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
}
