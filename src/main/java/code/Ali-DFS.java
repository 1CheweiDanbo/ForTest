package code;
import java.util.*;
class Point {
    int x;
    int y;
    boolean visited;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
        this.visited=false;
    }
    public int getLength(Point point){
        //注意：只能沿方格行走时的两点之间的距离
        return Math.abs(x-point.x)+Math.abs(y-point.y);
    }
}

class Main {

    /** 请完成下面这个函数，实现题目要求的功能 **/
    /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/

    static Point Start = new Point(0,0);
    static int minPath = Integer.MAX_VALUE;

    static int calculate(Point start, Point[] points, int sum, int count) {
        if(count==points.length){
            minPath=Math.min(minPath,sum + start.getLength(Start));
            return minPath;
        }
        for(int i=0;i<points.length;i++){
            if(points[i].visited==false){
                sum+=points[i].getLength(start);
                if(sum<minPath){
                    points[i].visited=true;
                    calculate(points[i],points,sum,count+1);
                }
                sum-=points[i].getLength(start);
                points[i].visited=false;

            }
        }
        return minPath;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());
        Point[] points=new Point[num];
        for (int i = 0;i<num;i++){
            String str[]=scanner.nextLine().trim().split(",");
            points[i]=new Point(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
        }
        System.out.println(calculate(Start,points,0,0));
    }
}