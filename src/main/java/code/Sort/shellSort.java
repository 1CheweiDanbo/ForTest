package code.Sort;

public class shellSort {
    public static void main(String[] args) {

    }
//    以第二次排序为例，原来是每次从1A到1E，从2A到2E，可以改成从1B开始，先和1A比较，然后取2B与2A比较，再取1C与前面自己组内的数据比较…….。这种每次从数组第gap个元素开始，每个元素与自己组内的数据进行直接插入排序显然也是正确的。
    public static int[] shell(int[] A){
        int n = A.length;
        int j,gap;
        for(gap = n/2;gap>0;gap/=2){
            for(j = gap;j<n;j++){
                if(A[j]<A[j-gap]){
                    int temp = A[j];
                    int k = j-gap;
                    while (k>=0&&A[k]>temp){
                        A[k+gap] = A[k];
                        k -= gap;
                    }
                    A[k+gap] = temp;
                }
            }
        }
        return A;
    }
}
