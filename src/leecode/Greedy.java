package leecode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Greedy {
    public static void main(String[] args) {
        int[] nums = {-6, -8, -7, 1};
        largestSumAfterKNegations(nums, 4);
        merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    }

    /**
     * 题目：455 分发饼干
     */
    //胃口 g 饼干尺寸 s
    //优先考虑胃口
    public static int findContentChildren(int[] g, int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int start = s.length - 1;
        int count = 0;
        for(int i = g.length - 1; i >= 0; i--){
            if(start >= 0 && s[start] >= g[i]){
                start--;
                count++;
            }
        }
        return count;
    }

    /**
     * 题目：376 摆动序列
     */
    //使用贪心算法
    public static int wiggleMaxLength1(int[] nums){
        if(nums.length <= 1){
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            if((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)){
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    //使用动态规划
    public static int wiggleMaxLength2(int[] nums){
        //设dp状态dp[i][0]，表示考虑前i个数，第i个数作为山峰的摆动子序列的最长长度
        //设dp状态dp[i][1]，表示考虑前i个数，第i个数作为山谷的摆动子序列的最长长度
        int[][] dp = new int[nums.length][2];
        //初始化
        dp[0][0] = 1;
        dp[0][1] = 1;

        for(int i = 1; i < nums.length; i++){
            //i自己可以成为波峰或者波谷
            dp[i][0] = dp[i][1] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] > nums[i]){
                    //i是波谷
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
                if(nums[j] < nums[i]){
                    //i是波峰
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                }
            }
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

    /**
     * 题目：53 最大子序和
     */
    //贪心算法
    public static int maxSubArray(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        int res = Integer.MIN_VALUE;//结果
        int sum = 0;//过程中的子序和
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            res = Math.max(res, sum);
            if(sum <= 0){
                sum = 0;//重置结果，前面的就没有了意义
            }
        }
        return res;
    }

    //使用动态规划
    public static int maxSubArray2(int[] nums){
        //表示第i个前最大子数组和
        int[] dp = new int[nums.length];
        //初始化
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        Arrays.sort(dp);
        return dp[nums.length - 1];
    }

    /**
     * 题目：122 买卖股票的最佳时机II
     */
    //贪心算法
    public static int maxProfit(int[] prices){
        int sum = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i - 1]){
                sum += (prices[i] - prices[i - 1]);
            }
        }
        return sum;
    }

    //使用动态规划
    public static int maxProfit2(int[] prices){
        //dp[i][0] 持有股票的最多现金
        //dp[i][1] 卖出股票后的最多现金
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i < prices.length; i++){
            //今日持有股票后的为 前一天没卖出 前一天卖出后今天买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            //卖了没有买 持有后抛出
            dp[i][1] = Math.max(dp[i- 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }

    /**
     * 题目：55 跳跃游戏
     */
    public static boolean canJump(int[] nums){
        if(nums.length == 1){
            return true;
        }
        int cover = 0;
        for(int i = 0; i <= cover; i++){
            cover = Math.max(cover, i + nums[i]);
            if(cover >= nums.length - 1){
                return true;
            }
        }
        return false;
    }

    /**
     * 题目：45 跳跃游戏II
     */
    public static int jump(int[] nums){
        int count = 0;
        int cover = 0;
        res = Integer.MAX_VALUE;
        backtracking(nums, cover, count, 0);
        return res;

    }

    //使用回溯尝试一下 （超出时间限制）
    static int res;
    public static void backtracking(int[] nums, int cover, int count, int index){
        //终止条件
        if(cover >= nums.length - 1){
            res = Math.min(res, count);
            return;
        }
        for(int i = index; i <= cover; i++){
            int coverNew = Math.max(i + nums[i], cover);
            count++;
            backtracking(nums, coverNew, count, i + 1);
            //回溯
            count--;
        }
    }

    //贪心算法
    public static int jump2(int[] nums){
        if(nums == null || nums.length == 0 || nums.length == 1){
            return 0;
        }
        //记录跳跃的次数
        int count = 0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大覆盖区域
        int maxDistance = 0;
        for(int i = 0; i < nums.length; i++){
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance, i + nums[i]);
            //说明当前一步，在跳一步就到达了末尾
            if(maxDistance >= nums.length - 1){
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，根性下一步可达到的最大区域
            if(i == curDistance){
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }

    /**
     * 题目：1005 K次取反后最大化的数组和
     */
    //数组排列
    public static int largestSumAfterKNegations(int[] nums, int k){
        if(nums.length == 1){
            return k % 2 == 0 ? nums[0] : -nums[0];
        }
        Arrays.sort(nums);
        int sum = 0;
        int index = 0;//找出离0最近的数
        int count = k;
        //先将负数修改成为正数
        for(int i = 0; i < nums.length; i++){
            if(count > 0 && nums[i] < 0){
                nums[i] = -nums[i];
                count--;
                index = i;
            }else {//出现了第一个正数 或者是没有K了
                break;
            }
        }
        if(count > 0){//有K
            //找到离0最近的数
            if(index < nums.length - 1 && nums[index] >= nums[index + 1]){
                index++;
            }
            while(count > 0){
                nums[index] = -nums[index];
                count--;
            }
        }

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return sum;
    }

    public static int largestSumAfterKNegations2(int[] nums, int k){
        if(nums.length == 1){
            return k % 2 == 0 ? nums[0] : -nums[0];
        }
        Arrays.sort(nums);
        int sum = 0;
        int index = 0;
        for(int i = 0; i < k; i++){//消耗K
            if(i < nums.length - 1 && nums[index] < 0){
                nums[index] = - nums[index];
                //如果正好在正负之间，且负数绝对值小，index指着不移动，把K消耗掉
                if(nums[index] >= Math.abs(nums[index + 1])){
                    index++;
                }
                continue;
            }
            //这里已经将所有的负数都处理完的情况
            nums[index] = -nums[index];
        }
        //求和
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 题目：134 加油站
     */
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int curSum = 0;
        int totalSum = 0;
        int index = 0;
        for(int i = 0; i < gas.length; i++){
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if(curSum < 0){//当前累加rest[i]和curSum一旦小于0
                index = (i + 1) % gas.length;
                curSum = 0;
            }
        }
        if(totalSum < 0){
            return -1;
        }
        return index;
    }

    /**
     * 题目：135 分发糖果
     */
    //一次是从左到右遍历，只比较右边孩子评分比左边大的情况
    //一次是从右到左遍历，只比较左边孩子评分比右边大的情况。(从后向前的原因是因为要使用后面的数据作为支撑)
    public static int candy(int[] ratings){
        int len = ratings.length;
        int[] candyVec = new int[len];
        Arrays.fill(candyVec, 1);
        //从左向右进行遍历
        for(int i = 1; i < len; i++){
            if(ratings[i] > ratings[i - 1]){
                candyVec[i] = candyVec[i - 1] + 1;
            }
        }
        //从右向左进行遍历
        for(int i = len - 2; i >= 0; i--){
            if(ratings[i] > ratings[i +1]){
                candyVec[i] = Math.max(candyVec[i + 1] + 1, candyVec[i]);
            }
        }

        int res = 0;
        for (int num : candyVec) {
            res += num;
        }
        return res;
    }

    /**
     * 题目：860 柠檬水找零
     */
    public static boolean lemonadeChange(int[] bills){
        //5元直接收取
        //十元找5元
        //20元 1 找3张15 2 找一张5元一张10元
        int[] packet = new int[2];//0 5元 1 10元 个数
        for(int i = 0; i < bills.length; i++){
            if(bills[i] == 5){
                packet[0]++;
            }
            if(bills[i] == 10){
                if(packet[0] > 0){//能找钱
                    packet[0]--;
                    packet[1]++;
                }else {
                    return false;
                }
            }
            if(bills[i] == 20){
                if(packet[1] > 0 && packet[0] > 0){
                    packet[1]--;
                    packet[0]--;
                }else if(packet[0] > 3){
                    packet[0] = packet[0] - 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 题目：406 根据身高重建队列
     */
    public static int[][] reconstructQueue(int[][] people){
        //先对数组进行排序
        //身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];//升序
            }
            return b[0] - a[0];//降序
        });

        List<int[]> que = new LinkedList<>();
        for (int[] person : people) {
            que.add(person[1], person);//位置 person
        }
        return que.toArray(new int[people.length][]);
    }

    /**
     * 题目：452 用最少数量的箭引爆气球
     */
    //自己想的思路不太对
    //妈的 终于改对了
    public static int findMinArrowShots(int[][] points){
        //排序升序 气球的头和气球的尾进行比较
//        Arrays.sort(points, (a, b) -> {
//            if(a[0] == b[0]){
//                return a[1] - b[1];
//            }
//            return a[0] - b[0];
//        });//不能使用comparator比较器，会导致
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int count = 1;
        int index = 0;
        for(int i = 1; i < points.length; i++){
            if(points[index][1] >= points[i][0]){
                points[index][1] = Math.min(points[index][1], points[i][1]);
                continue;
            }else {//一支箭处理不了
                index = i;
                count++;
            }
        }
        return count;
    }

    /**
     * 题目：435 无重叠区间
     */
    //自己想的
    public static int eraseOverlapIntervals(int[][] intervals){
        //和气球类似
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int res = 0;
        int count = 1;//记录一组重叠的个数
        int index = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[index][1] > intervals[i][0]){//有重叠
                intervals[index][1] = Math.min(intervals[index][1], intervals[i][1]);
                count++;
                continue;
            }else {
                index = i;
                res += (count - 1);
                count = 1;//重置
            }
        }
        if(count != 1){
            res += (count - 1);
        }
        return res;
    }

    public static int eraseOverlapIntervals2(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i - 1][1]){
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                count++;
            }
        }
        return count;
    }

    /**
     * 题目：763 划分字母区间
     */
    //忘记了 不会
    public static List<Integer> partitionLabels(String s){
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++){//统计每个字符最后出现的位置
            edge[chars[i] - 'a'] = i;
        }
        int right = 0;//分割字符的左右边界
        int left = -1;
        for(int i = 0; i < chars.length; i++){
            right = Math.max(right, edge[chars[i] - 'a']);
            if(i == right){
                //添加结果
                list.add(right - left);
                left = right;
            }
        }
        return list;
    }

    /**
     * 题目：56 合并区间
     */
    public static int[][] merge(int[][] intervals){
        //先排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new LinkedList<>();
        int index = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[index][1] >= intervals[i][0]){
                intervals[index][1] = Math.max(intervals[index][1], intervals[i][1]);
            }else {
                list.add(intervals[index]);
                index = i;
            }
        }
        list.add(intervals[index]);
        int[][] res = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;

    }

    public static int[][] merge2(int[][] intervals){
        List<int[]> res = new LinkedList<>();
        //按照左边界进行排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        //做左右边界的初始值
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            //如果左边界小于等于右边界
            if(intervals[i][0] <= right){
                //更新最大右边界
                right = Math.max(right, intervals[i][1]);
            }else {//写入答案
                res.add(new int[]{left, right});
                //更新left right
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        res.add(new int[]{left, right});
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 题目：738 单调递增的数字
     */
    //从前向后进行遍历，遇到num[i - 1] > num[i] 就让num[i - 1]减1，然后num[i]给9
    public static int monotoneIncreasingDigits(int n){
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for(int i = chars.length - 2; i >= 0; i--){
            if(chars[i] > chars[i + 1]){
                chars[i]--;
                start = i + 1;
            }
        }
        for(int i = start; i< s.length(); i++){
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 题目：968 监控二叉树
     */
    //使用递归
    static int result = 0;
    public static int minCameraCover(TreeNode root){
        //对根节点的状态做检测，防止根节点是无覆盖状态
        if(minCamera(root) == 0){
            result++;
        }
        return result;
    }

    /**
     节点的状态值：
     0 表示无覆盖
     1 表示 有摄像头
     2 表示有覆盖
     后序遍历，根据左右节点的情况,来判读 自己的状态
     */
    public static int minCamera(TreeNode root){
        //终止条件
        if(root == null){
            return 2;//空节点默认为有覆盖状态，避免在叶子节点上放摄像头
        }
        int left = minCamera(root.left);
        int right = minCamera(root.right);

        //如果左右节点都覆盖了的话，那么本节点的状态就应该是无覆盖，没有摄像头
        if(left == 2 && right == 2){
            return 0;
        }else if(left == 0 || right == 0){
            //左右节点都是无覆盖状态，那根节点此时应该放一个摄像头
            // (0,0) (0,1) (0,2) (1,0) (2,0)
            result++;
            return 1;
        }else {
            // 左右节点的 状态为 (1,1) (1,2) (2,1) 也就是左右节点至少存在 1个摄像头，
            // 那么本节点就是处于被覆盖状态
            return 2;
        }
    }




}
