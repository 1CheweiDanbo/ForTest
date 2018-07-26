package code;

import code.Exception.Exception;
import java.util.Stack;

public class twoStackToQuene {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node){
        stack1.push(node);
    }

    public int pop() throws Exception {
        if(stack2.size()<=0){
            while (stack1.size()>0){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()){
            throw new Exception("queue is empty!");
        }
        int head  = stack2.pop();
        return head;
    }
}
