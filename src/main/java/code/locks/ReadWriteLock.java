package code.locks;

public class ReadWriteLock {
    public static void main(String[] args) {
        final Data data = new Data();
        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        data.get();
                    }
                }
            }).start();
        }

        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.put();
                }
            }).start();
        }
    }
}
