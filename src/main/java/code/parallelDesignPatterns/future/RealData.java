package code.parallelDesignPatterns.future;

public class RealData implements Data{
    protected String data;
    public RealData(String data){
        try{
            Thread.sleep(10000);//模拟RealData的构造过程缓慢
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        this.data = data;
    }

    @Override
    public String getResult() throws InterruptedException {
        return data;
    }
}
