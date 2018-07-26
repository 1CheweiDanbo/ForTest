package code.Singleton;

/**
 *同步方法下的懒汉
 *  线程安全，但每次获取实例都需要申请锁，开销大，效率低 可用，但不推荐
 */

public class Singleton2 {
    private static Singleton2 Intance;
    private Singleton2 (){};
    public static synchronized Singleton2 getIntance(){
        if(Intance != null){
            Intance  = new Singleton2();
        }
        return Intance;
    }
}
