package code.Singleton;

/**
 * 同步代码块下的懒汉，不可用
 */
public class Singleton3 {
    private static Singleton3 INSTANCE;
    private Singleton3() {};
    public static Singleton3 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton3.class) {
                INSTANCE = new Singleton3();
            }
        }
        return INSTANCE;
    }
}
