package leecode02;

import javax.jnlp.ClipboardService;
import java.util.*;
import java.util.LinkedList;

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

    /**
     * 题目：117 填充每个节点的下一个右侧节点II
     */
    public static Node connect2(Node root){
        if(root == null){
            return root;
        }

        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                Node node = que.poll();
                if(i == len - 1){
                    node.next = null;
                }else {
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

    /**
     * 题目：104 二叉树的最大深度
     */
    public static int maxDepth(TreeNode root){
        int res = 0;
        if(root == null){
            return res;
        }

        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }
            res++;
        }
        return res;
    }

    /**
     * 题目：111 二叉树的最小深度
     */
    //层序遍历
    public static int minDepth(TreeNode root){
        int res = 0;
        if(root == null){
            return res;
        }

        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            res++;
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                if(node.left == null && node.right == null){
                    return res;
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

    //递归方法
    public static int minDepth2(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }

        //后序遍历
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);

        if(left == 0 && right != 0){
            return right + 1;
        }
        if(left != 0 && right == 0){
            return left + 1;
        }

        return 1 + Math.min(left, right);

    }

    /**
     * 题目：226 翻转二叉树
     */
    //递归
    public static TreeNode invertTree(TreeNode root){
        //终止条件
        if(root == null){
            return null;
        }

        //前序遍历
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //迭代法
    public static TreeNode invertTree2(TreeNode root){
        if(root == null){
            return null;
        }
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size = que.size();
            while(size -- > 0){
                TreeNode node = que.poll();
                swap(node);
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

    public static void swap(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;;
        root.right = temp;
    }

    /**
     * 题目：101 对称二叉树
     */
    //递归实现
    public static boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }
        return isSymmetric2(root.left, root.right);
    }

    public static boolean isSymmetric2(TreeNode left, TreeNode right){
        //终止条件
        if(left == null && right == null){
            return true;
        }else if(left != null && right == null){
            return false;
        }else if(left == null && right != null){
            return false;
        }else if(left.val != right.val){
            return false;
        }

        //当左右相等时 向下递归
        boolean outside = isSymmetric2(left.left, right.right);
        boolean inside = isSymmetric2(left.right, right.left);

        return outside && inside;
    }

    //使用迭代法
    public static boolean isSymmetric3(TreeNode root){
        if(root == null){
            return true;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root.left);
        que.offer(root.right);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode left = que.poll();
                TreeNode right = que.poll();
                if(left == null && right == null){
                    continue;
                }
                if(left == null && right != null){
                    return false;
                }
                if(left != null && right == null){
                    return false;
                }
                if(left.val != right.val){
                    return false;
                }
                que.offer(left.left);
                que.offer(right.right);
                que.offer(left.right);
                que.offer(right.left);
            }
        }
        return true;
    }

    /**
     * 题目：100 相同的树
     */
    //递归
    public static boolean isSameTree(TreeNode p, TreeNode q){
        //终止调价
        if(p == null && q == null){
            return true;
        }
        if(p != null && q == null){
            return false;
        }
        if(p == null && q != null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    //迭代
    public static boolean isSameTree2(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(p);
        que.offer(q);

        while(!que.isEmpty()){
            TreeNode pNode = que.poll();
            TreeNode qNode = que.poll();
            //判断
            if(pNode == null && qNode == null){
                continue;
            }
            if(pNode != null && qNode == null){
                return false;
            }
            if(pNode == null && qNode != null){
                return false;
            }
            if(pNode.val != qNode.val){
                return false;
            }

            que.offer(pNode.left);
            que.offer(qNode.left);
            que.offer(pNode.right);
            que.offer(qNode.right);
        }
        return true;
    }

    /**
     * 题目：572 另一个树的子树
     */
    //层序遍历
    public static boolean isSubtree(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.val == subRoot.val){
                    if(isSameTree3(temp, subRoot)){
                        return true;
                    }
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return false;
    }

    //使用队列
    public static boolean isSameTree3(TreeNode a, TreeNode b){
        if(a == null && b == null){
            return true;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(a);
        que.offer(b);

        while(!que.isEmpty()){
            TreeNode temp1 = que.poll();
            TreeNode temp2 = que.poll();
            //判断
            if(temp1 == null && temp2 == null){
                continue;
            }
            if(temp1 == null || temp2 == null){
                return false;
            }
            if(temp1.val != temp2.val){
                return false;
            }

            que.offer(temp1.left);
            que.offer(temp2.left);
            que.offer(temp1.right);
            que.offer(temp2.right);
        }
        return true;
    }

    //方法二
    public static boolean isSubtree2(TreeNode root, TreeNode subRoot){
        //终止条件
        if(subRoot == null){
            return true;
        }
        if(root == null){
            return false;
        }

        return check(root, subRoot) || isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);

    }

    public static boolean check(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null || p.val != q.val){
            return false;
        }
        return check(p.left, q.left) && check(p.right, q.right);
    }


    /**
     * 题目：104 二叉树的最大深度
     */
    public static int maxDepth2(TreeNode root){
        int res = 0;
        if(root == null){
            return res;
        }
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
            res++;
        }
        return res;
    }

    //使用递归
    public static int maxDepth3(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 题目：559：n叉树的最大深度
     */
    public static int maxDepth3(Node root){
        int res = 0;
        if(root == null){
            return res;
        }
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                Node temp = que.poll();
                for(Node child: temp.children){
                    if(child != null){
                        que.offer(child);
                    }
                }
            }
            res++;
        }
        return res;
    }

    //使用递归
    public static int maxDepth4(Node root){
        //终止条件
        if(root == null){
            return 0;
        }
        int depth = 0;
        if(root.children != null){
            for (Node child : root.children) {
                depth = Math.max(maxDepth4(child), depth);
            }
        }
        return depth + 1;
    }

    /**
     * 题目：111 二叉树的最小深度
     */
    //迭代法
    public static int minDepth3(TreeNode root){
        int res = 0;
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            res++;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.left == null && temp.right == null){
                    return res;
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return res;
    }

    //递归法
    public static int minDepth4(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        int left = minDepth4(root.left);
        int right = minDepth4(root.right);

        if(left == 0 && right != 0){
            return right + 1;
        }
        if(left != 0 && right == 0){
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * 题目：222 完全二叉树的节点个数
     */
    //计算普通二叉树
    public static int countNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        int res = 0;
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                res++;
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return res;
    }

    //通用递归法
    public static int countNodes2(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }

        int left = countNodes2(root.left);
        int right = countNodes2(root.right);

        return left + right + 1;
    }

    //针对完全二叉树的解法
    //满二叉树的节点数为 2 ^ depth - 1
    public static int countNode3(TreeNode root){
        if(root == null){
            return 0;
        }
        //开始根据左右深度是否相同来判断该子树是不是满二叉树
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        int leftDepth = 1;
        int rightDepth = 1;

        //求左子树的深度
        while(leftNode != null){
            leftNode = leftNode.left;
            leftDepth++;
        }

        //求右子树深度
        while(rightNode != null){
            rightNode = rightNode.right;
            rightDepth++;
        }

        if(leftDepth == rightDepth){//满足二叉树
            return (int)(Math.pow(2, leftDepth) - 1);
        }

        return countNode3(root.left) + countNode3(root.right) + 1;
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
