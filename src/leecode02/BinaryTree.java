package leecode02;

import javax.jnlp.ClipboardService;
import java.util.*;

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

    //统一迭代法
    public static List<Integer> preorderTraversal3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
                stack.push(node);
                stack.push(null);
            }else {
                node = stack.pop();
                res.add(node.val);
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

    //迭代法
    public static List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if(root == null){
            return res;
        }
        stack.push(root);
        //左右中  中右左
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(root.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }

        Collections.reverse(res);//记住方法
        return res;
    }

    //统一迭代法
    public static List<Integer> postorderTraversal3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                stack.push(node);
                stack.push(null);
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }else {
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
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

    //迭代方法
    public static List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        //创建指针
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }

        return res;
    }

    //使用统一迭代法
    //右中左 进入栈中
    public static List<Integer> inorderTraversal3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node != null){
                if(node.right != null){
                    stack.push(node.right);
                }
                stack.push(node);
                stack.push(null);
                if(node.left != null){
                    stack.push(node.left);
                }
            }else {
                node = stack.pop();
                res.add(node.val);
            }
        }

        return res;

    }

    /**
     * 题目：102 二叉树的层序遍历
     */
    //迭代法
    public static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode temp = que.poll();
                list.add(temp.val);
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    //递归法
    public static List<List<Integer>> levelOrder2(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        checkFun(root, 0, res);
        return res;
    }

    public static void checkFun(TreeNode node, Integer deep, List<List<Integer>> res){
        if(node == null){
            return;
        }
        deep++;
        if(res.size() < deep){
            List<Integer> item = new ArrayList<>();
            res.add(item);
        }
        res.get(deep - 1).add(node.val);

        checkFun(node.left, deep, res);
        checkFun(node.right, deep, res);
    }

    /**
     * 题目：107 二叉树的层序遍历II
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                list.add(node.val);
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 题目：199 二叉树的右视图
     */
    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                if(i == len - 1){
                    res.add(node.val);
                }
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 题目：637 二叉树的层平均值
     */
    public static List<Double> averageOfLevels(TreeNode root){
        List<Double> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            double sum = 0;
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                sum += node.val;
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            res.add(sum / len);
        }
        return res;
    }


    /**
     * 题目：429 N叉树的层序遍历
     */
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                Node node = que.poll();
                list.add(node.val);
                for(Node child : node.children){
                    que.offer(child);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 题目：515 在每个树行中找最大值
     */
    public static List<Integer> largestValues(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            int ans = Integer.MIN_VALUE;
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                ans = Math.max(ans, node.val);
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            res.add(ans);
        }
        return res;
    }

    /**
     * 题目：116 填充每个节点的下一个右侧节点指针
     */
    public static Node connect(Node root) {
        if(root == null){
            return null;
        }
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                Node node = que.poll();
                if(i == len - 1){
                    node.next = null;
                }else{
                    node.next = que.peek();
                }
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
        }

        return root;
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

class Node{
    int val;
    List<Node> children;

    Node left;
    Node right;
    Node next;

    public Node() {
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
