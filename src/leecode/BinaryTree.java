package leecode;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class BinaryTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(7);
        node.left = node1;
        node.right = node2;
        getMinimumDifference2(node);
    }

    /**
     * 题目：144 二叉树的前序遍历
     */
    //递归法
    public static List<Integer> preorderTraversal1(TreeNode root){
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    public static void preorder(TreeNode root, List<Integer> result){
        //终止条件
        if(root == null){
            return;
        }
        //单层递归逻辑
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    //迭代法
    public static List<Integer> preorderTraversal2(TreeNode root){
        //创建栈
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        //入栈
        stack.push(root);
        while(!stack.isEmpty()){//入栈顺序 中右左
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
    public static List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    public static void postorder(TreeNode root, List<Integer> res){
        //终止条件
        if(root == null){
            return;
        }
        //单层递归逻辑
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    // 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
    public static List<Integer> postorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        //入栈
        stack.push(root);
        while(!stack.isEmpty()){//入栈顺序
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        //反转
        Collections.reverse(res);
        return res;
    }

    /**
     * 题目：94 二叉树的中序遍历
     */
    public static List<Integer> inorderTraversal1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> res){
        //终止条件
        if(root == null){
            return;
        }
        //单层递归逻辑
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    // 中序遍历顺序: 左-中-右 入栈顺序： 左-右
    public static List<Integer> inorderTraversal2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
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

    //通用方法 写个中序遍历意思一下
    public static List<Integer> inorderTraversal3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if(root == null){
            return res;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node != null){
                //按照右中左的顺序入栈
                stack.pop();
                if(node.right != null){//右
                    stack.push(node.right);
                }
                stack.push(node);//中
                stack.push(null);// 中节点访问过，但是还没有处理，加入空节点做为标记。
                if(node.left != null){//左
                    stack.push(node.left);
                }
            }else {// 只有遇到空节点的时候，才将下一个节点放进结果集
                stack.pop();// 将空节点弹出
                node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 题目：102 二叉树层序遍历
     */
    //迭代法
    public static List<List<Integer>> levelOrder1(TreeNode root){
        Deque<TreeNode> que = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                list.add(temp.val);
                //先进先出
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

    //递归法(不会)
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
    //层序遍历 并反转数组
    public static List<List<Integer>> levelOrderBottom(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                list.add(temp.val);
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
            result.add(list);
        }
        //进行反转
        List<List<Integer>> res = new ArrayList<>();
        for(int i = result.size() - 1; i >= 0; i--){
            res.add(result.get(i));
        }
        return res;
    }

    /**
     * 题目：199 二叉树的右视图
     */
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            TreeNode temp = que.peek();
            result.add(temp.val);
            for(int i = 0; i < len; i++){
                TreeNode node = que.poll();
                if(node.right != null){
                    que.offer(node.right);
                }
                if(node.left != null){
                    que.offer(node.left);
                }
            }
        }
        return result;
    }

    /**
     * 题目：637 二叉树的层平均值
     */
    public static List<Double> averageOfLevels(TreeNode root){
        List<Double> result = new ArrayList<>();
        if(root == null){
            return result;
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
            result.add(sum / len);
        }
        return result;
    }

    /**
     * 题目：429 N叉树的层序遍历
     */
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < len; i++){
                Node temp = que.poll();
                list.add(temp.val);
                for (Node child : temp.children) {
                    if(child != null){
                        que.offer(child);
                    }
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 题目：515 在每个树行中找最大值
     */
    public static List<Integer> largestValues(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            int maxVal = Integer.MIN_VALUE;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                maxVal = maxVal < temp.val ? temp.val : maxVal;
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
            result.add(maxVal);
        }
        return result;
    }

    /**
     * 题目：116 填充每个节点的下一个右侧节点指针
     */
    public static Node connect(Node root){
        if(root == null){
            return root;
        }
        //创建队列
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                Node temp = que.poll();
                if(i == len - 1){
                    temp.next = null;
                }else {
                    temp.next = que.peek();
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return root;
    }

    /**
     * 题目：117 填充每个节点的下一个右侧节点指针II
     */
    public static Node connectII(Node root){
        if(root == null){
            return root;
        }
        //创建队列
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                Node temp = que.poll();
                if(i == len - 1){
                    temp.next = null;
                }else {
                    temp.next = que.peek();
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return root;
    }

    /**
     * 题目：104 二叉树的最大深度
     */
    //使用层序遍历
    public static int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        int ans = 0;
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
            ans++;
        }
        return ans;
    }

    /**
     * 题目：111 二叉树的最小深度
     */
    public static int minDepth(TreeNode root){
        int res = 0;
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.add(root);
        while(!que.isEmpty()){
            int len = que.size();
            res++;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
                if(temp.left == null && temp.right == null){
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 题目：226 翻转二叉树
     */
    //使用层序遍历 然后进行反转
    public static TreeNode invertTree1(TreeNode root){
        if(root == null){
            return root;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode cur = que.poll();
                if(cur.left != null){
                    que.offer(cur.left);
                }
                if(cur.right != null){
                    que.offer(cur.right);
                }
                //反转子节点
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
            }
        }
        return root;
    }

    //使用深度遍历 递归法
    public static TreeNode invertTree2(TreeNode root){
        //终止条件
        if(root == null){
            return null;
        }
        //前序遍历
        swapChildren(root);//中
        invertTree2(root.left);//左
        invertTree2(root.right);//右
        return root;
    }

    //反转子节点
    public static void swapChildren(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    //使用深度遍历 迭代法 前序遍历
    public static TreeNode invertTree3(TreeNode root){
        if(root == null){
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        //按照 中 左 右
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            swapChildren(node);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        return root;
    }


    /**
     * 题目：101 对称二叉树
     */
    //迭代
    public static boolean isSymmetric1(TreeNode root){
        if(root == null){
            return true;
        }
        return isEqual(root.left, root.right);

    }

    public static boolean isEqual(TreeNode left, TreeNode right){
        //判断节点情况
        if(left == null && right == null){//如果是空节点 解没有必要继续进行递归
            return true;
        }else if(left == null && right != null){
            return false;
        }else if(left != null && right == null){
            return false;
        }else if(left.val != right.val){
            return false;
        }
        //这里正常 进行下一层
        boolean compareOutside = isEqual(left.left, right.right);
        boolean compareInside = isEqual(left.right, right.left);
        return compareInside && compareOutside;
    }

    //递归法
    public static boolean isSymmetric2(TreeNode root){
        if(root == null){
            return true;
        }
        //使用队列
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root.left);
        que.offer(root.right);
        while(!que.isEmpty()){
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
            //入队列 先进先出
            que.offer(left.left);
            que.offer(right.right);
            que.offer(left.right);
            que.offer(right.left);
        }

        return true;
    }

    /**
     * 题目：100 相同的树
     */
    //递归
    public static boolean isSameTree1(TreeNode p, TreeNode q){
        //终止条件
        if(p == null && q == null){
            return true;
        }
        if(p == null && q != null){
            return false;
        }
        if(p != null && q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }

        boolean compareLeft = isSameTree1(p.left, q.left);
        boolean compareRight = isSameTree1(p.right, q.right);
        return compareLeft && compareRight;
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
            //两数相等 继续比较
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
    public static boolean isSubtree1(TreeNode root, TreeNode subRoot){
        //先遍历二叉树找到相同节点
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        //深度遍历 前序
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.val == subRoot.val){//进行下一步对比
                if(isSame(temp, subRoot)){
                    return true;
                }
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
        return false;
    }

    public static boolean isSame(TreeNode root, TreeNode subRoot){
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        que.offer(subRoot);
        while(!que.isEmpty()){
            TreeNode pNode = que.poll();
            TreeNode qNode = que.poll();
            if(pNode == null && qNode == null){
                continue;
            }
            if(pNode == null || qNode == null){
                return false;
            }
            if(pNode.val != qNode.val){
                return false;
            }
            //相等 进入下一步讨论
            que.offer(pNode.left);
            que.offer(qNode.left);
            que.offer(pNode.right);
            que.offer(qNode.right);
        }
        return true;
    }

    //使用递归法 抄的
    public static boolean isSubtree2(TreeNode root, TreeNode subRoot){
        //终止条件
        if(subRoot == null){
            return true;
        }
        if(root == null){
            return false;
        }
        return check(root, subRoot) || isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);// 是否是同一颗树 || 和左子树相同 || 和右子树相同
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
    //使用层序遍历
    public static int maxDepth2(TreeNode root){
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

    //使用深度遍历(从下到上 最下一层设为1)
    public static int maxDepth3(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth3(root.left), maxDepth3(root.right)) + 1;
    }

    static int res;
    public static int maxDepth4(TreeNode root){
        res = 0;
        depth(root, 0);
        return res;
    }

    //深度遍历 递归（从上到下 最上一层设为1）
    public static void depth(TreeNode root, int depth){
        //终止条件
        if(root == null){
            return;
        }
        depth++;
        res = Math.max(res, depth);
        depth(root.left, depth);
        depth(root.right, depth);

    }

    /**
     * 题目：559 n叉树的最大深度
     */
    //层序遍历
    public static int maxDepth5(Node root){
        if(root == null){
            return 0;
        }
        int res = 0;
        Deque<Node> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            res++;
            for(int i = 0; i < len; i++){
                Node temp = que.poll();
                for (Node child : temp.children) {
                    if(child.children != null){
                        que.offer(child);
                    }
                }
            }
        }
        return res;
    }

    //深度遍历 从上到下
    //int res;
    public static int maxDepth6(Node root){
        res = 0;
        depth2(root, 0);
        return res;
    }

    public static void depth2(Node node, int depth){
        //终止条件
        if(node == null){
            return;
        }
        //node节点不为空
        depth++;
        res = Math.max(res, depth);
        for (Node child : node.children) {
            depth2(child, depth);
        }
    }

    //深度遍历 从下到上
    public static int maxDepth7(Node root){
        //终止条件
        if(root == null){
            return 0;
        }
        int depth = 0;
        if(root.children != null){
            for (Node child : root.children) {
                depth = Math.max(depth, maxDepth7(child));
            }
        }
        return depth + 1;
    }


    /**
     * 题目：111 二叉树的最小深度
     */
    //使用层序遍历
    public static int minDepth2(TreeNode root){
        if(root == null){
            return 0;
        }
        int res = 0;
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            res++;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                //判断情况
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

    //使用深度遍历
    public static int minDepth3(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        //单层递归条件
        int leftDepth = minDepth3(root.left);
        int rightDepth = minDepth3(root.right);
        if(leftDepth == 0 && rightDepth != 0){
            return 1 + rightDepth;
        }
        if(leftDepth != 0 && rightDepth == 0){
            return 1 + leftDepth;
        }
        return 1 + Math.min(leftDepth, rightDepth);
    }

    /**
     * 题目：222 完全二叉树的节点个数
     */
    //层序遍历（没有使用完全二叉树的特性）
    public static int countNodes1(TreeNode root){
        if(root == null){
            return 0;
        }
        int res = 0;
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            res += len;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
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

    //通用递归解法
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
    //满二叉树的节点数为 2^depth - 1
    public static int countNodes3(TreeNode root){
        if(root == null){
            return 0;
        }
        //开始根据左深度和右深度是否相同来判断该子树是不是满二叉树
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        int leftDepth = 1;
        int rightDepth = 1;

        //求左子树的深度
        while(leftNode != null){
            leftNode = leftNode.left;
            leftDepth++;
        }

        //求右子树的深度
        while(rightNode != null){
            rightNode = rightNode.right;
            rightDepth++;
        }

        if(leftDepth == rightDepth){//满足满二叉树
            return (int)(Math.pow(2, leftDepth) - 1);
        }

        //进行递归
        return countNodes2(root);
    }

    /**
     * 题目：110 平衡二叉树
     */
    //有问题 有特殊情况
    public boolean isBalanced1(TreeNode root){
        //思路：使用层序遍历 求最大深度和最小深度
        if(root == null){
            return true;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        int maxDepth = 0;
        int minDepth = Integer.MAX_VALUE;
        int depth = 0;
        while(!que.isEmpty()){
            int len = que.size();
            depth++;
            maxDepth = Math.max(maxDepth, depth);
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.left == null && temp.right == null){
                    minDepth = Math.min(minDepth, depth);
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return maxDepth - minDepth <= 1;
    }

    //递归法
    public static boolean isBalanced2(TreeNode root){
        //思路：使用层序遍历 求左右子树的深度
        if(root == null){
            return true;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(Math.abs(depth(temp.left) - depth(temp.right)) > 1){
                    return false;
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return true;

    }

    public static int depth(TreeNode node){
        //终止条件
        if(node == null){
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    //使用递归法
    public static boolean isBalanced3(TreeNode root){
        return getHeight(root) != -1;
    }

    public static int getHeight(TreeNode node){
        //终止条件
        if(node == null){
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        //左子树中有不满足的情况
        if(leftHeight == -1){
            return -1;
        }
        //右子树中有不满足的情况
        if(rightHeight == -1){
            return -1;
        }

        //该节点的左右子树不满足
        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 题目：257 二叉树的所有路径
     */
    //回溯？
    static List<String> result;
    public static List<String> binaryTreePaths(TreeNode root){
        result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        getPath(root, path);
        return result;
    }

    public static void getPath(TreeNode node, List<Integer> path){
        path.add(node.val);
        //终止条件
        //找到末端节点
        if(node.left == null && node.right == null){
            StringBuffer sb = new StringBuffer();
            //添加结果
            for(int i = 0; i < path.size() - 1; i++){
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
        }

        //向左右寻找
        if(node.left != null){
            getPath(node.left, path);//这里已经处理了path，所以要退回到没有处理之前
            path.remove(path.size() - 1);
        }
        if(node.right != null){
            getPath(node.right, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 题目：404 左叶子之和
     */
    //核心：通过父节点判断是否是左叶子
    //递归法
    public static int sumOfLeftLeaves1(TreeNode root){
        //终止条件
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 0;
        }
        //判断是不是左节点
        int leftValue = sumOfLeftLeaves1(root.left);//左边子树中的左叶子
        int rightValue = sumOfLeftLeaves1(root.right);//右边子树的左叶子
        if(root.left != null && root.left.left == null && root.left.right == null){
            leftValue += root.left.val;
        }

        return leftValue + rightValue;
    }

    //迭代法
    public static int sumOfLeftLeaves2(TreeNode root){
        if(root == null){
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int sum = 0;
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left != null && temp.left.left == null && temp.left.right == null){
                sum += temp.left.val;
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
        return sum;
    }

    /**
     * 题目：513 找树左下角的值
     */
    //层序遍历
    public static int findBottomLeftValue1(TreeNode root){
        if(root == null){
            return 0;
        }
        int res = root.val;
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            res = que.peek().val;
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
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

    //使用递归法
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
    public static boolean hasPathSum(TreeNode root, int targetSum){
        if(root == null){
            return false;
        }
        return traversal3(root, targetSum - root.val);

    }

    public static boolean traversal3(TreeNode node, int targetSum){
        //终止条件
        //找到
        if(node.left == null && node.right == null && targetSum == 0){
            return true;
        }
        if(node.left == null && node.right == null){
            return false;
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

    /**
     * 题目：113 路径总和II
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        List<Integer> list = new ArrayList<>();
        traversal4(root, targetSum, list, res);
        return res;
    }

    //找所有路径 要全部遍历
    public static void traversal4(TreeNode node, int targetSum, List<Integer> path, List<List<Integer>> result){
        path.add(node.val);
        //遇到叶子节点
        if(node.left == null && node.right == null){
            if(targetSum == node.val){
                result.add(new ArrayList<>(path));
            }
            return;
        }
        if(node.left != null){
            traversal4(node.left, targetSum - node.val, path, result);
            path.remove(path.size() - 1);
        }
        if(node.right != null){
            traversal4(node.right, targetSum - node.val, path, result);
            path.remove(path.size() - 1);
        }
    }

    public void traversal5(TreeNode node, int targetSum, List<Integer> list, List<List<Integer>> result){
        //终止条件
        list.add(node.val);
        //找到
        if(node.left == null && node.right == null && targetSum == 0){
            //这里注意 因为list是一直改变回溯的 所以记录结果时要新建一个
            result.add(new ArrayList<>(list));
            return;
        }
        //到头了 但是没有找到
        if(node.left == null && node.right == null){
            return;
        }

        if(node.left != null){
            traversal4(node.left, targetSum - node.left.val, list, result);
            list.remove(list.size() - 1);
        }
        if(node.right != null){
            traversal4(node.right, targetSum - node.right.val, list, result);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：106 从中序与后序遍历序列构造二叉树
     */
    //思路
    //第一步：如果数组大小为零的话，说明是空节点了。
    //第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
    //第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
    //第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）
    //第五步：切割后序数组，切成后序左数组和后序右数组
    //第六步：递归处理左区间和右区间
    public static TreeNode buildTree(int[] inorder, int[] postorder){
        if(inorder.length == 0 || postorder.length == 0){
            return null;
        }
        return traversal5(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    //前闭后开
    public static TreeNode traversal5(int[] inorder, int inBegin, int intEnd, int[] postorder, int postBegin, int postEnd){
        if(postorder.length == 0){
            return null;
        }
        if(inBegin >= intEnd || postBegin >= postEnd){
            return null;
        }
        //取出中间节点
        int rootValue = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootValue);

        //叶子节点
        if(postorder.length == 1){
            return root;
        }
        //分割中序数组
        int delimiterIndex;
        for(delimiterIndex = 0; delimiterIndex < inorder.length; delimiterIndex++){
            if(inorder[delimiterIndex] == rootValue){
                break;
            }
        }
        int lenOfLeft = delimiterIndex - inBegin;
        root.left = traversal5(inorder, inBegin, inBegin + lenOfLeft,
                postorder, postBegin, postBegin + lenOfLeft);
        root.right = traversal5(inorder, delimiterIndex + 1, intEnd,
                postorder, postBegin + lenOfLeft, postEnd - 1);
        return root;
    }

    /**
     * 题目：105 从前序与中序遍历序列构造二叉树
     */
    public static TreeNode buildTree2(int[] preorder, int[] inorder){
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        return traversal6(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public static TreeNode traversal6(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd){
        //终止条件
        if(preorder.length == 0){
            return null;
        }
        if(preBegin >= preEnd || inBegin >= inEnd){
            return null;
        }
        //创建中间节点
        int rootValue = preorder[preBegin];
        TreeNode root = new TreeNode(rootValue);
        //判断是不是叶子节点
        if(preorder.length == 1){
            return root;
        }
        //不是叶子节点 继续向下进行递归
        int spaceIn;
        for(spaceIn = inBegin; spaceIn < inorder.length; spaceIn++){
            if(inorder[spaceIn] == rootValue){
                break;
            }
        }
        int lenOfLeft = spaceIn - inBegin;
        root.left = traversal6(preorder, preBegin + 1, preBegin + 1 + lenOfLeft,
                inorder, inBegin, spaceIn);
        root.right = traversal6(preorder, preBegin + 1 + lenOfLeft, preEnd,
                inorder, spaceIn + 1, inEnd);
        return root;
    }

    /**
     * 题目：654 最大二叉树
     */
    //递归
    public static TreeNode constructMaximumBinaryTree(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        return recursion(nums, 0, nums.length);
    }

    //前闭后开
    public static TreeNode recursion(int[] nums, int begin, int end){
        //终止条件
        if(begin >= end){
            return null;
        }
        //找最大值和其位置
        int maxIndex = begin;
        for(int i = begin; i < end; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        int rootValue = nums[maxIndex];
        TreeNode root = new TreeNode(rootValue);
        //叶子节点
        if(begin + 1 == end){
            return root;
        }
        root.left = recursion(nums, begin, maxIndex);
        root.right = recursion(nums, maxIndex + 1, end);
        return root;
    }

    /**
     * 题目：617 合并二叉树
     */
    //递归
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        //终止条件
        if(root1 == null && root2 == null){
            return null;
        }
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

    //使用层序遍历 迭代
    public static TreeNode mergeTree2(TreeNode root1, TreeNode root2){
        //空值判断
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        //使用队列
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root1);
        que.offer(root2);
        while(!que.isEmpty()){
            TreeNode node1 = que.poll();
            TreeNode node2 = que.poll();
            //此时两个节点一定不为空
            node1.val += node2.val;
            //如果两颗树左节点都不为空 加入队列
            if(node1.left != null && node2.left != null){
                que.offer(node1.left);
                que.offer(node2.left);
            }
            //注意： 这里不仅仅是赋值 还相当于把node2的左子树全部放到了node1下
            if(node1.left == null && node2.left != null){
                node1.left = node2.left;
            }
            if(node1.right != null && node2.right != null){
                que.offer(node1.right);
                que.offer(node2.right);
            }
            if(node1.right == null && node2.right != null){
                node1.right = node2.right;
            }
        }
        return root1;
    }

    /**
     * 题目：700 二叉搜索树中的搜索
     */
    //普通二叉树 层序遍历
    public static TreeNode searchBST(TreeNode root, int val){
        if(root == null){
            return null;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.val == val){
                    return temp;
                }
                if(temp.left != null){
                    que.offer(temp.left);
                }
                if(temp.right != null){
                    que.offer(temp.right);
                }
            }
        }
        return null;
    }

    //深度遍历 普通二叉树 前序
    public static TreeNode searchBST2(TreeNode root, int val){
        if(root == null){
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.val == val){
                return temp;
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
            if(temp.left != null){
                stack.push(temp.left);
            }
        }
        return null;
    }

    //递归法
    public static TreeNode searchBST3(TreeNode root, int val){
        //终止条件
        if(root == null || root.val == val){
            return root;
        }

        //单层递归逻辑
        if(root.val > val){
            return searchBST3(root.left, val);
        }
        if(root.val < val){
            return searchBST3(root.right, val);
        }
        return null;
    }

    //迭代法
    public static TreeNode searchBST4(TreeNode root, int val){
        while(root != null){
            if(root.val > val){
                root = root.left;
            }else if(root.val < val){
                root = root.right;
            }else {
                return root;
            }
        }
        return null;
    }

    /**
     * 题目：98 验证二叉搜索树
     */
    //迭代 中序遍历
    public static boolean isValidBST(TreeNode root){
        if(root == null){
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode temp = stack.pop();
            if(pre != null && temp.val <= pre.val){
                return false;
            }
            pre = temp;
            cur = temp.right;
        }
        return true;
    }

    //递归 中序遍历
    static TreeNode max;
    public static boolean isValidBST2(TreeNode root){
        //终止条件
        if(root == null){
            return true;
        }
        boolean left = isValidBST2(root.left);
        if(!left){
            return false;
        }
        //中
        if(max != null && root.val <= max.val){
            return false;
        }
        max = root;
        boolean right = isValidBST2(root.right);
        return right;
    }

    //递归法
    //使用左右边界来判断节点的大小
    public static boolean isValidBST3(TreeNode root){
        return validBTS(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }

    public static boolean validBTS(long lower, long upper, TreeNode root){
        if(root == null){
            return true;
        }
        //判断
        if(root.val <= lower || root.val >= upper){
            return false;
        }
        return validBTS(lower, root.val, root.left) && validBTS(root.val, upper, root.right);
    }


    /**
     * 题目：530 二叉搜索树的最小绝对差
     */
    //迭代 试一试
    public static int getMinimumDifference(TreeNode root){
        Deque<TreeNode> stack = new ArrayDeque<>();
        int res = Integer.MAX_VALUE;
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                TreeNode temp = stack.pop();
                if(pre != null){
                    res = res < temp.val - pre.val ? res : temp.val - pre.val;
                }
                pre = temp;
                cur = temp.right;
            }
        }
        return res;
    }

    //和迭代一样的思路 尝试使用递归
    static int res1;
    static TreeNode pre;
    public static int getMinimumDifference2(TreeNode root){
        if(root == null){
            return 0;
        }
        pre = null;
        res1 = Integer.MAX_VALUE;
        getMin(root);
        return res1;
    }

    public static void getMin(TreeNode root){
        //终止条件
        if(root == null){
            return;
        }
        //左
        getMin(root.left);
        //中
        if(pre != null){
            res1 = Math.min(res1, root.val - pre.val);
        }
        pre = root;
        //右
        getMin(root.right);
    }

    /**
     * 题目：501 二叉搜索树中的众数
     */
    //递归中序遍历
    static List<Integer> resList;
    static int maxCount;//最大频率
    static int count;//统计频率
    static TreeNode preNode;
    public static int[] findMode(TreeNode root){
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        preNode = null;
        findAllMode(root);
        int[] res = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++){
            res[i] = resList.get(i);
        }
        return res;
    }

    public static void findAllMode(TreeNode root){
        if(root == null){
            return;
        }
        //左
        findAllMode(root.left);
        //中 进行处理
        if(preNode == null || preNode.val != root.val){
            count = 1;
        }else{
            count++;
        }
        //更新结果以及maxCount
        if(count > maxCount){
            resList.clear();
            resList.add(root.val);
            maxCount = count;
        }else if(count == maxCount){
            resList.add(root.val);
        }
        preNode = root;
        //右
        findAllMode(root.right);
    }

    //使用迭代
    public static int[] findMode2(TreeNode root){
        TreeNode pre = null;
        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;//左
            }else {
                cur = stack.pop();
                //计数
                if(pre == null || cur.val != pre.val){
                    count = 1;
                }else {
                    count++;
                }
                //更新结果
                if(count > maxCount){
                    result.clear();
                    result.add(cur.val);
                    maxCount = count;
                }else if(count == maxCount){
                    result.add(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            res[i] = result.get(i);
        }
        return res;
    }

    /**
     * 题目：236 二叉树的最近公共祖先
     */
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
        //终止条件
        if(root == q || root == p || root == null){
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        //两个目标分别在节点的左右
        if(left != null && right != null){
            return root;
        }
        if(left == null && right != null){
            return right;
        }
        if(left != null && right == null){
            return left;
        }
        return null;
    }

    /**
     * 题目：235 二叉搜索树的最近公共祖先
     */
    //递归法
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        //终止条件
        if(root == null){
            return root;
        }
        if(root.val > p.val && root.val > q.val){
            //向左进行遍历
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            if(left != null){
                return left;
            }
        }
        if(root.val < p.val && root.val < q.val){
            //向右进行遍历
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(right != null){
                return right;
            }
        }
        return root;
    }

    //回顾 使用二叉树的方法
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
        //终止条件
        if(root == null){
            return null;
        }
        //找到对应的节点
        if(root == p || root == q){
            return root;
        }
        //判断左右节点
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        //通过左右递归的结果来判断没有找到对应节点的情况
        //左右子节点分别找到
        if(left != null && right != null){
            return root;
        }
        if(left == null && right != null){
            return right;
        }
        if(left != null && right == null){
            return left;
        }
        return null;
    }

    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q){
        //终止条件
        if(root == null){
            return null;
        }

        //比两个目标值都小，向右递归
        if(root.val < p.val && root.val < q.val){
            TreeNode right = lowestCommonAncestor3(root.right, p, q);
            if(right != null){
                return right;
            }
        }
        //比两个目标值都大，向左进行递归
        if(root.val > p.val && root.val > q.val){
            TreeNode left = lowestCommonAncestor3(root.left, p, q);
            if(left != null){
                return left;
            }
        }
        //第一次刚刚好的时候，满足最近公共祖先
        return root;
    }

    //使用二叉搜索树的性质 迭代法
    public static TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return root;
        }
        while(root != null){
            if(root.val > p.val && root.val > q.val){
                root = root.left;
            }else if(root.val < p.val && root.val < q.val){
                root = root.right;
            }else {
                break;
            }
        }
        return root;
    }

    /**
     * 题目：701 二叉搜索树中的插入操作
     */
    //迭代法
    public static TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode pre = root;//记录上一个节点，如果当前节点找到位置并为空
        while(cur != null){
            pre = cur;
            if(cur.val > val){
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }

        if(pre.val > val){
            pre.left = new TreeNode(val);
        }
        if(pre.val < val){
            pre.right = new TreeNode(val);
        }
        return root;
    }

    //递归法
    public static TreeNode insertIntoBST2(TreeNode root, int val){
        //终止条件
        if(root == null){//找到了
            return new TreeNode(val);
        }
        if(root.val > val){
            root.left = insertIntoBST2(root.left, val);
        }
        if(root.val < val){
            root.right = insertIntoBST2(root.right, val);
        }
        return root;
    }

    /**
     * 题目：450 删除二叉搜索树中的节点
     */
    //递归法
    //第一种情况：没有找到删除的节点
    //第二种情况：找到 左右子节点都为空 删除节点
    //第三种情况：左或右节点为空
    //第四种情况：左右子节点都有 将左子树的最右节点放在要删除的节点上(并不太行)
    //迭代法 实验失败
//    public static TreeNode deleteNode(TreeNode root, int key){
//        if(root == null){
//            return null;
//        }
//        TreeNode cur = root;
//        //TreeNode pre = root;
//        while(cur != null){
//            pre = cur;
//            if(cur.val > key){
//                cur = cur.left;
//            }else if(cur.val < key){
//                cur = cur.right;
//            }else {//找到
//                //情况二
//                if(cur.left == null && cur.right == null){
//                    cur = null;
//                }
//                //情况三
//                if(cur.left == null && cur.right != null){
//                    cur = cur.right;
//                }
//                if(cur.left != null && cur.right == null){
//                    cur = cur.left;
//                }
//                //情况四
//                TreeNode node = cur.left;
//                TreeNode pre = cur;
//                while(node.right != null){
//                    pre = node;
//                    node = node.right;
//                }
//                if(pre == cur){
//                    cur.val = node.val;
//                    cur.left = null;
//                }else {
//                    pre.right = null;
//                }
//
//
//                return root;
//            }
//        }
//        //没找到
//        return root;
//    }
    //使用递归法
    public static TreeNode deleteNode(TreeNode root, int key){
        //终止条件
        if(root == null){//没找到
            return root;
        }
        if(root.val > key){
            root.left = deleteNode(root.left, key);
        }
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        }
        //找到
        if(root.val == key){
            //左右子节点都为空 直接返回空 进行删除
            if(root.left == null && root.right == null){
                return null;
            }
            //左右子节点其中有一个不为空 返回其子节点
            if(root.left == null && root.right != null){
                return root.right;
            }
            if(root.left != null && root.right == null){
                return root.left;
            }
            //左右子节点都不为空，用右子节点代替删除节点，将左子树放到右子树的最左端
            if(root.left != null && root.right != null){
                TreeNode node = root.right;
                //找到右子树的最左端
                while(node.left != null){
                    node = node.left;
                }
                node.left = root.left;//将删除节点的左子树放到右子树的最左端
                root = root.right;
                return root;
            }
        }
        return root;
    }

    //使用二叉树的另一种删除方式
    //二叉树中序排序后比目标节点大一点的那个值就是其右子树最左面的节点，所以将其和目标节点进行交换就不会改变二叉树的顺序
    //代码中目标节点（要删除的节点）被操作了两次
    //第一次是和目标节点的右子树最左面节点交换
    //第二次直接被null覆盖了
    public static TreeNode deleteNode1(TreeNode root, int key){
        //终止条件
        if(root == null){//第一种情况
            return root;
        }
        if(root.val > key){
            root.left = deleteNode1(root.left, key);
        }
        if(root.val < key){
            root.right = deleteNode1(root.right, key);
        }
        //处理单层逻辑
        if(root.val == key){
            if(root.left == null && root.right == null){
                return null;
            }
            if(root.left == null && root.right != null){
                return root.right;
            }
            if(root.left != null && root.right == null){
                return root.left;
            }
            if(root.left != null && root.right != null){
                TreeNode node = root.right;
                while(node.left != null){
                    node = node.left;
                }
                root.val = node.val;//将删除节点的数值变成右子树的最左节点
                //删除目标节点（相当于删除右子树的最左节点）
                root.right = deleteNode1(root.right, node.val);
            }
        }
        return root;
    }

    /**
     * 题目：669 修剪二叉搜索树
     */
    //递归法
    public static TreeNode trimBST(TreeNode root, int low, int high){
        //终止条件
        if(root == null){
            return null;
        }
        if(root.val < low){
            return trimBST(root.right, low, high);
        }
        if(root.val > high){
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /**
     * 题目：108 将有序数组转换为二叉搜索树
     */
    public static TreeNode sortedArrayToBST(int[] nums){
        return sortedArray(nums, 0, nums.length - 1);
    }

    //递归 左闭右闭？
    public static TreeNode sortedArray(int[] nums, int left, int right){
        //终止条件
        if(left > right){
            return null;
        }
        //确定中间节点
        int mid = (right - left) / 2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArray(nums, left, mid - 1);
        node.right = sortedArray(nums, mid + 1, right);
        return node;
    }

    //迭代法
    public static TreeNode sortedArrayToBST1(int[] nums){
        //判断
        if(nums == null || nums.length == 0){
            return null;
        }
        //根节点初始化
        TreeNode root = new TreeNode(-1);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> leftQueue = new LinkedList<>();
        Queue<Integer> rightQueue = new LinkedList<>();

        //根节点入队列
        nodeQueue.offer(root);
        //0位最区间下标初试位置
        leftQueue.offer(0);
        //nums.size - 1 为右区间下标初试位置
        rightQueue.offer(nums.length - 1);

        while(!nodeQueue.isEmpty()){
            TreeNode curNode = nodeQueue.poll();
            int left = leftQueue.poll();
            int right = rightQueue.poll();
            int mid = left + (right - left) / 2;

            //将mid对应的元素给中间节点
            curNode.val = nums[mid];

            //处理左区间
            if(left <= mid - 1){
                curNode.left = new TreeNode(-1);
                nodeQueue.offer(curNode.left);
                leftQueue.offer(left);
                rightQueue.offer(mid - 1);
            }
            //处理右区间
            if(right >= mid + 1){
                curNode.right = new TreeNode(-1);
                nodeQueue.offer(curNode.right);
                leftQueue.offer(mid + 1);
                rightQueue.offer(right);
            }
        }
        return root;
    }

    /**
     * 题目：538 把二叉搜索树转换为累加树
     */
    //递归 按照右中左进行处理
    public static TreeNode convertBST(TreeNode root){
        preVal = 0;
        convertBST1(root);
        return root;
    }

    //对整个树进行修改，所以返回值为空
    static int preVal;
    public static void convertBST1(TreeNode root){
        //终止条件
        if(root == null){
            return;
        }
        convertBST1(root.right);
        root.val += preVal;
        preVal = root.val;
        convertBST1(root.left);
    }

}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

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

//class Node{
//    int val;
//    List<Node> children;
//
//    public Node() {
//    }
//
//    public Node(int val) {
//        this.val = val;
//    }
//
//    public Node(int val, List<Node> children) {
//        this.val = val;
//        this.children = children;
//    }
//}

/**
 * 完美二叉树
 */
class Node{
    int val;
    Node left;
    Node right;
    Node next;
    List<Node> children;

    public Node() {
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

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}