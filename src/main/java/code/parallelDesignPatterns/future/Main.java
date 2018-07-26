package code.parallelDesignPatterns.future;

import com.sun.xml.internal.ws.client.ClientTransportException;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为获取的是FutureData，而非RealData
        Data data = client.request("name");
        System.out.println("请求完毕！");
        try{
            //用sleep代替对其他业务逻辑的处理
            //在处理其他业务逻辑的过程中，RealData也在被创建，充分利用等待时间
            Thread.sleep(20000);
            //真实数据
            System.out.println("数据："+data.getResult());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
