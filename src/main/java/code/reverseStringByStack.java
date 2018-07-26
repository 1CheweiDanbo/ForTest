package code;

import java.util.Stack;

public class reverseStringByStack {

    public static String reverse(String str){
        char[] array = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char val:array){
            stack.push(val);
        }
        String newStr = "";
        for(int i=0;i<array.length;i++){
            newStr += stack.pop();
        }
        return newStr;
    }

    public static void main(String[] args) {
        String string = "hello  World";
        System.out.println(reverse(string));
    }
}
