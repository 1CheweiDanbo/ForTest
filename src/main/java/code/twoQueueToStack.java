package code;

import code.Exception.Exception;
import java.util.LinkedList;

public class twoQueueToStack {
    LinkedList<Integer> queue1 = new LinkedList<>();
    LinkedList<Integer> queue2 = new LinkedList<>();

    public void push(int x){
        if(queue2.isEmpty()){
            queue2.offer(x);
        } else{
            queue1.offer(x);
        }
    }

    public void pop() throws Exception {
        if(queue1.isEmpty() && queue2.isEmpty()){
            throw new Exception("queue is empty！");
        } else if(queue1.isEmpty()){
            while (queue2.size()>1)
                queue1.offer(queue2.poll());
            queue2.poll();
        } else {
            while (queue1.size()>1)
                queue2.offer(queue1.poll());
            queue1.poll();
        }
    }
}
