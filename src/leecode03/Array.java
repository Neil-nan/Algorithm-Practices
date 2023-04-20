package leecode03;


import java.util.*;

/**
 * @decription 按照codeTop的顺序刷的
 */
public class Array {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        findMedianSortedArrays(nums1, nums2);
        firstMissingPositive(new int[]{1, 2, 0});
    }
    /**
     * 题目：15 三数之和
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(nums[len - 1] < 0){
            return res;
        }
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){//去重
                return res;
            }
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            //创建左右指针
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum + nums[i] < 0){
                    left++;
                }else if(sum + nums[i] > 0){
                    right--;
                }else{//找到结果
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(new ArrayList<>(list));
                    //去重
                    while(left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;

    }

    /**
     * 题目：53 最大子数组和
     */
    //返回数组的解法 https://leetcode-cn.com/problems/maximum-subarray/solution/zheng-li-yi-xia-kan-de-dong-de-da-an-by-lizhiqiang/
    //贪心算法
    public static int maxSubArray(int[] nums){
        int res = Integer.MIN_VALUE;
        int count = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            count += nums[i];
            if(res < count){
                res = count;
            }
            if(count < 0){
                count = 0;
            }
        }
        return res;
    }

    //使用动态规划
    public static int maxSubArray2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int len = nums.length;

        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];

        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if(dp[i] > res){
                res = dp[i];
            }
        }

        return res;
    }

    /**
     * 题目：1 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        //创建hash表 key 数值 value 位置
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                res[1] = i;
                res[0] = map.get(target - nums[i]);
                return res;
            }else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    /**
     * 题目：33 搜索旋转排序数组
     */
    public static int search(int[] nums, int target){
        //二分查找 先找中间数
        if(nums == null || nums.length == 0){
            return -1;
        }
        int index = 0;
        int len = nums.length;
        while(index < len - 1 && nums[index] < nums[index + 1]){
            index++;
        }
        int res = -1;
        //二分查找
        if(target <= nums[len - 1]){
            res = binarySearch(nums, index + 1, len - 1, target);
            if(res != -1){
                return res;
            }
        }
        if(target <= nums[index]){
            res = binarySearch(nums, 0, index, target);
            if(res != -1){
                return res;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int left, int right, int target){
        while(left <= right){
            int mid = right - (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    //另外一种思路
    //将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
    //此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
    public static int search2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[left] <= nums[mid]){
                //left 到 mid 是顺序区间
                if(target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                // mid 到 right 是顺序区间
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 题目：121 买卖股票的最佳时机
     */
    //所有股票问题的想法  https://leetcode.cn/circle/article/qiAgHn/
    public static int maxProfit(int[] prices){
        //左边的最小值和右边的最大值
        int left = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < prices.length; i++){
            left = Math.min(left, prices[i]);
            result = Math.max(result, prices[i] - left);
        }
        return result;
    }

    /**
     * 题目：88 合并两个有序数组
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1 有两个指针 一个指向nums2存放的位置  一个指向自己 nums2有一个指针指向自己
        //特殊情况进行判断
        if(n == 0){
            return;
        }
        // if(m == 0){
        //     nums1 = nums2;
        //     return;
        // }
        int index1 = m - 1;
        int index = nums1.length - 1;
        int index2 = n - 1;
        while(index >= 0){
            while(index1 >= 0 && index2 >= 0){
                int num1 = nums1[index1];
                int num2 = nums2[index2];
                if(num1 <= num2){
                    nums1[index] = num2;
                    index2--;
                }else {
                    nums1[index] = num1;
                    index1--;
                }
                index--;
            }
            while(index1 >= 0){
                nums1[index] = nums1[index1];
                index1--;
                index--;
            }
            while(index2 >= 0){
                nums1[index] = nums2[index2];
                index2--;
                index--;
            }
        }
    }

    //从后向前 双指针
    //在此遍历过程中的任意一个时刻，nums1 数组中有 m−p1−1个元素被放入 nums1的后半部，
    // nums2数组中有 n−p2−1 个元素被放入 nums1 的后半部，
    // 而在指针 p1的后面，nums1 数组有 m+n−p1−1 个位置。
    // m + n - p1 - 1 >= m - p1 - 1 + n - p2 - 1
    public static void merge2(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while(p1 >= 0 || p2 >= 0){
            if(p1 == -1){
                cur = nums2[p2--];
            }else if(p2 == -1){
                cur = nums1[p1--];
            }else if(nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            }else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }

    /**
     * 题目：54 螺旋矩阵
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        //判断
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        //创建上下左右边界
        int wide = matrix.length;
        int len = matrix[0].length;
        int top = 0;
        int bottom = wide - 1;
        int left = 0;
        int right = len - 1;
        int index = 0;
        int num = wide * len;
        while(index < num){
            for(int i = left; i <= right && index < num; i++){
                res.add(matrix[top][i]);
                index++;
            }
            top++;
            for(int i = top; i <= bottom && index < num; i++){
                res.add(matrix[i][right]);
                index++;
            }
            right--;
            for(int i = right; i >= left && index < num; i--){
                res.add(matrix[bottom][i]);
                index++;
            }
            bottom--;
            for(int i = bottom; i >= top && index < num; i--){
                res.add(matrix[i][left]);
                index++;
            }
            left++;
        }
        return res;

    }

    /**
     * 题目：42 接雨水
     */
    //单调栈
    public static int trap(int[] height){
        //单调栈 栈底大 栈顶小
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int sum = 0;

        for(int i = 1; i < len; i++){
            if(height[i] < height[stack.peek()]){
                stack.push(i);
            }else if(height[i] == height[stack.peek()]){
                stack.pop();
                stack.push(i);
            }else{
                //弹出计算
                int heightAtIdx = height[i];
                while(!stack.isEmpty() && heightAtIdx > height[stack.peek()]){
                    int mid = stack.pop();
                    if(!stack.isEmpty()){
                        int left = stack.peek();
                        int h = Math.min(height[left], height[i]) - height[mid];
                        int w = i - left - 1;
                        int hold = h * w;
                        if(hold > 0){
                            sum += hold;
                        }
                    }
                }
                stack.push(i);
            }
        }
        return sum;

    }

    //使用双指针
    public static int trap2(int[] height){
        //记录此位置的最左边和左右边的最大值
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];

        maxLeft[0] = height[0];
        for(int i = 1; i < len; i++){
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        maxRight[len - 1] = height[len - 1];
        for(int i = len - 2; i >= 0; i--){
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }

        //求和
        int sum = 0;
        for(int i = 0; i < len; i++){
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            if(count > 0){
                sum += count;
            }
        }
        return sum;
    }

    /**
     * 题目：4 寻找两个正序数组的中位数
     */
    //创建新的数组
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        //创建新的数组
        if(nums1 == null || nums1.length == 0){
            return findMedian(nums2);
        }
        if(nums2 == null || nums2.length == 0){
            return findMedian(nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] nums = new int[len1 + len2];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index <= (len1 + len2) / 2 && index1 < len1 && index2 < len2){
            int num1 = nums1[index1];
            int num2 = nums2[index2];
            if(num1 <= num2){
                nums[index] = num1;
                index1++;
            }else{
                nums[index] = num2;
                index2++;
            }
            index++;
        }

        if(index >= (len1 + len2) / 2 + 1){
            return findMedian(nums);
        }

        while(index1 < len1 && index <= (len1 + len2) / 2){
            nums[index++] = nums1[index1++];
        }

        while(index2 < len2 && index <= (len1 + len2) / 2){
            nums[index++] = nums2[index2++];
        }

        return findMedian(nums);
    }

    public static double findMedian(int[] nums){
        int len = nums.length;
        if(len % 2 == 1){
            return nums[len / 2];
        }else{
            return ((double)nums[len / 2] + nums[(len - 1) / 2]) / 2;
        }
    }

    //正派解法
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        if(len % 2 == 1){
            int mid = len / 2;
            double median = getKthElement(nums1, nums2, mid + 1);
            return median;
        }else {
            int midIndex1 = len / 2 - 1;
            int midIndex2 = len / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k){
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        int kthElement = 0;

        while(true){
            //边界情况
            if(index1 == len1){
                return nums2[index2 + k - 1];
            }
            if(index2 == len2){
                return nums1[index1 + k - 1];
            }
            if(k == 1){
                return Math.min(nums1[index1], nums2[index2]);
            }

            //正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 题目：56 合并区间
     */
    public static int[][] merge(int[][] intervals){
        //先排列 按照头进行排列
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        //左右边界的初始值
        int left = intervals[0][0];
        int right = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            if(right >= intervals[i][0]){
                //更新右边界
                right = Math.max(intervals[i][1], right);
            }else{//写入答案
                res.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        res.add(new int[]{left, right});
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 题目：31 下一个排列
     */
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        if(nums.length == 1){
            return;
        }

        //找到 A[i] < A[j] 从后先前
        int i = nums.length - 2;
        int j = nums.length - 1;
        while(i >= 0){
            if(nums[i] < nums[j]){
                break;
            }
            i--;
            j--;
        }

        //如果是最后一个
        if(i == -1){
            //重新来过
            Arrays.sort(nums);
            return;
        }

        //从(j, end)中找到第一个令 A[i] < A[k] 的k值 这个区间中的数都是降序的
        int k;
        for(k = nums.length - 1; k >= j; k--){
            if(nums[i] < nums[k]){
                break;
            }
        }

        //得到k 交换i和k
        swap(nums, i, k);

        //j以后降序 改为升序
        reverse(nums, j, nums.length - 1);

    }

    public static void reverse(int[] nums, int l, int r){
        //双指针进行升序
        while(l < r){
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    public static void swap(int[] nums, int i, int k){
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }

    /**
     * 题目：41 缺失的第一个正数
     */
    //原地哈希
    public static int firstMissingPositive(int[] nums){
        int len = nums.length;
        //第一步 将数组中小于等于 0 的数字转化为 len + 1
        for(int i = 0; i < len; i++){
            if(nums[i] <= 0){
                nums[i] = len + 1;
            }
        }

        //第二步 将数组中符合在空间 [1, len] 中的数字所应该对应的位置用 - 进行标记
//        for(int i = 0; i < len; i++){
//            int num = Math.abs(nums[i]);
//            if(num <= len){
//                nums[num - 1] = - Math.abs(nums[num - 1]);
//            }
//        }

//        for(int i = 0; i < len; i++){
//            //int num = Math.abs(nums[i]);
//            if(nums[i] <= len){
//                nums[nums[i] - 1] = - Math.abs(nums[nums[i] - 1]);
//            }
//        }


        //第三步 进行遍历 第一个是正数的位置 就是
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }

        return len + 1;
    }

    //置换
    public static int firstMissingPositive2(int[] nums){
        //将数字放到属于自己的位置上
        int len = nums.length;
        for(int i = 0; i < len; i++){
            //要一直交换 位置上不能留 好人
            while(nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]){
                //交换
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for(int i = 0; i < len; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return len + 1;
    }

    /**
     * 题目：105 从前序与中序遍历序列构造二叉树
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
            return null;
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    //前闭后闭区间
    public static TreeNode build(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd){
        //终止条件
        if(preBegin > preEnd || inBegin > inEnd){
            return null;
        }
        //确定中间节点
        int rootValue = preorder[preBegin];
        TreeNode root = new TreeNode(rootValue);
        //判断是不是叶子节点
        if(preorder.length == 1){
            return root;
        }
        //找到中序遍历的位置
        int spaceIn;
        for(spaceIn = inBegin; spaceIn <= inEnd; spaceIn++){
            if(rootValue == inorder[spaceIn]){
                break;
            }
        }
        int lenOfLeft = spaceIn - inBegin;
        root.left = build(preorder, preBegin + 1, preBegin + 1 + lenOfLeft - 1, inorder, inBegin, inBegin + lenOfLeft);
        root.right = build(preorder, preBegin + 1 + lenOfLeft, preEnd, inorder, spaceIn + 1, inEnd);

        return root;
    }

    /**
     * 题目：78 子集
     */
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        backtracking(nums, 0, list);
        return res;
    }

    //全部
    public static void backtracking(int[] nums, int index, List<Integer> list){
        res.add(new ArrayList<>(list));
        //终止条件
        if(index >= nums.length){
            return;
        }

        for(int i = index; i < nums.length; i++){
            list.add(nums[i]);
            backtracking(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：64 最小路径和
     */
    //使用动态规划
    public static int minPathSum(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int wide = grid.length;
        int len = grid[0].length;

        //创建dp数组 初始化
        int[][] dp = new int[wide][len];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < len; i++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for(int i = 1; i < wide; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for(int i = 1; i < wide; i++){
            for(int j = 1; j < len; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[wide - 1][len - 1];
    }

    //进阶 打印出路径
    public static int minPathSum2(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int wide = grid.length;
        int len = grid[0].length;

        int[][] dp = new int[wide][len];
        int[] cnt = new int[wide * len];

        //这里从后向前进行便利 结果相同 但是方便输出路径
        for(int i = len - 1; i >= 0; i--){
            for(int j = wide - 1; j >= 0; j--){
                if(i == len - 1 && j == wide - 1){
                    dp[i][j] = grid[i][j];
                }else {
                    //处理两个边界问题
                    int bottom;
                    int right;//从右侧和从下边来的分别的大小
                    if(i + 1 < wide){
                        bottom = dp[i + 1][j] + grid[i][j];
                    }else {
                        bottom = Integer.MAX_VALUE;
                    }
                    if(j + 1 < len){
                        right = dp[i][j + 1] + grid[i][j];
                    }else {
                        right = Integer.MAX_VALUE;
                    }
                    dp[i][j] = Math.min(bottom, right);
                    if(bottom < right){
                        cnt[getIndex(i, j, len)] = getIndex(i + 1, j, len);
                    }else {
                        cnt[getIndex(i, j, len)] = getIndex(i, j + 1, len);//数组的位置代表着dp数组中位置 对应的数值则为前一个点的位置
                    }
                }
            }
        }

        int index = getIndex(0, 0, len);
        for(int i = 1; i <= wide + len; i++){
            if(i == wide + len){
                continue;
            }
            int x = parseIdx(index, len)[0];
            int y = parseIdx(index, len)[1];
            System.out.print("(" + x + "," + y + ") ");
            index = cnt[index];
        }
        System.out.println(" ");
        return dp[0][0];
    }

    public static int[] parseIdx(int index, int len){
        return new int[]{index / len, index % len};
    }

    public static int getIndex(int x, int y, int len){
        return x * len + y;
    }

    /**
     * 题目：48 旋转图像
     */
    //延水平线进行翻转 再延对角线进行翻转
    public static void rotate(int[][] matrix){
        int len = matrix.length;
        //水平翻转
        for(int i = 0; i < len / 2; i++){
            for(int j = 0; j < len; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - i - 1][j];
                matrix[len - i - 1][j] = temp;
            }
        }

        //对角线翻转
        for(int i = 0; i < len; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //原地旋转
    public static void rotate2(int[][] matrix){
        int len = matrix.length;
        for(int i = 0; i < len / 2; i++){
            for(int j = 0; j < (len + 1) / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }

    /**
     * 题目：39 组合总和
     */
    //回溯
    static List<List<Integer>> resInteger = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        //排序
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        backtracking(candidates, target, 0, 0, list);
        return resInteger;
    }

    //纵向可以使用 横向不能使用（不重复 不用去重）
    public static void backtracking(int[] candidates, int target, int sum, int index, List<Integer> list){
        //终止条件
        if(sum == target){
            resInteger.add(new ArrayList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++){
            sum += candidates[i];
            list.add(candidates[i]);
            if(sum > target){//没有继续的必要了
                sum -= candidates[i];
                list.remove(list.size() - 1);
                return;
            }
            backtracking(candidates, target, sum, i, list);
            //回溯
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }
    }

    /**
     * 题目：169 多数元素
     */
    public static int majorityElement(int[] nums){
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if(count == 0){
                candidate = num;
            }
            if(candidate != num){
                count--;
            }else {
                count++;
            }
        }
        return candidate;
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
