package code;

public class binSearch {

    public static void main(String[] args) {
        int[] srcArray = { 3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81,
                95, 101 };
        System.out.println(binSearch(srcArray, 0, srcArray.length - 1, 81));
    }

    /**
     * 二分查找递归实现
     *
     * @param srcArray
     * @param start
     * @param end
     * @param key
     * @return
     */

    public static int binSearch(int srcArray[], int start, int end, int key) {
        int mid = (end - start) + start;  // 使用 (low + high) / 2 会有整数溢出的问题
        if(srcArray[mid] == key){
            return mid;
        }
        if(start >= end){
            return -1;
        } else if(key > srcArray[mid]){
            return binSearch(srcArray,mid+1,end,key);
        } else if(key < srcArray[mid]){
            return binSearch(srcArray,start,mid-1,key);
        }
        return -1;
    }

    /**
     * 二分查找的普通实现
     * @param srcArray
     * @param key
     * @return
     */
    public static int binSearch(int srcArray[], int key) {
        int mid = srcArray.length/2;
        if(key == srcArray[mid]){
            return mid;
        }
        int start = 0;
        int end = srcArray.length-1;
        while (start <= end){
            mid = (end - start) / 2;
            if(key < srcArray[mid]){
                end = mid - 1;
            } else if (key > srcArray[mid]){
              start = mid + 1;
            } else {
                return mid;
            }
        }
        // 当start>end时表示查找区间为空，查找失败
        return -1;
    }
}
