package code.ProducerConsumer;

import code.ProducerConsumer.AbstractFactoryPattern.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionModel implements Model {
    private final Lock BUFFER_LOCK = new ReentrantLock();
    private final Condition BUFFER_CON = BUFFER_LOCK.newCondition();
    private final Queue<Task> buffer = new LinkedList<>();
    private final int cap;
    private final AtomicInteger increTaskNo = new AtomicInteger();

    public LockConditionModel(int cap) {
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

    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

        @Override
        public void consume() throws InterruptedException {
            BUFFER_LOCK.lockInterruptibly();//可中断锁
            try {
                while (buffer.size() == 0){
                    BUFFER_CON.await();
                }
                Task task = buffer.poll();
                assert task != null;
                Thread.sleep(500 + (long)Math.random());
                System.out.println("consume:"+task.no);
                BUFFER_CON.signalAll();

            } finally {
              BUFFER_LOCK.unlock();
            }

        }
    }

    private class ProducerImpl extends AbstractProducer implements Producer, Runnable {

        @Override
        public void produce() throws InterruptedException {
            Thread.sleep(500 + (long)Math.random());
            BUFFER_LOCK.lockInterruptibly();
            try {
                while (buffer.size() == cap){
                    BUFFER_CON.await();
                }
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                System.out.println("produce:"+task.no);
                BUFFER_CON.signalAll();
            } finally {
                BUFFER_LOCK.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Model model = new WaitNotifyModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
        for (int i = 0; i < 4; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
    }
}
