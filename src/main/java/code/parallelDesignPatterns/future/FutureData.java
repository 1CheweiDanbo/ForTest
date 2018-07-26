package code.parallelDesignPatterns.future;

public class FutureData implements Data{
    RealData realData = null; //FutureData是realData的封装
    boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if(isReady)
            return;
        this.realData = realData;
        isReady = true;
        notifyAll();//RealData已经被注入到FutureData中，通知getResult()方法
    }

    @Override
    public String getResult() throws InterruptedException {
        if(!isReady)
            wait();//一直等到RealData注入到FutureData
        return realData.getResult();
    }
}
