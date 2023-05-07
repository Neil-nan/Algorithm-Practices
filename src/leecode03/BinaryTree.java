package leecode03;

import sun.java2d.pipe.AAShapePipe;

import java.util.*;

public class BinaryTree {

    /**
     * 题目：102 二叉树的层序遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root){
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
        return res;
    }

    /**
     * 题目：103 二叉树的锯齿形层序遍历
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        //创建队列
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        int index = 1;
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
            if(index % 2 == 0){
                Collections.reverse(list);
            }
            res.add(new ArrayList<>(list));
            index++;
        }
        return res;
    }

    /**
     * 题目：236 二叉树的最近公共祖先
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        //终止条件
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if(leftNode == null && rightNode == null){//都没找到
            return null;
        }else if(leftNode != null && rightNode == null){//找到一个
            return leftNode;
        }else if(leftNode == null && rightNode != null){
            return rightNode;
        }else {
            return root;
        }

    }


}

