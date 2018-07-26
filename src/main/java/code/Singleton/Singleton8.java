package code.Singleton;

import java.io.Serializable;

/**
 *
 */
public class Singleton8 implements Serializable {
    private Singleton8(){}

    private static Singleton8 instance = new Singleton8();
    public static Singleton8 getInstance(){
        return instance;
    }

    private Object readResolve(){
        return instance;
    }
}
