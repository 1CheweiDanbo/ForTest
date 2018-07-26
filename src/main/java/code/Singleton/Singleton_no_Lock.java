package code.Singleton;

import java.util.concurrent.atomic.AtomicBoolean;

public class Singleton_no_Lock {
    private static AtomicBoolean shouldEnter = new AtomicBoolean(false);
    private static AtomicBoolean shouldLeave = new AtomicBoolean(false);

    private static volatile Singleton_no_Lock Instance;
    private Singleton_no_Lock(){}
    public static Singleton_no_Lock getInstance(){
        if(Instance != null){
            if(shouldEnter.compareAndSet(false,true)){
                Instance = new Singleton_no_Lock();
                shouldLeave.set(true);
            }
            while (!shouldLeave.get()){}
        }
        return Instance;
    }
}
