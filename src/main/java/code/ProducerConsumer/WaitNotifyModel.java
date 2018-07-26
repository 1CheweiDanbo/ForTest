package code.ProducerConsumer;

import code.ProducerConsumer.AbstractFactoryPattern.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyModel implements Model{

    /**
     * Java 中可以用 wait、notify 和 notifyAll 来实现线程间的通信
     * 如果你的Java程序中有两个线程——即生产者和消费者，那么生产者可以通知消费者，
     * 让消费者开始消耗数据，因为队列缓冲区中有内容待消费（不为空）。
     * 相应的，消费者可以通知生产者可以开始生成更多的数据，
     * 因为当它消耗掉某些数据后缓冲区不再为满
     * 在多线程间共享的那个Object来使用wait。在生产者消费者问题中，这个共享的Object就是那个缓冲区队列。
     * 在多个线程间被共享的对象。在生产者消费者问题中，应该被synchronized的就是那个缓冲区队列。
     */

    private final Object BUFFER_LOCK = new Object();
    private final Queue<Task> buffer = new LinkedList<>();
    private final int cap;
    private final AtomicInteger increTaskNo = new AtomicInteger();

    public WaitNotifyModel(int cap) {
        this.cap = cap;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer,Runnable{

        @Override
        public void consume() throws InterruptedException {
            synchronized (BUFFER_LOCK){
                while (buffer.size() == 0){
                    BUFFER_LOCK.wait();
                }

                Task task = buffer.poll();
                assert task != null;
                Thread.sleep(500 + (long) Math.random()*500);
                System.out.println("consume:"+task.no);
                BUFFER_LOCK.notifyAll();
            }

        }
    }
    private class ProducerImpl extends AbstractProducer implements Producer,Runnable{

        @Override
        public void produce() throws InterruptedException {
            synchronized (BUFFER_LOCK){
                while (buffer.size() == cap){
                    BUFFER_LOCK.wait();
                }
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                System.out.println("produce:"+task.no);
                BUFFER_LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Model model = new WaitNotifyModel(3);
        for (int i=0;i<2;i++){
            new Thread(model.newRunnableProducer()).start();
        }
        for(int i=0;i<4;i++){
            new Thread(model.newRunnableConsumer()).start();
        }
    }

}
