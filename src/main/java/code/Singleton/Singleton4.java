package code.Singleton;

/**
 * 双重检查，synchronized保证了INSTANCE写操作对其它线程读操作的可见性
 * volatile关键字保证顺序性。
 * 具体来说，INSTANCE = new Singleton4()不是原子操作，
 * 实际上被拆分为了三步：1) 分配内存；2) 初始化对象；3) 将INSTANCE指向分配的对象内存地址。
 * 如果没有volatile，可能会发生指令重排，使得INSTANCE先指向内存地址，而对象尚未初始化，
 * 其它线程直接使用INSTANCE引用进行对象操作时出错
 */
public class Singleton4 {
    private static volatile Singleton4 INSTANCE;
    private Singleton4() {};
    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized(Singleton4.class){
                if(INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }  
}
