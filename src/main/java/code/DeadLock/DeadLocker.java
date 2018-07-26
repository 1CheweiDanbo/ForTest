package code.DeadLock;

public class DeadLocker implements Runnable {

    public int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println("the flag is "+flag);
        if(flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if(flag == 0){
            synchronized (o2){
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLocker dl1 = new DeadLocker();
        DeadLocker dl2 = new DeadLocker();
        dl1.flag = 1;
        dl2.flag = 0;
        Thread t1 = new Thread(dl1);
        Thread t2 = new Thread(dl2);//Thread t2 = new Thread(td1);顺序访问时避免死锁
        t1.start();
        t2.start();
    }
}
