package test;

public class Proxy1 {
    private static Proxy1 instance;
    private Proxy1(){}
    public static Proxy1 getInstance(){
        if(instance == null){
            instance = new Proxy1();
        }
        return instance;
    }
}
