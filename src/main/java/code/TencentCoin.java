package code;

import java.util.*;

public class TencentCoin {
    private static int n;
    private static int count = 0;
    private static int[] p = null;//p[i]记录2^i元硬币使用的个数，取值为0~2

    /*------------------回溯法-----------------*/

    //初始化数组的大小
    private static void init(){
        double lo = Math.log(n)/Math.log(2);
        int length = (int)lo+1;
        p = new int[length];
    }

    //取值并回溯
    private static final void solve(int i){
        if(i>= p.length) return;
        for(int t = 0;t<=2;t++){
            p[i] = t;
            if(isOK()) count++;
            else if(isPart()) solve(i+1);
        }
        p[i] = 0;
    }

    //判断当前值是否等于n
    private static boolean isOK(){
        int sum = 0;
        for(int i = 0;i<p.length;i++){
            sum += Math.pow(2,i)*p[i];
        }
        if(sum == n)return true;
        else return false;
    }

    //是否进行延伸
    private static boolean isPart(){
        int sum = 0;
        for(int i=0;i<p.length;i++){
            sum += Math.pow(2,i)*p[i];
        }
        if (sum<n)return true;
        else return false;
    }

    /*-----------------动态规划法---------------------*/
    /**
     * 使用res[n,i]表示：使用1，1，2，2，4，4，...，2^i，2^i可以组合出n的方案数
     * res[n,i]=1，当n=0，即所有面值的硬币所取数目都为0
     * res[n,i]=1，当n=1，即只取一个一元的硬币
     * res[2,0]=1，即只取两个一元硬币
     * res[n,0]=0，当n>=3，因为无法只使用1，1组成大于等于3的组合
     * res[n,i]=sum(res[n-2^i*m,i-1]) n,i取其他，0=<m<=2
     */
    private static int[][] res = null;
    //初始化数组
    private static void init2(){
        double lo = Math.log(n)/Math.log(2);
        int length = (int) lo+1;
        res = new int[n + 1][length];
        System.out.println(res[0].length);
        for(int i=0;i<res[0].length;i++){
            res[0][i] = 1;
            res[1][i] = 1;
        }
        res[1][0] = 1;
        res[2][0] = 1;
    }

    //动态规划
    private static final int solve2(){
        if(n == 0) return 1;
        if(n == 1) return 1;
        init2();
        for(int i=1;i<n+1;i++){
            for(int j = 1;j<res[0].length;j++){
                int sum = 0;
                for(int m = 0;m<3;m++){
                    int rest = (int)(i-Math.pow(2,j)*m);
                    System.out.println(rest);
                    if(rest>=0){
                        sum += res[rest][j-1];
                    }
                }
                res[i][j] = sum;
            }
        }
        return res[n][res[0].length-1];
    }

    /**
     * 将硬币分为两份：1，2，4，8，16，.....和1，2，4，8，16....
     * 组成两个数值为a,b的两个数字，他们的和是a+b=n;
     * a在每一份中只可能有一种组合方式（二进制的思想）。
     * 将a和b使用二进制表示，那么对于n=11，有a=101，b=110这种组合，即a=1+0+4=5,b=0+2+4=6。但是，请注意，对于a和b，在相同位取不同值，只有一种组合方法。
     * 如111+100和101+110（即交换中间位）本质上都是同一种组合方法，因此对于该类型可以使用二进制异或进行去重
     */
    private static void solve3(int n){
        if(n<=2){
            System.out.println(n);
            return;
        }
        Set<Integer> countSet = new HashSet<Integer>();
        int stop = n/2;
        for(int i = 1;i<=stop;i++){
            int result = (i)^(n-i);//异或a和b
            countSet.add(result);
        }
        System.out.println(countSet.size());
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.close();
        double start = System.currentTimeMillis();
        init();
        solve(0);
        System.out.println(count);
        System.out.println("用时："+(System.currentTimeMillis()-start));
        double start2 = System.currentTimeMillis();
        int result = solve2();
        System.out.println(result);
        System.out.println("用时："+(System.currentTimeMillis()-start2));
        double start3 = System.currentTimeMillis();
        solve3(n);
        System.out.println("用时："+(System.currentTimeMillis()-start3));
    }
}
