package code.locks;

import java.util.Random;
import java.util.concurrent.locks.*;

public class Data {
    private Object data = null;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" is ready to read;");
            Thread.sleep(new Random().nextInt(100));
            System.out.println(Thread.currentThread().getName()+" have read data "+data);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void put(){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" is ready to write;");
            Thread.sleep(new Random().nextInt(100));
            System.out.println(Thread.currentThread().getName()+" have write data "+data);
        } catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
