package code.Singleton;

/**
 * 静态变量 饿汉 推荐
 * 实现简单，无线程同步问题
 * 在类装载时完成实例化，若该实例一直未被使用，则会造成资源浪费
 */
public class Singleton5 {
    private static final Singleton5 INSTANCE = new Singleton5();
    private Singleton5(){
    }

    public static Singleton5 getInstance(){
        return getInstance();
    }
}
