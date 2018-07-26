package code.Singleton.destroySingleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    String name;
    private Singleton (){
        System.out.println("new Singleton");
        name = "Singleton";
    }

    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }

    private Object readResolve(){
        return instance;
    }
}
