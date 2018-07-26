package code.parallelDesignPatterns.future;

public class Client {
    public Data request(final String string){
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //RealData的构造很慢，所以放在单独的线程中执行
                RealData realData = new RealData(string);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }
}
