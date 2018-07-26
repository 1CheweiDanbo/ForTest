package code;

public class binSearchInsertToArray {
    public static void main(String[] args) {
        int[] array = {11,15,16,35,46,78,83};
        int index = insertToArray(array,50);
        System.out.println("index:"+index);
    }

    private static int insertToArray(int[] array, int key) {
        int max = array.length-1;
        int min = 0;
        while (min<=max){
            int mid = (max-min)+min; //overflow
            if(key>array[mid])
                min = mid + 1;
            else if(key<array[mid])
                max = mid - 1;
            else
                return mid;
        }
        return -1;//search fail
    }
}
