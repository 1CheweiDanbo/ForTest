package code.Singleton;

/**
 * 线程不安全的懒汉单例模式
 * 饿汉 声明实例引用时即实例化
 * 懒汉 静态方法第一次被调用前不实例化，也即懒加载。
 * 对于创建实例代价大，且不定会使用时，使用懒加载模式可以减少开销
 *
 */
public class Singleton1 {
    private static Singleton1 Instance;

    private Singleton1(){};
    public  static Singleton1 getInstance(){
        if(Instance != null){
            Instance = new Singleton1();
        }
        return Instance;
    }
}
