package code;

public class reverseStringByXOR {
    /**
     * use XOR operation to reverse string
     * a = a^b;
     * b = b^a;
     * a = b^a;
     * @param str
     * @return
     */
    public static String reverse(String str){
       char[] array = str.toCharArray();
       int begin = 0;
       int end = array.length-1;
       while (begin<end){
           array[begin] = (char) (array[begin]^array[end]);
           array[end] = (char) (array[end]^array[begin]);
           array[begin] = (char) (array[begin]^array[end]);
           begin++;
           end--;
       }
       return new String(array);
    }

    public static void main(String[] args) {
        String string = "hello world";
        System.out.println(reverse(string));
    }
}
