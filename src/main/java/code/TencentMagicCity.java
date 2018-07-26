package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class TencentMagicCity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        str = br.readLine().trim();
        int start = str.trim().indexOf(" ");
        int end = str.trim().lastIndexOf(" ");
        String str1 = str.substring(0,start);
        String str2 = str.substring(end+1,str.length());
        int n = Integer.parseInt(str1);
        int k = Integer.parseInt(str2);
        int[][] dis = new int[n][n];
        for(int i=0;i<n;i++){
            String value = br.readLine().trim();
            for(int j = 0;j<value.length();j++){
                dis[i][j] = Integer.parseInt(String.valueOf(value.charAt(j)));
            }
        }
        float maxTime = 2^32;
        //链表的每个元素存储着单一通路的行走时间,遍历链表中的元素
        ArrayList<int[]> arr = magicCity(n,dis);
        for(Iterator<int[]> iterator = arr.iterator();iterator.hasNext();){
            int[] temp = iterator.next();
            for(int i = 0;i<temp.length;i++){
                System.out.print(temp[i]+"   ");
            }
            System.out.println();
        }

        //遍历链表中的元素，打印所需时间最少的通路所用时间
        for(Iterator<int[]> iterator = arr.iterator();iterator.hasNext();){
            int[] temp = iterator.next();
            sortTime(temp,0,temp.length-1);
            float realTime = 0;
            if(k>=temp.length){
                for(int i =0;i<temp.length;i++){
                    realTime += temp[i]/2;
                }
            }else {
                for(int i=0;i<k;i++){
                    realTime += temp[i]/2;
                }
                for(int i=k;i<temp.length;i++){
                    realTime += temp[i];
                }
            }
            if(realTime<maxTime) maxTime = realTime;
        }
        System.out.println(maxTime);
    }

    private static ArrayList<int[]> magicCity(int cityNum, int[][] dis) {
        ArrayList<int[]> arr = new ArrayList<int[]>();
        ArrayList<String> arr_temp = new ArrayList<String>();
        String[] cities = new String[cityNum];
        for(int i = 0;i<cityNum;i++){
            cities[i] = Integer.toString(i);
        }
        arr_temp.add(cities[0]);
        magicCity(cities,dis,arr,arr_temp);
        return arr;
    }

    private static void magicCity(String[] cities, int[][] dis, ArrayList<int[]> arr, ArrayList<String> arr_temp) {
        if(arr_temp.contains(cities[2])){ //递归结束，到达城市1
            int[] temp = new int[arr_temp.size()-1];
            for(int k = 0;k<arr_temp.size()-1;k++){
                int m = Integer.parseInt(arr_temp.get(k));
                int n = Integer.parseInt(arr_temp.get(k+1));
                 temp[k] = dis[m][n];
            }
            arr.add(temp);
            return;
        }
        for (int i = 0;i<cities.length;i++){
            if(!arr_temp.contains(cities[i])){
                arr_temp.add(cities[i]);
                magicCity(cities,dis,arr,arr_temp);
                arr_temp.remove(arr_temp.size()-1);
            }
        }
    }

    public static void sortTime(int[] arr,int p,int q)
    {
        if (p < q) {
            int i = Partition(arr,p,q);
            sortTime(arr,p,i-1);
            sortTime(arr,i+1,q);
        }
    }

    public static int Partition(int[] arr,int p,int q)
    {
        int x = arr[p];
        int i = p;
        for (int j = p+1; j <= q; j++) {
            if (arr[j] > x ) {
                i = i+1;
                Swap(arr,i,j);
            }
        }
        Swap(arr,p,i);
        return i;
    }

    public static void Swap(int[] arr,int m,int n)
    {
        int temp;
        temp = arr[m];
        arr[m]=arr[n];
        arr[n]=temp;
    }
}
