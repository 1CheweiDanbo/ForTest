package code.printBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class printBinaryTree {

    public ArrayList<Integer> print(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null)
            return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left !=null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
        return list;
    }
}
