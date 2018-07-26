package code;

public class KMPSearch {
    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str,match));
    }

    private static int getIndexOf(String str, String match) {
        if(str == null || match == null || match.length()<1 || str.length()<match.length()){
            return -1;
        }
        int i=0;
        int j=0;
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        int[] next = getNext(str2);
        while (i<str1.length && j<str2.length){
            if(str1[i] == str2[j]){
                i++;
                j++;
            }else if (next[j] == -1){
                i++;
            }else{
                j = next[j];
            }
        }
        return j == match.length() ? i-j : -1;
    }

    private static int[] getNext(char[] chars){
        if(chars.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int j = 2;
        int k = 0;
        while (j < next.length){
            if(chars[j-1] == chars[k]) {
                next[j++] = ++k;
            } else if(k>0){
                k = next[k];
            } else {
                next[j++] = 0;
            }
        }
        return next;
    }
}
