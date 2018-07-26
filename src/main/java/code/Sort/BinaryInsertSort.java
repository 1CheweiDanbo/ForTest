package code.Sort;

import java.util.Scanner;

public class BinaryInsertSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] array = str.split(" ");
        int[] arrayInt = new int[array.length];
        for(int i=0;i<array.length;i++){
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        int[] result = binaryInsertSort(arrayInt);
        for(int i = 0;i<result.length;i++){
            System.out.print(result[i] + "  ");
        }
    }

    private static int[] binaryInsertSort(int[] arrayInt) {
        for (int i=1;i<arrayInt.length;i++){
            int temp = arrayInt[i];
            int left = 0;
            int right = i-1;
            while (left <= right){
                int mid = (right-left)+left;
                if(arrayInt[mid]<temp)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            for(int j = i;j>left;j--){
                arrayInt[j] = arrayInt[j-1]; //将arrayInt[left]~arrayInt[i]均往后移一位
            }
            arrayInt[left] = temp;
        }
        return arrayInt;
    }
}
