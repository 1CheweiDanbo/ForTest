package code.parallelDesignPatterns.guardedSuspeionsion;

public class ServerThread extends Thread{
    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue,String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run(){
        while (true){
            final Request request = requestQueue.getReuqest();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" handles "+ request);
        }
    }
}
