package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class triangle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] value = br.readLine().trim().split(" ");
        int a = Integer.parseInt(value[0]);
        int b = Integer.parseInt(value[1]);
        int c = Integer.parseInt(value[2]);
        int d = Integer.parseInt(value[3]);
        System.out.println(triangle(a,b,c,d));
    }

    private static String triangle(int a, int b, int c, int d) {
        List<String> list = new ArrayList<>();
        for(int i=0;i<4;i++){
            String str1 = Ok(a,b,c);
            list.add(str1);
            String str2 = Ok(a,b,d);
            list.add(str2);
            String str3 = Ok(a,c,d);
            list.add(str3);
            String str4 = Ok(b,c,d);
            list.add(str4);
        }
        if(list.contains("triangle")){
            return "triangle";
        } else if(list.contains("segment"))
            return "segment";
        return "impossible";
    }

    private static String Ok(int a, int b, int c) {
        if (a > b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        if (b > c) {
            b = b + c;
            c = b - c;
            b = b - c;
        }
        if (a > b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        if(c<b+a && a>c-b)
            return "triangle";
        if(c == b+a)
            return "segment";
        return "impossible";
    }
}
