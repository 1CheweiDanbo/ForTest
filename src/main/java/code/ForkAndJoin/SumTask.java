package code.ForkAndJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.lang.ClassLoader;

public class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 100;
    Long[] array;
    int start;
    int end;

    SumTask(Long[] array,int start,int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start <THRESHOLD){
            long sum = 0;
            for(int i=start;i<end;i++){
                sum += array[i];
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            System.out.println(String.format("compute %d ~%d  = %d",start,end,sum));
            return sum;
        }
        int middle = (end-start)+start;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask sumTask1 = new SumTask(this.array,start,middle);
        SumTask sumTask2 = new SumTask(this.array,middle,end);
        invokeAll(sumTask1,sumTask2);
        Long subResult1 = sumTask1.join();
        Long subResult2 = sumTask2.join();
        Long result = subResult1+subResult2;
        System.out.println("result = "+subResult1+" + "+subResult2 +" = "+result);
        return result;
    }

    public static void main(String[] args) {
        Long[] array = new Long[100];
        fillRandom(array);

        ForkJoinPool fjl = new ForkJoinPool(4);
        ForkJoinTask<Long> task = new SumTask(array,0,array.length);
        long startTime = System.currentTimeMillis();
        Long result = fjl.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("time:"+(endTime-startTime));
    }

    private static void fillRandom(Long[] array) {
    }
}
