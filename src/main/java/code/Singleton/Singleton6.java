package code.Singleton;

/**
 * 静态代码块 饿汉 可用
 *
 */
public class Singleton6 {
    private static Singleton6 INSTANCE;

    static{
        INSTANCE = new Singleton6();
    }
    private Singleton6() {}
    public static Singleton6 getInstance() {
        return INSTANCE;
    }

}
