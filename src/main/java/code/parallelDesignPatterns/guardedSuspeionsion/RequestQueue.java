package code.parallelDesignPatterns.guardedSuspeionsion;

import java.util.LinkedList;

public class RequestQueue {
    private LinkedList<Request> queue = new LinkedList<>();

    public synchronized Request getReuqest(){
        while (queue.size() == 0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return queue.removeFirst();
    }

    public synchronized void addRequest(Request request){
        queue.add(request);
        notifyAll();
    }
}
