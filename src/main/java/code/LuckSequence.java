package code;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LuckSequence {
    /**对于一个序列Ai(0<i<=n),我们称相邻两个数字的绝对值之差为跳数，
     * 如果序列Ai的所有跳数包含1到n-1的所有值，则称Ai为幸运序列。
     * 例如序列：1 4 2 3是一个幸运序列，因为所有的跳数分别为3、2和1包含1到n-1(n=4)的所有值。
     * 你的任务是判断一个序列是否为幸运序列
     * 输入：每行包含一个整数n(0<n<=3000)表示序列长度,后面依次是n个整数Ai(Ai<|10^6|)
     * 输出：对于每一行的输入，产生一行输出说“Luck sequence”或“Unluck sequence”。
     */

    private static int[] sequences;
    static Set<Integer> set = new HashSet<Integer>();
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sequence = sc.nextLine();
        sequences = stringArrayintArray(sequence.trim().split(" "));
        count = sequences[0];
        if (count == sequences.length-1){
            luck(sequences);
        }else{
            System.out.println("输入有误！");
        }
    }

    private static void luck(int[] sequences) {
        for(int i=1;i<sequences.length;i++){
            int stepCount = Math.abs(sequences[i-1]-sequences[i]);
            set.add(stepCount);
        }
        if (set.size() == count-1){
            for(int val :set){
                if(val<count){
                    list.add(val);
                }
            }
            if (list.size() == count-1){
                System.out.println("Luck sequence");
            } else {
            System.out.println("Unluck sequence");
            }
    }else {
        System.out.println("Unluck sequence");}
    }

    public static int[] stringArrayintArray(String[] strArray){
        int[] intArray = new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }
}
