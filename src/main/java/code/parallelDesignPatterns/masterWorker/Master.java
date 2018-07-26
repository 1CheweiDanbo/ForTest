package code.parallelDesignPatterns.masterWorker;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    //任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue();
    //Worker进程队列
    protected Map<String,Thread> threadMap = new HashMap();
    //子任务处理结果集
    protected Map<String,Object> resultMap = new ConcurrentHashMap();

    //构造函数
    public Master(Worker worker, int countWorker) {
        worker.setWorkQueue(workQueue); //添加任务队列
        worker.setResultMap(resultMap); //添加计算结果集合
        for(int i=0;i<countWorker;i++){
            //循环添加任务进程
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }

    }
    //是否所有子任务都结束了
    public boolean isComplete(){
        for (Map.Entry<String,Thread> entry : threadMap.entrySet()) {
            if(entry.getValue().getState() != State.TERMINATED)
                return false; //有任务未完成
        }
        return true;
    }

    //提交子任务
    public void submit(Object job){
        workQueue.add(job);
    }

    //返回子任务结果集
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    //执行所有worker进程，进行处理
    public void execute(){
        for (Map.Entry<String,Thread> entry:threadMap.entrySet()){
            entry.getValue().start();
        }
    }
}
