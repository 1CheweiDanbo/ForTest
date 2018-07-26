package code.parallelDesignPatterns.masterWorker;

public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input){
        int i = (Integer) input;
        return i * i * i;
    }
}
