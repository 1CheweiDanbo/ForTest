package code.ProducerConsumer.AbstractFactoryPattern;

public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
