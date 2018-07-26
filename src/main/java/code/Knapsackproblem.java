package code;

import java.util.Scanner;

public class Knapsackproblem {

    private static Scanner sc;
    private static int count = 0;
    private static int[] worths;
    private static int[] weights;

    /**
     * 0-1背包问题
     *
     * 输入第一行：奖品数量
     * 输入第二行：每个奖品的价值，通过空格分割
     * 输入第三行：每个奖品的重量，通过空格分割
     * 输入第四行：能够带走的奖品总重量限度
     *
     * @param args
     */
    public static void main(String[] args)  {
        sc = new Scanner(System.in);
        count = sc.nextInt();
        sc.nextLine();
        String worth = sc.nextLine();
        worths = stringArrayintArray(worth.trim().split(" "));
        String weight = sc.nextLine();
        weights = stringArrayintArray(weight.trim().split(" "));
        int remainWeight = sc.nextInt();
//        System.out.print(getMostWorth(remainWeight, 0));
        System.out.println(knapsack(worths,weights,remainWeight));
    }


    public static int getMostWorth(int remainWeight, int index){
        if(index < count){
            if(remainWeight>=weights[index]){
                int yes = worths[index] + getMostWorth(remainWeight-weights[index], index+1);
                int no = getMostWorth(remainWeight, index+1);
                return yes>no?yes:no;
            }else{
                return getMostWorth(remainWeight, index+1);
            }
        }
        return 0;
    }

    /**
     * 非递归
     * @param val
     * @param wt
     * @param W
     * @return
     *
     * 参考：http://www.importnew.com/13072.html
     */
    public static int knapsack(int val[], int wt[], int W){
        int N = val.length;
        int[][] V = new int[N+1][W+1];
        for(int i=0;i<N+1;i++){
            V[i][0] = 0;
        }
        for(int i=0;i<W+1;i++){
            V[0][i] = 0;
        }
        for(int item = 1;item<N+1;item++){
            for(int weight = 1;weight<W+1;weight++){
                if(wt[item-1] <= weight){
                    V[item][weight] = Math.max(val[item-1]+V[item-1][weight-wt[item-1]],V[item-1][weight]);
                } else {
                    V[item][weight] = V[item-1][weight];
                }
            }
        }
        return V[N][W];
    }

    public static int[] stringArrayintArray(String[] strArray){
        int[] intArray = new int[strArray.length];
        for(int i=0;i<strArray.length;i++){
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }
}