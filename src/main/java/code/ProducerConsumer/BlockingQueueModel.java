package code.ProducerConsumer;

import code.ProducerConsumer.AbstractFactoryPattern.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueModel implements Model {

    /**
     * BlockingQueue是一种特殊的Queue，若BlockingQueue是空的，
     * 从BlockingQueue取东西的操作将会被阻断进入等待状态直到BlocingkQueue进了新货才会被唤醒。
     * 同样，如果BlockingQueue是满的任何试图往里存东西的操作也会被阻断进入等待状态，
     * 直到BlockingQueue里有新的空间才会被唤醒继续操作。
     *
     * 根据不同的需要BlockingQueue有4种具体实现：
     * （1）ArrayBlockingQueue：规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小。其所含的对象是以FIFO（先入先出）顺序排序的。
     * （2）LinkedBlockingQueue：大小不定的BlockingQueue，若其构造函数带一个规定大小的参数，生成的BlockingQueue有大小限制，
     * 若不带大小参数，所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。其所含的对象是以FIFO（先入先出）顺序排序的。
     * LinkedBlockingQueue和ArrayBlockingQueue比较起来，它们背后所用的数据结构不一样，
     * 导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue，但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue。
     * （3）PriorityBlockingQueue：类似于LinkedBlockingQueue，但其所含对象的排序不是FIFO，而是依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序。
     * （4）SynchronousQueue：特殊的BlockingQueue，对其的操作必须是放和取交替完成的。
     */
    private final BlockingQueue<Task> queue;

    /**
     * AtomicInteger提供原子操作来进行Integer的使用，因此十分适合高并发情况下的使用
     */
    private final AtomicInteger increTaskNo = new AtomicInteger();

    public BlockingQueueModel(int cap) {
        //LinkedBlockingQueue 的队列不init，入队时检查容量，
        // ArrayBlockingQueue 在创建时init
        this.queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    /**
     * 每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。
     * 解决了多重继承，接口只是解决了部分问题，而内部类使得多重继承的解决方案变得更加完整
     * 1、内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。
     * 2、在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类。
     * 3、创建内部类对象的时刻并不依赖于外围类对象的创建。
     * 4、内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。
     * 5、内部类提供了更好的封装，除了该外围类，其他类都不能访问。
     *
     * 注意：内部类是个编译时的概念，一旦编译成功后，它就与外围类属于两个完全不同的类（当然他们之间还是有联系的）。
     * 对于一个名为OuterClass的外围类和一个名为InnerClass的内部类，在编译成功后，会出现这样两个class文件：
     * OuterClass.class和OuterClass$InnerClass.class。
     * 在Java中内部类主要分为成员内部类、局部内部类、匿名内部类、静态内部类
     */
    public class ConsumerImpl extends AbstractConsumer implements Consumer,Runnable {

        @Override
        public void consume() throws InterruptedException {
            /**
             * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
             * remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
             * element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
             * offer       添加一个元素并返回true       如果队列已满，则返回false
             * poll         移除并返问队列头部的元素    如果队列为空，则返回null
             * peek       返回队列头部的元素             如果队列为空，则返回null
             * put         添加一个元素                      如果队列满，则阻塞
             * take        移除并返回队列头部的元素     如果队列为空，则阻塞
             */
            System.out.println("the queue size is "+queue.size());
            Task task = queue.take();
            Thread.sleep(500 + (long) Math.random()*500);
            System.out.println("consume:" + task.no);
        }
    }
    public class ProducerImpl extends AbstractProducer implements Producer,Runnable{
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep(500 + (long) Math.random()*500);
            Task task = new Task(increTaskNo.getAndIncrement());
            System.out.println("produce:"+task.no);
            queue.put(task);
            System.out.println("after produce the size of queue is "+queue.size());
        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        for (int i=0;i<2;i++){
            new Thread(model.newRunnableConsumer()).start();
        }
        for(int i=0;i<4;i++){
            new Thread(model.newRunnableProducer()).start();
        }
    }
}

