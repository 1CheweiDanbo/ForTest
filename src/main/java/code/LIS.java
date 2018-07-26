package code;

/**
 * 最简单的思路是动态规划，数组array表示输入的数组，用一个数组dp记录子串每一位的最长递增子串长度，采用公式
 * dp[i]=max(dp[i],dp[j]+1)   其中j<i,并且array[i]>array[j]
 * 具体分析如下：
 * 设f(i)表示L中以array[i]为末元素的最长递增子序列的长度。则有如下的递推方程：
 * 这个递推方程的意思是， 在求以array[i]为末元素的最长递增子序列时，
 * 找到所有序号在L前面且小于array[i]的元素array[j]，即j < i 且array[j]< array[i]。
 * 如果这样的元素存在，那么对所有array[j],都有一个以array[i]为末元素的最长递增子
 * 序列的长度f(j)，把其中最大的f(j)选出来，那么f(i)就等于最大的f(j)加上1，
 * 即以array[i]为末元素的最长递增子序列，等于以使f(j)最大的那个array[j]为末元素
 * 的递增子序列最末再加上array[i]；如果这样的元素不存在，
 * 那么array[i]自身构成一个长度为1的以array[i]为末元素的递增子序列。
 */
public class LIS {
    public static int lis(int[] array){
        int length = array.length;
        if(length == 0){
            return 0;
        }
        int maxCount = 0;
        int[] dp = new int[length];
        for(int i=0;i<length;i++){
            dp[i] =1;
            for(int j=0;j<i;j++){
                if(array[j]<array[i]){
                    dp[i] = dp[i] > dp[j] +1 ? dp[i] : dp[j]+1;
                }
                if(maxCount<dp[i]){
                    maxCount = dp[i];
                }
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] array = {10,9,2,5,3,7,101,18};
        System.out.println(lis(array));
    }

}
