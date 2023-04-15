package SwordOffer;


import java.math.BigInteger;
import java.util.*;

public class SwordOffer {

    public static void main(String[] args) {
        myPow2(2, 5);
        exchange2(new int[]{1, 2, 3, 4});
        verifyPostorder(new int[]{1, 2, 3, 4, 5});
        translateNum(25);
    }

    /**
     * 题目：剑指offer 03 数组中重复的数字
     */
    public static int findRepeatNumber1(int[] nums){
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1]){
                return nums[i];
            }
        }
        return -1;
    }

    public static int findRepeatNumber2(int[] nums){
        //set集合
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(set.contains(num)){
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    /**
     * 题目：剑指offer 04 二维数组中的查找
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        //先找到满足的数组
        int wide = matrix.length;
        int len = matrix[0].length;
        List<Integer> record = new ArrayList<>();
        for(int i = 0; i < wide; i++){
            if(matrix[i][0] <= target && matrix[i][len - 1] >= target){
                record.add(i);
            }
        }
        for (Integer integer : record) {
            if(findNumber(matrix[integer.intValue()], target)){
                return true;
            }
        }
        return false;
    }

    //双指针找数
    public static boolean findNumber(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目：剑指offer 05 替换空格
     */
    public static String replaceSpace(String s){
        if(s == null || s.length() == 0){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ' '){
                sb.append("%20");
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 题目：剑指offer 06 从尾到头打印链表
     */
    public static int[] reversePrint1(ListNode head){
        //从尾到头
        List<Integer> list = new ArrayList<>();
        ListNode index = head;
        while(index != null){
            list.add(index.val);
            index = index.next;
        }
        int len = list.size();
        int[] res = new int[len];
        for(int i = len - 1; i >= 0; i--){
            res[len - 1 - i] = list.get(i);
        }
        return res;
    }

    //不借助其他空间
    public static int[] reversePrint2(ListNode head){
        //先计算数组的长度
        ListNode index = head;
        int len = 0;
        while(index != null){
            len++;
            index = index.next;
        }
        int[] res = new int[len];
        index = head;
        while(index != null){
            res[len - 1] = index.val;
            len--;
            index = index.next;
        }
        return res;
    }


    /**
     * 题目：剑指offer 07 重建二叉数组
     */
    //使用递归法
    public static TreeNode buildTree(int[] preorder, int[] inorder){
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        return traversal(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    //前闭后开
    public static TreeNode traversal(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd){
        //终止条件
        if(preorder.length == 0){
            return null;
        }
        if(preBegin >= preEnd || inBegin >= inEnd){
            return null;
        }
        //求根节点
        int rootValue = preorder[preBegin];
        TreeNode root = new TreeNode(rootValue);
        //判断是不是叶子节点
        if(preorder.length == 1){
            return root;
        }
        //不是叶子节点 拆分数组 向下遍历
        int delimiterIndex;
        for(delimiterIndex = 0; delimiterIndex < inorder.length; delimiterIndex++){
            if(inorder[delimiterIndex] == rootValue){
                break;
            }
        }
        int lenOfLeft = delimiterIndex - inBegin;

        root.left = traversal(preorder, preBegin + 1, preBegin + 1 + lenOfLeft,
                inorder, inBegin, inBegin + lenOfLeft);
        root.right = traversal(preorder, preBegin + 1 + lenOfLeft, preEnd,
                inorder, inBegin + lenOfLeft + 1, inEnd);
        return root;
    }

    /**
     * 题目：剑指offer 10-1 斐波那契数列
     */
    //递归 超时
    public static int fib(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    //动态规划
    public static int fib2(int n){
        if(n < 2){
            return n;
        }
        int[] res = new int[n + 1];
        res[1] = 1;
        for(int i = 2; i < n + 1; i++){
            res[i] = (res[i - 1] + res[i - 2]) % 1000000007;
        }
        return res[n];
    }

    /**
     * 题目：剑指offer 10-II 青蛙跳台阶问题
     */
    //动态规划
    public static int numWays(int n){
        if(n < 2){
            return 1;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i < n + 1; i++){
            res[i] = (res[i - 1] + res[i - 2]) % 1000000007;
        }
        return res[n];
    }

    /**
     * 题目：剑指offer 11 旋转数组的最小数字
     */
    //双指针进行搜索
    //详细解释 https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solutions/102826/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
    public static int minArray(int[] numbers){
        if(numbers.length == 1){
            return numbers[0];
        }
        //创建双指针
        int left = 0;
        int right = numbers.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            //还在右排序数组
            if(numbers[mid] < numbers[right]){
                right = mid;//x在[left, mid]的区间里
            }else if(numbers[mid] > numbers[right]){//这时一定在做排序数组中
                left = mid + 1;//x在[mid + 1, right]的区间里
            }else {//这时候是相等的情况，无法判断x是在左排数组中还是在右排数组中
                right = right - 1;//缩小范围
            }
        }

        return numbers[right + 1];
    }

    /**
     * 题目：剑指offer 12 矩阵中的路径
     */
    public static boolean exist(char[][] board, String word){
        int high = board.length;
        int wide = board[0].length;
        boolean[][] visited = new boolean[high][wide];
        for(int i = 0; i < high; i++){
            for(int j = 0; j < wide; j++){
                boolean flag = check(board, visited, i, j, word, 0);
                if(flag){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int index){
        //终止条件
        if(board[i][j] != s.charAt(index)){
            return false;
        }else if(index == s.length() - 1){
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        //递归深度为寻找的字符串 横向for为在数组中寻找的方向
        for (int[] direction : directions) {
            if(i + direction[0] >= 0 && i + direction[0] < board.length && j + direction[1] >= 0 && j + direction[1] < board[0].length){//上下左右满足寻找的范围
                if(!visited[i + direction[0]][j + direction[1]]){//还没有找
                    boolean flag = check(board, visited, i + direction[0], j + direction[1], s, index + 1);
                    if(flag){
                        result = true;
                        break;
                    }
                }
            }
        }
        //回溯
        visited[i][j] = false;
        return result;
    }

    /**
     * 题目：剑指offer 14-I 剪绳子
     * 和343 是一道题
     */
    public static int cuttingRope(int n){
        //创建dp
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i < n + 1; i++){
            for(int j = 1; j <= i / 2; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));//这里不展开j的原因是i - j > j 所以dp[i -j]就已经包含了dp[j]
            }
        }
        return dp[n];
    }

    /**
     * 题目：剑指offer 14-II 剪绳子II
     */
    //不能取模
    public static int cuttingRope2(int n){
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        // dp[1] = BigInteger.valueOf(1);
        for(int i = 3; i < n + 1; i++){
            for(int j = 1; j < i; j++){
                dp[i] = dp[i].max(BigInteger.valueOf(j * (i - j))).max(dp[i - j].multiply(BigInteger.valueOf(j)));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }

    /**
     * 题目：剑指offer 15 二进制中1的个数
     */
    //位运算符相关的知识
    //具体的知识看 https://blog.csdn.net/yinying293/article/details/128481204
    //我们可以直接循环检查给定整数 nnn 的二进制位的每一位是否为 1
    public static int hammingWeight(int n){
        int res = 0;
        for(int i = 0; i < 32; i++){
            if((n & (1 << i)) != 0){
                res++;
            }
        }
        return res;
    }

    //观察这个运算：n & (n−1)n~\&~(n - 1)n & (n−1)，其运算结果恰为把 nnn 的二进制位中的最低位的 111 变为 000 之后的结果
    //如：6 & (6−1)=4,6=(110)2,4=(100)运算结果 4即为把 6的二进制位中的最低位的 1变为 0之后的结果。
    public static int hammingWeight2(int n){
        int res = 0;
        while(n != 0){
            n &= n - 1;
            res++;
        }
        return res;
    }

    /**
     * 题目：剑指offer 16 数值的整数次方
     */
    //没通过
    public static double myPow(double x, int n){
        int res = 1;
        //判断正负
        if(n == 0){
            return 0;
        }else if(n > 0){
            while(n > 0){
                res *= x;
            }
            return res;
        }else {
            n = -n;
            while(n > 0){
                res *= x;
            }
            res = 1 / res;
            return res;
        }
    }

    public static double myPow2(double x, int n){
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long nAbs){
        double ans = 1.0;
        double x_contribute = x;
        while(nAbs > 0){
            if(nAbs % 2 == 1){
                ans *= x_contribute;
            }
            nAbs /= 2;
            x_contribute *= x_contribute;
        }
        return ans;
    }

    /**
     * 题目：剑指offer 17 打印从1到最大的n位数
     */
    public static int[] printNumbers(int n){
        int end = (int)Math.pow(10, n);
        int[] res = new int[end - 1];
        for(int i = 0; i < end - 1; i++){
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * 题目：剑指offer 18 删除链表的节点
     */
    public static ListNode deleteNode(ListNode head, int val){
        //创建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //创建指针
        ListNode index = dummy;
        while(index.next != null){
            if(index.next.val == val){
                index.next = index.next.next;
                return dummy.next;
            }
            index = index.next;
        }
        return dummy.next;
    }

    /**
     * 题目：剑指offer 19 正则表达式匹配
     */
    //动态规划 思路看解析 不会
//    public static boolean isMatch(String s, String p){
//
//    }

    /**
     * 题目：剑指offer 20 表示数值的字符串
     */
    //有限状态自动机
    //真™难
    public static boolean isNumber(String s){
        //定义以下9中状态
        /**
         * 0 开始的空格
         * 1 幂符号前的正负号
         * 2 小数点前的数字
         * 3 小数点、小数点后的数字
         * 4 当小数点前为空格时，小数点、小数点后的数字
         * 5 幂符号
         * 6 幂符号后的正负号
         * 7 幂符号后的数字
         * 8 结尾的空格
         * 合法的结束状态有 2 3 7 8
         */
        //初始化
        //状态转移表 键值对 (key,value)(key, value)(key,value) 含义：若输入 key ，则可从状态 i转移至状态 value 。
        Map[] states = {
                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<>() {{ put('d', 3); }},                                        // 4.
                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<>() {{ put('d', 7); }},                                        // 6.
                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if(!states[p].containsKey(t)) return false;
            p = (int)states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;

    }

    /**
     * 题目：剑指offer 21 调整数组顺序使奇数位于偶数前面
     */
    //本人思路
    public static int[] exchange(int[] nums){
        int len = nums.length;
        //创建双指针
        int left = 0;
        int right = len - 1;
        while(left <= right){
            //判断左指针
            if(isOdd(nums[left])){//是奇数右指针不动，左指针后移
                left++;
            }else {//是偶数，和右指针进行交换，别管右指针是啥，交换后右边是好的，右指针前移
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right--;
            }
        }
        return nums;
    }

    //判断是奇数还是偶数
    public static boolean isOdd(int num){
        if(num % 2 == 0){
            return false;
        }else {
            return true;
        }
    }

    //同样是左右指针，左指针跳过奇数寻找偶数  右指针跳过偶数寻找奇数 找到后进行交换
    public static int[] exchange2(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int temp;
        while(left < right){
            while(left < right && (nums[left] & 1) == 1){//详细看剑指offer15
                left++;
            }
            while(left < right && (nums[right] & 1) == 0){
                right--;
            }
            //都不是 进行交换
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }

    /**
     * 题目：剑指offer 22 链表中倒数第K个节点
     */
    public static ListNode getKthFromEnd(ListNode head, int k){
        //快慢双指针
        ListNode fastIndex = head;
        ListNode slowIndex = head;
        //快指针先走
        for(int i = 1; i < k; i++){
            fastIndex = fastIndex.next;
        }
        //快慢一起走
        while(fastIndex.next != null){
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next;
        }
        return slowIndex;
    }

    public static ListNode getKthFromEnd2(ListNode head, int k){
        ListNode fastIndex = head;
        ListNode slowIndex = head;
        for(int i = 0; i < k; i++){
            fastIndex = fastIndex.next;
        }
        while(fastIndex != null){
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }
        return slowIndex;
    }

    /**
     * 题目：剑指offer 24 反转链表
     */
    public ListNode reverseList(ListNode head){
        //创建双指针  pre 指向之前的节点 cur 当前节点 temp 当前节点的下一个节点
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 题目：剑指offer 25 合并两个排序的链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode index = dummy;
        //创建两个指针
        ListNode index1 = l1;
        ListNode index2 = l2;
        while(index1 != null || index2 != null){
            while(index1 != null && index2 != null){
                if(index1.val <= index2.val){
                    index.next = index1;
                    index1 = index1.next;
                }else {
                    index.next = index2;
                    index2 = index2.next;
                }
                index = index.next;
            }
            if(index1 == null && index2 != null){
                while(index2 != null){
                    index.next = index2;
                    index2 = index2.next;
                    index = index.next;
                }
            }
            if(index1 != null && index2 == null){
                while(index1 != null){
                    index.next = index1;
                    index1 = index1.next;
                    index = index.next;
                }
            }

        }
        return dummy.next;
    }

    /**
     * 题目：剑指offer 26 树的子结构
     */
    //层序遍历
    //注意是子结构 不是子树
    public static boolean isSubStructure(TreeNode A, TreeNode B){
        if(A == null || B == null){
            return false;
        }
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(A);
        while(!que.isEmpty()){
            int len = que.size();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(temp.val == B.val){
                    //进行判断
                    if(compareTree(temp, B)){
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

    //递归遍历寻找
    public static boolean compareTree(TreeNode a, TreeNode b){
        if(a == null && b == null){
            return true;
        }
        if(a != null && b == null){
            return true;
        }
        if(a == null && b != null){
            return false;
        }
        if(a.val != b.val){
            return false;
        }
        //这个节点是可以的，对他们的子节点进行比较
        boolean left = compareTree(a.left, b.left);
        boolean right = compareTree(a.right, b.right);
        return left && right;

    }

    /**
     * 题目：剑指offer 27 二叉树的镜像
     */
    public static TreeNode mirrorTree(TreeNode root){
        if(root == null){
            return root;
        }

        //交换左右子节点
        swapChildren(root);
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    public static void swapChildren(TreeNode node){
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    /**
     * 题目：剑指offer 28 对称的二叉树
     */
    //后序遍历
    public static boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }
        return compare(root.left, root.right);

    }

    public static boolean compare(TreeNode node1, TreeNode node2){
        //终止条件
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }
        if(node1.val != node2.val){
            return false;
        }
        boolean outside = compare(node1.left, node2.right);
        boolean inside = compare(node1.right, node2.left);
        return outside && inside;
    }

    /**
     * 题目：剑指offer 29 顺时针打印矩阵
     */
    public static int[] spiralOrder(int[][] matrix){
        //判断
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int wide = matrix.length;
        int len = matrix[0].length;
        int top = 0;//上边界
        int bottom = wide - 1;
        int left = 0;
        int right = len - 1;
        int[] res = new int[len * wide];
        int index = 0;
        while(index < res.length){
            //上
            for(int i = left; i <= right && index < res.length; i++){
                res[index++] = matrix[top][i];
            }
            top++;
            //右
            for(int i = top; i <= bottom && index < res.length; i++){
                res[index++] = matrix[i][right];
            }
            right--;
            //下
            for(int i = right; i >= left && index < res.length; i--){
                res[index++] = matrix[bottom][i];
            }
            bottom--;
            //左
            for(int i = bottom; i >= top && index < res.length; i--){
                res[index++] = matrix[i][left];
            }
            left++;
        }
        return res;
    }

    /**
     * 题目：剑指offer 31 栈的压入、弹出序列
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped){
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);//num入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]){//循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 题目：剑指offer 32-I 从上到下打印二叉树
     */
    //层序遍历
    public static int[] levelOrder(TreeNode root){
        List<Integer> list = new LinkedList<>();
        if(root == null){
            return new int[0];
        }
        //创建队列
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
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
        }
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 题目：剑指offer 32-II 从上到下打印二叉树II
     */
    public static List<List<Integer>> levelOrderII(TreeNode root){
        //创建结果
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        //创建队列
        Deque<TreeNode> que = new LinkedList<>();
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
            res.add(list);
        }
        return res;
    }

    /**
     * 题目：剑指offer 32-III 从上到下打印二叉树III
     */
    //使用双端队列
    public static List<List<Integer>> levelOrderIII(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int len = que.size();
            //这里作为双端队列
            LinkedList<Integer> list = new LinkedList<>();
            for(int i = 0; i < len; i++){
                TreeNode temp = que.poll();
                if(res.size() % 2 == 0){
                    list.addLast(temp.val);// 偶数层 -> 队列头部
                }else {
                    list.addFirst(temp.val);// 奇数层 -> 队列尾部
                }
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

    /**
     * 题目：剑指offer 33 二叉搜索树的后序遍历序列
     */
    public static boolean verifyPostorder(int[] postorder){
//        if(postorder == null || postorder.length == 0){
//            return false;
//        }
//        if(postorder.length == 1){
//            return true;
//        }
        //找到父节点
        return verify(postorder, 0, postorder.length - 1);

    }

    //使用递归
    public static boolean verify(int[] postorder, int start, int end){
        //终止条件
        if(start >= end){
            return true;//只剩下一个节点
        }
//        if(start + 1 == end){
//            return postorder[start] < postorder[end];
//        }
        int index = start;
        int father = postorder[end];
        while(postorder[index] < father){
            index++;
        }
        int rightIndex = index;
        while(postorder[rightIndex] > father){
            rightIndex++;
        }
        boolean left = verify(postorder, start, index - 1);
        boolean right = verify(postorder, index, end - 1);

        return rightIndex == end && left && right;
    }

    /**
     * 题目：剑指offer 34 二叉树中和为某一值的路径
     */
    //回溯
    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> pathSum(TreeNode root, int target){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return result;
        }
        backtracking(root, target, list, root.val);
        return result;
    }

    public static void backtracking(TreeNode node, int target, List<Integer> list, int sum){
        //终止条件
        list.add(node.val);
        //遇到叶子节点
        if(node.right == null && node.left == null){
            if(sum == target){
                result.add(new ArrayList<>(list));
            }
            return;
        }

        if(node.left != null){
            backtracking(node.left, target, list, sum + node.left.val);//回溯
            list.remove(list.size() - 1);
        }
        if(node.right != null){
            backtracking(node.right, target, list, sum + node.right.val);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：剑指offer 35 复杂链表的复制
     */
    //看答案
    public static Node copyRandomList(Node head){
        if(head == null){
            return null;
        }
        Node p = head;
        //第一步 在每个原节点后面创建一个新节点
        //1->1'->2->2'->3->3'
        while(p != null){
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }

        p = head;
        //第二步 设置新节点的随机节点
        while(p != null){
            if(p.random != null){
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        Node dummy = new Node(-1);
        p = head;
        Node cur = dummy;
        //第三步 将两个链表分离
        while(p != null){
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummy.next;
    }

    /**
     * 题目：剑指offer 36 二叉搜索树与双向链表
     */
    //递归
    static Node pre;
    static Node head;
    public static Node treeToDoublyList(Node root){
        if(root == null){
            return null;
        }
        treeToList(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    //中序遍历
    public static void treeToList(Node node){
        //终止条件
        if(node == null){
            return;
        }
        treeToList(node.left);
        if(pre != null){
            pre.right = node;
        }else {
            head = node;
        }
        node.left = pre;
        pre = node;
        treeToList(node.right);
    }

    /**
     * 题目：剑指offer 38 字符串的排列
     */
    static List<String> res;
    public static String[] permutation(String s){
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int[] used = new int[len];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        backtracking1(chars, sb, used);
        int lenRes = res.size();
        String[] result = new String[lenRes];
        for(int i = 0; i < lenRes; i++){
            result[i] = res.get(i);
        }
        return result;
    }

    //纵向去重 横向也要去重
    public static void backtracking1(char[] chars, StringBuilder sb, int[] used){
        //终止条件
        if(sb.length() == chars.length){
            res.add(new String(sb));
        }

        for(int i = 0; i < chars.length; i++){
            if(used[i] == 1){//纵向递归的时候已经被使用过
                continue;
            }
            //横向去重
            if(i > 0 && chars[i] == chars[i - 1] && used[i - 1] == 0){
                continue;
            }
            sb.append(chars[i]);
            used[i] = 1;
            //回溯
            backtracking1(chars, sb, used);
            used[i] = 0;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 题目：剑指offer 39 数组中出现次数超过一半的数字
     */
    public static int majorityElement(int[] nums){
        //直接排序
        Arrays.sort(nums);
        int len = nums.length;
        int index = len / 2;
        return nums[index];

    }

    //使用投票算法
    public static int majorityElement2(int[] nums){
        //众数candidate  出现次数count
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if(count == 0){
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 题目：剑指offer 40 最小的k个数
     */
    public static int[] getLeastNumbers(int[] arr, int k){
        if(k == 0){
            return new int[]{};
        }
        int[] res = new int[k];
        Arrays.sort(arr);
        for(int i = 0; i < k; i++){
            res[i] = arr[i];
        }
        return res;
    }

    //使用快排
    public static int[] getLeastNumbers2(int[] arr, int k){
        quickSort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    public static void quickSort(int[] arr, int l, int r){
        //终止条件  子数组长度为1
        if(l >= r){
            return;
        }
        //哨兵划分操作 以arr[l]作为基准数
        int i = l;
        int j = r;
        while(i < j){
            while(i < j && arr[j] >= arr[l]){
                j--;
            }
            while(i < j && arr[i] <= arr[l]){
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, l);
        //递归左右子数组执行哨兵划分
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 题目：剑指offer 42 连续子数组的最大和
     */
    //贪心
    public static int maxSubArray(int[] nums){
        int res = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            if(count > res){
                res = count;
            }
            if(count <= 0){
                count = 0;
            }
        }
        return res;

    }

    //动态规划
    public static int maxSubArray2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        //创建dp数组 初始化
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if(dp[i] > res){
                res = dp[i];
            }
        }
        return res;
    }

    /**
     * 题目：剑指offer 43 1~n 整数中1出现的次数
     */
    //https://leetcode.cn/problems/number-of-digit-one/solutions/1748815/by-baoya_uncle-2hnj/
    // https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
    // 2304
    public static int countDigitOne(int n){
        int digit = 1;
        int res = 0;
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        while(high != 0 || cur != 0){
            if(cur == 0){
                res += high * digit;
            }else if(cur == 1){
                res += high * digit + low + 1;
            }else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    /**
     * 题目：剑指offer 44 数字序列中某一位的数字
     */
    //https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solutions/219252/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
    public int findNthDigit(int n){
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    /**
     * 题目：剑指offer 46 把数字翻译成字符串
     */
    //回溯
    static int resNum;
    public static int translateNum(int num){
        String numString = String.valueOf(num);
        backtracking(numString, 0);
        return resNum;
    }

    public static void backtracking(String numString, int index){
        //终止条件
        if(index == numString.length()){
            resNum++;
            return;
        }
        for(int i = index; i < numString.length(); i++){
            String cur = numString.substring(index, i + 1);//判断
            if(isTran(cur)){
                backtracking(numString, i + 1);
            }else {
                break;
            }
        }
    }

    public static boolean isTran(String cur){
        if(cur.length() >= 2 && cur.charAt(0) == '0'){
            return false;
        }
        if(cur.length() > 2){
            return false;
        }
        int curNum = Integer.valueOf(cur);
        if(curNum >= 0 && curNum <= 25){
            return true;
        }else {
            return false;
        }
    }

    public static int translateNum2(int num){
        String s = String.valueOf(num);
        int len = s.length();
        int[] dp = new int[len + 1];
        //初始化
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= len; i++){
            String temp = s.substring(i - 2, i);
            int tempNum = Integer.valueOf(temp);
            if(tempNum >= 10 && tempNum <= 25){
                dp[i] = dp[i - 1] + dp[i - 2];
            }else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[len];
    }

    /**
     * 题目：剑指offer 47 礼物的最大价值
     */
    //动态规划
    public static int maxValue(int[][] grid){
        int len = grid[0].length;
        int wide = grid.length;
        //创建dp数组 初始化
        int[][] dp = new int[wide + 1][len + 1];

        for(int i = 1; i <= wide; i++){
            for(int j = 1; j <= len; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[wide][len];
    }







}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class Node{
    int val;
    Node next;
    Node random;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 题目：剑指offer 09 用两个栈实现队列
 */
class CQueue{

    //一个输入栈 一个输出栈
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public CQueue(){
        stackIn = new Stack<>();//负责进栈
        stackOut = new Stack<>();//负责出栈
    }

    public void appendTail(int value){
        stackIn.push(value);
    }

    public int deleteHead(){
        //判断出栈是否为空
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        if(!stackOut.isEmpty()){
            return stackOut.pop();
        }
        return -1;
    }
}

/**
 * 题目：剑指offer 30 包含min函数的栈
 */
//没有引入辅助栈
class MinStack{
    //记录每个元素与 未压入 该元素时栈中最小元素的差值
    Deque<Long> stack;
    //当前 已压入 栈中元素的最小值
    private long min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        //压入第一个元素
        if(stack.isEmpty()){
            min = x;
            stack.push(0L);
            return;
        }
        //栈不为空时 每次压入计算与min的差值后压入结果
        stack.push((long)x - min);
        //更新min
        min = Math.min((long)x, min);
        //上面两个语句是不能颠倒的 一定是先压入 再更新 因为min一定是当前栈中的最小值
    }

    public void pop() {
        long pop = stack.pop();
        //当前元素小于0 说明弹出元素是当前张中的最小值 要更新最小值
        if(pop < 0){
            // 因为对于当前弹出的元素而言，计算压入栈中的值时，计算的是该元素与【未压入】该元素时
            // 栈中元素的最小值的差值，故弹出该元素后栈中的最小值就是未压入该元素时的最小值
            // 即当前元素的值（min）减去两者的差值
            long lastMin = min;
            min = lastMin - pop;
        }
        //若大于等于0 不会对min有影响
    }

    public int top() {
        long peek = stack.peek();
        //若当前栈顶小于等于0 说明最小值就是栈顶元素
        if(peek <= 0){
            return (int)min;
        }
        //否则就是min + peek
        return (int)(min + peek);
    }

    public int min() {
        return (int)min;
    }
}

/**
 * 题目：剑指offer 37 二叉树的序列化与反序列化
 */
class Codec{

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public String rserialize(TreeNode root, String str){
        //终止条件
        if(root == null){
            str += "None,";
        }else {
            //前序遍历
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);

        return root;
    }
}

/**
 * 题目：剑指offer 41 数据流中的中位数
 */
class MedianFinder {

    //两个优先队列 queMax 和 queMin
    // 分别记录大于中位数的数和小于等于中位数的数
    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    /** initialize your data structure here. */
    public MedianFinder() {
        queMin = new PriorityQueue<>((a, b) -> (b - a));//从大到小
        queMax = new PriorityQueue<>((a, b) -> (a - b));//从小到大
    }

    public void addNum(int num) {
        if(queMin.isEmpty() || num <= queMin.peek()){
            queMin.offer(num);
            if(queMax.size() + 1 < queMin.size()){
                queMax.offer(queMin.poll());
            }
        }else {
            queMax.offer(num);
            if(queMax.size() > queMin.size()){
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}
