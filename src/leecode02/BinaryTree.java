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

    /**
     * 题目：110 平衡二叉树
     */
    //递归后序
    public static boolean isBalanced(TreeNode root){
        return getHeight(root) != -1;
    }

    public static int getHeight(TreeNode node){
        //终止条件
        if(node == null){
            return 0;
        }

        //后序遍历
        int left = getHeight(node.left);
        int right = getHeight(node.right);

        //如果左右子树不满足平衡二叉树 返回-1
        if(left == -1 || right == -1){
            return -1;
        }

        if(Math.abs(left - right) > 1){
            return -1;
        }else {
            return Math.max(left, right) + 1;
        }
    }

    /**
     * 题目：257 二叉树的所有路径
     */
    static List<String> res = new ArrayList<>();
    public static List<String> binaryTreePaths(TreeNode root){
        List<Integer> list = new ArrayList<>();
        backtracking(root, list);
        return res;
    }

    public static void backtracking(TreeNode node, List<Integer> list){
        list.add(node.val);
        //终止条件
        if(node.left == null && node.right == null){
            StringBuffer sb = new StringBuffer();
            listToString(list, sb);
            res.add(sb.toString());
        }

        if(node.left != null){
            backtracking(node.left, list);
            list.remove(list.size() - 1);
        }
        if(node.right != null){
            backtracking(node.right, list);
            list.remove(list.size() - 1);
        }
    }

    public static void listToString(List<Integer> list, StringBuffer sb){
        int len = list.size();
        for(int i = 0; i < len - 1; i++){
            sb.append(list.get(i));
            sb.append("->");
        }
        sb.append(list.get(len - 1));
    }

    /**
     * 题目：404 左叶子之和
     */
    //前序遍历
    public static int sumOfLeftLeaves(TreeNode root){
        if(root == null){
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int res = 0;
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null && node.left.left == null && node.left.right == null){
                res += node.left.val;
            }
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    }

    //递归
    public static int sumOfLeftLeaves2(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        int left = sumOfLeftLeaves2(root.left);
        int right = sumOfLeftLeaves2(root.right);
        int mid = 0;

        if(root.left != null && root.left.left == null && root.left.right == null){
            mid = root.left.val;
        }

        return mid + left + right;
    }

    /**
     * 题目：513 找树左下角的值
     */
    public static int findBottomLeftValue(TreeNode root){
        if(root == null){
            return 0;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        int res = 0;
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                if(i == 0){
                    res = node.val;
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

    //递归
    static int maxDepth = -1;
    static int bottomResult = 0;
    public static int findBottomLeftValue2(TreeNode root){
        if(root == null){
            return 0;
        }
        traversal2(root, 0);
        return bottomResult;
    }

    public static void traversal2(TreeNode root, int depth){
        if(root.left == null && root.right == null){
            if(depth > maxDepth){
                maxDepth = depth;//更新最大深度
                bottomResult = root.val;
            }
            return;
        }
        if(root.left != null){
            depth++;
            traversal2(root.left, depth);
            depth--;//回溯
        }
        if(root.right != null){
            depth++;
            traversal2(root.right, depth);
            depth--;
        }
        return;
    }

    /**
     * 题目：112 路径总和
     */
    //回溯
    public static boolean hasPathSum(TreeNode root, int targetSum){
        //终止条件
        if(root == null){
            return false;
        }
        return traversal3(root, targetSum - root.val);
    }

    public static boolean traversal3(TreeNode node, int targetSum){
        //终止条件
        if(node.left == null && node.right == null){
            if(targetSum == 0){
                return true;
            }else {
                return false;
            }
        }

        if(node.left != null){
            if(traversal3(node.left, targetSum - node.left.val)){
                return true;
            }
        }
        if(node.right != null){
            if(traversal3(node.right, targetSum - node.right.val)){
                return true;
            }
        }
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        //节点指针
        Stack<TreeNode> stack1 = new Stack<>();
        //路径数值
        Stack<Integer> stack2 = new Stack<>();

        stack1.push(root);
        stack2.push(root.val);
        while (!stack1.isEmpty()){
            int size = stack1.size();

            for(int i = 0; i < size; i++){
                TreeNode tmp = stack1.pop();
                int sum = stack2.pop();

                //如果该节点是叶子节点，同时该节点的路径数值等于sum，那么就返回true
                if(tmp.left == null && tmp.right == null && sum == targetSum){
                    return true;
                }
                //左节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if(tmp.left != null){
                    stack1.push(tmp.left);
                    stack2.push(sum + tmp.left.val);
                }
                //右节点，咽进去一个节点的时候，将该节点的路径数值也记录下来
                if(tmp.right != null){
                    stack1.push(tmp.right);
                    stack2.push(sum + tmp.right.val);
                }

            }

        }
        return false;
    }

    /**
     * 题目：113 路径总和 II
     */
    static List<List<Integer>> resList;
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum){
        resList = new ArrayList<>();
        if(root == null){
            return resList;
        }
        List<Integer> list = new ArrayList<>();
        traversal4(root, targetSum - root.val, list);
        return resList;
    }

    public static void traversal4(TreeNode node, int targetSum, List<Integer> list){
        list.add(node.val);
        //终止条件
        if(node.left == null && node.right == null){
            if(targetSum == 0){
                resList.add(new ArrayList<>(list));
            }
            return;
        }

        if(node.left != null){
            traversal4(node.left, targetSum - node.left.val, list);
            list.remove(list.size() - 1);
        }
        if(node.right != null){
            traversal4(node.right, targetSum - node.right.val, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：106 从中序与后序遍历序列构造二叉树
     */
    //前序
    public static TreeNode buildTree(int[] inorder, int[] postorder){
        if(inorder.length == 0 || postorder.length == 0){
            return null;
        }
        return findNode(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    //前闭后闭区间
    public static TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd){
        //终止条件
        if(inBegin > inEnd || postBegin > postEnd){
            return null;
        }
        //找到中间结点
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);
        //判断是不是叶子结点(可以不写)
        if(postEnd - postBegin == 0){
            return root;
        }
        //找中间结点的位置
        int index;
        for(index = inBegin; index <= inEnd; index++){
            if(inorder[index] == rootValue){
                break;
            }
        }
        int lenOfLeft = index - inBegin;
        root.left = findNode(inorder, inBegin, index - 1, postorder, postBegin, postBegin + lenOfLeft - 1);
        root.right = findNode(inorder, index + 1, inEnd, postorder, postBegin + lenOfLeft, postEnd - 1);
        return root;
    }

    /**
     * 题目：105 从前序与中序遍历序列构造二叉树
     */
    public static TreeNode buildTree2(int[] preorder, int[] inorder){
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        return findNode2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    //前闭后闭空间
    public static TreeNode findNode2(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd){
        //终止条件
        if(preBegin > preEnd || inBegin > inEnd){
            return null;
        }
        //找到中间结点
        int rootValue = preorder[preBegin];
        TreeNode root = new TreeNode(rootValue);
        //判断是不是叶子结点
        if(preEnd - preBegin == 0){
            return root;
        }
        //找到中间结点在中序排列中的位置
        int index;
        for(index = inBegin; index <= inEnd; index++){
            if(inorder[index] == rootValue){
                break;
            }
        }
        int lenOfLeft = index - inBegin;
        root.left = findNode2(preorder, preBegin + 1, preBegin + lenOfLeft, inorder, inBegin, index - 1);
        root.right = findNode2(preorder, preBegin + 1 + lenOfLeft, preEnd, inorder, index + 1, inEnd);
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
