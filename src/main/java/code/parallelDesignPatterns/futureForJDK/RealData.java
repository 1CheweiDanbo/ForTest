package code.parallelDesignPatterns.futureForJDK;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
    private String param;

    public RealData(String param){
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(param);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
