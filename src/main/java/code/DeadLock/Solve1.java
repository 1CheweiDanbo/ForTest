package code.DeadLock;

public class Solve1 implements Runnable {
    /**
     * 对对象进行锁定，加大锁定的粒度，
     * 例如使进程锁定当前对象，而不是逐步锁定当前对象的两个子对象o1和o2
     * 这样就在t1锁定o1之后， 即使发生休眠，当前对象仍然被t1锁定，
     * t2不能打断t1去锁定o2，等t1休眠后再锁定o2，获取资源，执行成功。
     * 然后释放当前对象t2，接着t1继续运行
     *
     * 代码修改成public synchronized void run(){..},去掉子对象锁定。
     * 对于一个成员方法加synchronized关键字，实际上是以这个成员方法所在的对象本身作为对象锁。
     * 此例中，即对td1，td2这两个Slove1 对象进行加锁
     */
    public int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    public synchronized void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("1");
        }
        if (flag == 0) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("0");
        }
    }

    public static void main(String[] args) {
        Solve1 td1 = new Solve1();
        Solve1 td2 = new Solve1();
        td1.flag = 1;
        td2.flag = 0;
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();
    }
}
