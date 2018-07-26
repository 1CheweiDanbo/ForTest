package code.parallelDesignPatterns.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{

    private BlockingQueue<Data> queue;
    private volatile boolean isRunning = true;
    private static AtomicInteger count = new AtomicInteger();
    private static Random random = new Random();

    public Producer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(random.nextInt(1000));
                int id = count.incrementAndGet();
                Data data = new Data(Integer.toString(id),"数据"+id);
                this.queue.offer(data);
                System.out.println("当前线程:" +
                    Thread.currentThread().getName() +
                    ", 获取了数据，id为:" + id +
                    ", 进行装载到公共缓冲区中...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isRunning = false;
    }
}
