package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TencentDigitalConversion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] value = br.readLine().trim().split(" ");
        int a = Integer.parseInt(value[0]);
        int b = Integer.parseInt(value[1]);
        int A = Integer.parseInt(value[2]);
        int B = Integer.parseInt(value[3]);
        int res1 = transfer(a,A);
        int res2 = transfer(b,B);
        if((res1 == res2) && (-1 != res1)){
            System.out.println(res1);
        }else{
            System.out.println(-1);
        }
        int res3 = transfer2(a,A);
        int res4 = transfer2(b,B);
        if((res3 == res4) && (-1 != res3)){
            System.out.println(res3);
        }else{
            System.out.println(-1);
        }
        int res5 = transfer3(a,A);
        int res6 = transfer3(b,B);
        if((res5 == res6) && (-1 != res5)){
            System.out.println(res5);
        }else{
            System.out.println(-1);
        }
    }

    /**
     * 递归 定义状态
     * transfer(before,after)表示从数字before转化到数字after最少转化次数
     * 定义状态转移方程
     * 自顶而下分析，最后一步转化无非两种情况，前一步结果*2或者+1，那么前一次结果为after/2或者after-1。
     * 最后一步经历的转化次数无非是transfer(before,after/2)或者transfer(before,after-1)。
     * 显然可得，取最小的transfer(before,after) = min(transfer(before,after/2),transfer(before,after-1))+1
     * 递归边界最终会有两种情况，一种是before刚好与after相同，说明这是可以转换。一种是before>after，说明不能完成转化。
     *
     * @param before 转化前
     * @param after 转化后
     * @return
     */

    private static int transfer(int before, int after) {
        if (before == after)
            return 0;
        if (before > after)
            return -1;
        int res1 = transfer(before,after/2);
        int res2 = transfer(before,after-1);
        if(-1 == res1 && -1 == res2){
            return -1;
        }else if(-1 == res1){
            return res2+1;
        }else if(-1 == res2){
            return res1+1;
        }else {
            return Math.min(res1,res2)+1;
        }
    }

    static int transfer2(int before,int after){
        int[] buf = new int[after+1];
        for(int i = 0;i<buf.length;i++){
            buf[i] = -1;
        }
        return transfer2(before,after,buf);
    }

    /**
     * memorandum 备忘录法
     * 定义一个数组，数组的下标表示after，数组值表示从数字before转化到数字after最少转化次数。
     * 数组需要初始化为无效值-1，用于判断是否已经计算过
     * @param before
     * @param after
     * @param buf 数组
     */
    private static int transfer2(int before,int after,int[] buf) {
        if (before == after)
            return 0;
        if (before > after)
            return -1;
        if(-1 != buf[after])
            return buf[after];
        int res1 = transfer2(before,after/2,buf);
        int res2 = transfer2(before,after-1,buf);
        if(-1 == res1 && -1 == res2){
            return -1;
        }else if(-1 == res1){
            buf[after] = res2+1;
        }else if(-1 == res2){
            buf[after] = res1+1;
        }else{
            buf[after] = Math.min(res1,res2)+1;
        }
        return buf[after];
    }

    /**
     *  表格法
     *  用自底而上的迭代代替自顶而下的递归
     *  数组表示含义与备忘录法一致的，虽然会有一定空间浪费，时间复杂度，已经从O(2^n)降低到O(n)
     *  通常有时间限制的DP，优先使用表格法，备忘录法在一些情况会比表格法快，基本不使用递归法。
     *  使用回溯法可以把表格法和备忘录法获得的存储中间值，转化成获得最优解具体的操作，
     *  例如本题中，小Q究竟是依次按了哪些按钮获得last值。
     * @param before
     * @param after
     * @return
     */
    private static int transfer3(int before,int after) {
        int[] buf = new int[after+1];
        for(int i= 0;i<buf.length;i++){
            buf[i] = -1;
        }
        for(int i=before+1;i<after+1;i++){
            if(i/2 >= before && i-1 >= before){
                buf[i] = Math.min(buf[i/2+1],buf[i-1])+1;//可能有问题
            } else if(i/2 >= before){
                buf[i] = buf[i/2]+1;
            } else if(i-1 >= before){
                buf[i] = buf[i-1]+1;
            } else {
                buf[i] = -1;
            }
        }
        return buf[after];
    }
}
