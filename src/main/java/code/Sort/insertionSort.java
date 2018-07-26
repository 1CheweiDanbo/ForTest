package code.Sort;

public class insertionSort {
    public static void main(String[] args) {

        int[] a = {2,3,3,5,2,5,1,4};
        for(int val:insertSort(a)){
            System.out.println(val);
        }
    }

    public static int[] insertSort(int[] A) {
        int n = A.length;
        int temp;
        int j;
        for (int i = 1; i < n; i++) {
            temp = A[i];
            j = i - 1;
            while (j >= 0) {
                if (temp < A[j]) {
                    A[j + 1] = A[j];
                } else {
                    break;
                }
                j--;
            }
            A[j + 1] = temp;
        }
        return A;
    }

    public static int[] insertSort2(int[] A){
        int n = A.length;
        int i,j,gap;
        for(gap = n/2;gap>0;gap /= 2){
            for(i = gap;i<n;i++){
                for(j = i-gap;j>=0&&A[j]>A[j+gap];j-=gap){
                    Swap(A,j,j+gap);
                }
            }
        }
        return A;
    }

    public static void Swap(int[] A,int i,int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
