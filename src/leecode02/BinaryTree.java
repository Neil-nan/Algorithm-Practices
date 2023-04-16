package leecode02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree {

    /**
     * 题目：144 二叉树的前序遍历
     */
    //递归
    public static List<Integer> preorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public static void preorder(TreeNode root, List<Integer> list){
        //终止条件
        if(root == null){
            return;
        }
        //中 左 右
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    //迭代遍历
    public static List<Integer> preorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if(root == null){
            return res;
        }
        //入栈
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    }

    /**
     * 题目：145 二叉树的后序遍历
     */
    //递归
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    public static void postorder(TreeNode root, List<Integer> list){
        //终止条件
        if(root == null){
            return;
        }
        //左 右 中
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /**
     * 题目：94 二叉树的中序遍历
     */
    //递归
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public static void inorder(TreeNode root, List<Integer> list){
        //终止条件
        if(root == null){
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}

//创建节点
class TreeNode{

    int val;
    TreeNode left;
    TreeNode right;

    //构造方法
    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
