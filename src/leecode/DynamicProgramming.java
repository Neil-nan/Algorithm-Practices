package leecode;

import java.util.*;

public class DynamicProgramming {

    /**
     * 题目：509 斐波那契数
     */
    public static int fib(int n){
        if(n < 2){
            return n;
        }
        //创建dp数组
        int[] dp = new int[n + 1];
        //初始化
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n + 1];
    }

    /**
     * 题目：70 爬楼梯
     */
    public static int climbStairs(int n){
        //创建dp数组 初始化
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < n + 1; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 题目：746 使用最小花费爬楼梯
     */
    public static int minCostClimbingStairs(int[] cost){
        //创建dp数组，初始化
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <= len; i++){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[len];
    }

    /**
     * 题目：62 不同路径
     */
    public static int uniquePaths(int m, int n){
        //创建dp数组 初始化
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 题目：63 不同路径II
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid){
        //创建dp数组 初始化
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        //石头后面都是0
        for(int i = 0; i < n; i++){
            if(obstacleGrid[0][i] == 0){
                dp[0][i] = 1;
            }else {
                break;
            }
        }
        for(int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 0){
                dp[i][0] = 1;
            }else {
                break;
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 题目：343 整数拆分
     */
    //这道题的争议点在 j * dp[i - j]上 为什么没有 dp[j] * (i - j) 和 dp[j] * dp[i - j]
    //在评论里给了一种数学方式的解答
    // 因为j * dp[i - j]包含了dp[j] * dp[i - j]，这是可以证明的： 对j最优拆分：j = a1 + a2 +...+an;
    // 对i - j 最优拆分：i - j = b1 + b2 +...+bn; 所以有 dp[j] = a1 * a2 ... an; dp[i - j] = b1 * b2 *... bn; dp[i] * dp[i - j] = (a1 *a2 *...an) * (b1 * b2 ... bn) = a1 * (a2 * ... * an * b1 * b2 ... bn)
    // 令 k = a1，必有i - k = a2 + ... + an + b1 + b2 +...+ bn(这就是对 i - k 的一种拆分) 也就是说如果以上这种对i - k的一种拆分是最优的，那么必有dp[j] * dp[i - j] = k * dp[i - k]
    // 所以此时j * dp[i - j]包含dp[j] * dp[i - j]； 如果以上这种对i - k的拆分不是最优的，那这种拆分方案虽不会被j * dp[i - j]包含但也不会是答案；
    //https://leetcode.cn/problems/integer-break/solutions/352875/zheng-shu-chai-fen-by-leetcode-solution/
    public static int integerBreak(int n){
        //dp[i]为正整数i拆分后的结果最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            for(int j = 1; j <= i / 2; j++){//遍历后半段没有意义
                //并且，在本题中，我们分析 dp[0], dp[1]都是无意义的，
                //j 最大到 i-j,就不会用到 dp[0]与dp[1]
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                //而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
            }
        }
        return dp[n];
    }

    /**
     * 题目：96 不同的二叉搜索树
     */
    public static int numTrees(int n){
        //初始化
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 题目：背包理论基础
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){
        //创建dp数组
        int goods = weight.length;
        int[][] dp = new int[goods][bagSize + 1];
        //初始化
        for(int i = 1; i <= bagSize; i++){
            dp[0][i] = value[0];
        }

        //进行计算, 先遍历物品 再遍历背包容量
        for(int i = 1; i < goods; i++){
            for(int j = 1; j <= bagSize; j++){
                if(j < weight[i]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

    }

    /**
     * 题目：背包问题的一维dp数组
     */
    public static void testWeightBagProblem2(int[] weight, int[] value, int bagWeight){
        int wLen = weight.length;
        //定义dp数组 dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[bagWeight + 1];
        //遍历顺序 先遍历物品 再遍历背包容量
        for(int i = 0; i < wLen; i++){
            for(int j = bagWeight; j >= weight[i]; j--){
                //这里进行从后向前的遍历 因为只有从后向前你递推公式中使用的数据才是上一层的，如果从前向后进行遍历那么使用的数据就将是本层处理后的
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

    }

    /**
     * 题目：416 分割等和子集
     */
    //使用01背包
    public static boolean canPartition(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }
        //创建dp数组
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        //判断奇偶性
        if((sum & 1) == 1){
            return false;
        }
        int len = sum / 2;
        int[] dp = new int[len + 1];
        //先遍历物品 再遍历背包
        for(int i = 0; i < nums.length; i++){
            for(int j = len; j >= nums[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[len] == len;
    }

    /**
     * 题目：698 划分为k个相等的子集
     */
    //回溯
    public static boolean canPartitionKSubsets(int[] nums, int k){
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % k != 0){
            return false;
        }
        int target = sum / k;
        //排序优化
        //进行降序排序，可以增加剪枝cnt[i] + nums[index] > target的命中率
//        Arrays.sort(nums, (a, b) -> b - a);
        /*注意，要想改变默认的排列顺序，不能使用基本类型（int,double, char）
          而要使用它们对应的类*/
        //方法一
        Integer newNums[] = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(newNums, (o1, o2) -> o2 - o1);
        //方法二
        //Arrays.sort(newNums, Collections.reverseOrder());
        int[] cnt = new int[k];
        return backtracking(newNums, 0, cnt, target, k);

    }

    public static boolean backtracking(Integer[] nums, int index, int[] cnt, int target, int k){
        //终止条件
        if(index == nums.length){
//            for (int i : cnt) {
////                if(i != target){
////                    return false;
////                }
////            }
            //这里解释一下不判断的原因， 因为每一次添加的条件是添加球后盒子里的和小于等于目标值，当所有的盒子都被填满后
            //所有的球的和等于目标总值，但是加起来却小于等于目标值 所以结果是只能等于
            return true;
        }

        // nums[index] 开始做选择
        //横向遍历盒子 纵向遍历球
        for(int i = 0; i < k; i++){
            //如果当前桶和上一个桶内的元素和相等 则跳过
            //如果元素和相等 那么nums[index]球已经经历过上一个桶的情况，和当前桶是一致的
            //注意 这里桶是不一样的桶，但是球还是一样的球
            if(i > 0 && cnt[i] == cnt[i - 1]){
                continue;
            }
            //剪枝
            if(cnt[i] + nums[index] > target){
                continue;
            }
            cnt[i] += nums[index];
            //回溯
            if(backtracking(nums, index + 1, cnt, target, k)){
                return true;
            }
            cnt[i] -= nums[index];
        }
        return false;
    }

    /**
     * 题目：473 火柴拼正方形
     */
    public static boolean makesquare(int[] matchsticks){
        //确定每个边的长度
        int sum = 0;
        for(int i = 0; i < matchsticks.length; i++){
            sum += matchsticks[i];
        }
        //除不开
        if(sum % 4 != 0){
            return false;
        }
        int len = sum / 4;
        int[] cnt = new int[4];
        Arrays.sort(matchsticks);//降序
        int left = 0;
        int right = matchsticks.length - 1;
        while(left <= right){
            int temp = matchsticks[left];
            matchsticks[left] = matchsticks[right];
            matchsticks[right] = temp;
            left++;
            right--;
        }
        return backtracking2(matchsticks, cnt, len, 0);

    }

    //回溯

    /**
     *
     * @param matchsticks  火柴长度
     * @param cnt 每个边边长的统计
     * @param len 边长的值
     * @param index 指向火柴
     * @return
     */
    public static boolean backtracking2(int[] matchsticks, int[] cnt, int len, int index){
        //终止条件
        if(index == matchsticks.length){
//            for(int i = 0; i < 4; i++){
//                if(cnt[i] != len){
//                    return false;
//                }
//            }
            return true;
        }

        //横向遍历每条边 纵向遍历火柴
        for(int i = 0; i < 4; i++){
            if(i > 0 && cnt[i] == cnt[i - 1]){
                continue;
            }
            if(cnt[i] + matchsticks[index] > len){//这个篮子已经放不下了，要换下一个
                continue;
            }
            cnt[i] += matchsticks[index];
            if(backtracking2(matchsticks, cnt, len, index + 1)){//回溯
                return true;
            }
            //回溯
            cnt[i] -= matchsticks[index];
        }
        return false;
    }

    /**
     * 题目：1049 最后一块石头的重量II
     */
    //平均分成两组
    public static int lastStoneWeightII(int[] stones){
        int sum = 0;
        for(int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;
        //创建dp数组
        int[] dp = new int[target + 1];
        //先遍历物品再遍历背包
        for(int i = 0; i < stones.length; i++){
            for(int j = target; j >= stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }

    /**
     * 题目：494 目标和
     */
    //结果一定可以分成两队 一堆是正数 一堆是负数 假设正数的总和为x 那么负数对应的总和就是sum-x
    //x - (sum - x) = target     x = (target + sum) / 2
    public static int findTargetSumWays(int[] nums, int target){
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        //如果target过大 sum将无法满足
        if(target < 0 && sum < -target){
            return 0;
        }
        if((sum + target) % 2 != 0){
            return 0;
        }
        //创建dp数组
        int len = (target + sum) / 2;
        int[] dp = new int[len + 1];
        dp[0] = 1;//初始化
        //先遍历物品 再遍历背包
        for(int i = 0; i < nums.length; i++){
            for(int j = len; j >= nums[i]; j--){
                //推导公式
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[len];
    }

    /**
     * 题目：474 一和零
     */
    public static int findMaxForm(String[] strs, int m, int n){
        //创建dp数组
        //dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int oneNum;
        int zeroNum;
        for(String str : strs){
            oneNum = 0;
            zeroNum = 0;
            for (char c : str.toCharArray()) {
                if(c == '0'){
                    zeroNum++;
                }else {
                    oneNum++;
                }
            }
            //倒序遍历
            for(int i = m; i >= zeroNum; i--){
                for(int j = n; j >= oneNum; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 题目：完全背包问题
     */
    public static void testCompletePackAnotherWay(int[] weight, int[] value, int bagWeight){
        int[] dp = new int[bagWeight + 1];
        //先遍历物品 再遍历背包 都是从前向后进行遍历
        for(int i = 0; i < weight.length; i++){
            for(int j = weight[i]; j <= bagWeight; j++){
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
    }

    /**
     * 题目：518 零钱兑换 II
     */
    public static int change(int amount, int[] coins){
        //创建dp数组
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 题目：377 组合总和IV
     */
    //如果求组合数就是外层for循环遍历物品，内层for遍历背包。
    //如果求排列数就是外层for遍历背包，内层for循环遍历物品。
    public static int combinationSum4(int[] nums, int target){
        //创建dp数组
        int[] dp = new int[target + 1];
        //初始化
        dp[0] = 1;
        //先遍历背包 再遍历物品
        for(int i = 0; i <= target; i++){
            for(int j = 0; j < nums.length; j++){
                if(i >= nums[j]){
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 题目：70 爬楼梯
     */
    public static int climbStairs2(int n){
        //创建dp数组并初始化
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //对题目进行修改
    public static int climbStairs(int n, int m){
        //创建dp数组
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //先遍历背包 再遍历物品
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m; j++){
                if(i >= j){
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }

    /**
     * 题目：322 零钱兑换
     */
    public static int coinChange(int[] coins, int amount){
        int max = Integer.MAX_VALUE;
        //创建dp数组
        int[] dp = new int[amount + 1];
        //初试化
        for(int i = 0; i < dp.length; i++){
            dp[i] = max;
        }
        dp[0] = 0;
        //不需要排序 先遍历物品 再遍历背包
        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    /**
     * 题目：279 完全平方数
     */
    public static int numSquares(int n){
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for(int i = 0; i < dp.length; i++){
            dp[i] = max;
        }
        dp[0] = 0;
        for(int i = 1; i * i <= n; i++){//遍历物品
            for(int j = i * i; j <= n; j++){//遍历背包
                if(dp[j - i * i] != max){
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n] == max ? -1 : dp[n];
    }

    /**
     * 题目：139 单词拆分
     */
    //本题是需要排列的
    public static boolean wordBreak(String s, List<String> wordDict){
        HashSet<String> set = new HashSet<>(wordDict);
        //创建dp数组
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //因为是排序 所以先遍历背包再遍历物品
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < i; j++){
                //判断逻辑：如果确定dp[j] 是true，且 [j, i) 这个区间的子串出现在字典里，
                // 那么dp[i]一定是true。（j < i ）
                String word = s.substring(j, i);
                if(set.contains(word) && dp[j] == true){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 题目：多重背包问题
     * @param weight  重量
     * @param value  价值
     * @param nums  数量
     * @param bagWeight
     */
    //改变物品数量为01 背包格式
    public static void testMultiPack(int[] weight, int[] value, int[] nums, int bagWeight){
        List<Integer> weightList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0){
                weightList.add(weight[i]);
                valueList.add(value[i]);
                numsList.add(nums[i]);
                nums[i]--;
            }
        }

        //创建dp数组
        int[] dp = new int[bagWeight + 1];
        //先遍历物品再遍历背包 并且是从后向前进行遍历
        for(int i = 0; i < weightList.size(); i++){
            for(int j = bagWeight; j >= weightList.get(i); j--){
                dp[j] = Math.max(dp[j], dp[j - weightList.get(i)] + valueList.get(i));
            }
        }
    }

    /**
     * 题目：198 打家劫舍
     */
    public static int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        //创建dp数组
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        dp[2] = Math.max(dp[1], nums[1]);
        for(int i = 3; i < dp.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);//偷还是不偷
        }
        return dp[nums.length];
    }

    /**
     * 题目：213 打家劫舍 II
     */
    public static int robII(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }

        //将环拆成两个部分进行考虑（含头不含尾，含尾不含头）
        int result1 = robRange(nums, 0, nums.length - 2);//含头不含尾情况
        int result2 = robRange(nums, 1, nums.length - 1);//含尾不含头情况

        return Math.max(result1, result2);
    }

    public static int robRange(int[] nums, int start, int end){
        if(end == start){
            return nums[start];
        }
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(dp[start], nums[start + 1]);
        for(int i = start + 2; i <= end; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    /**
     * 题目：337 打家劫舍III
     */
    //使用后序遍历
    public static int robIII(TreeNode root){
        int[] res = robAction(root);
        return Math.max(res[0], res[1]);
    }

    //0 不偷  左右子节点都可以偷 注意是可以偷 取最大
    //1 偷  左右都不能偷
    public static int[] robAction(TreeNode root){
        //终止条件
        if(root == null){
            return new int[2];//空节点 偷不偷都是0
        }
        int[] res = new int[2];
        //后序遍历 根据子节点来处理结果
        int[] left = robAction(root.left);
        int[] right = robAction(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    /**
     * 题目：121 买卖股票的最佳时机
     */
    //贪心
    //最左最小值，取最右最大值，那么得到的差值就是最大利润
    public static int maxProfit(int[] prices){
        int low = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < prices.length; i++){
            low = Math.min(low, prices[i]);
            result = Math.max(result, prices[i] - low);
        }
        return result;
    }

    //使用动态规划
    // dp[i][0]代表第i天持有股票的最大收益
    // dp[i][1]代表第i天不持有股票的最大收益
    public static int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        //创建dp数组
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);//上一次持有的股票没有卖出去  购买了今天的股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];

    }

    /**
     * 题目：122 买卖股票的最佳时机II
     */
    //贪心算法 只看上峰
    public static int maxProfitII(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int low = prices[0];
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(low < prices[i]){
                res += (prices[i] - low);
            }
                low = prices[i];
        }
        return res;
    }

    //使用dp动态规划
    public static int maxProfitII2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        //创建dp数组 并初始化
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * 题目：123 买卖股票的最佳时机III
     */
    public static int maxProfitIII(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        //初始化
        //dp[i][j]中，i表示第i天，j为0~4五个状态，dp[i][j]表示第i天状态j所剩最大现金
        //0 没有操作 1 第一次买入 2 第一次卖出 3 第二次买入 4 第二次卖出
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        //这里初试第二次买入状态是根据第一次卖出的状态给出的（不用过分纠结）
        dp[0][3]  = -prices[0];
        for(int i = 1; i < len; i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[len - 1][4];
    }

    /**
     * 题目：188 买卖股票的最佳时机 IV
     */
    //和上一题类似？
    public static int maxProfitIV(int k, int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        //dp数组的初始化
        int[][] dp = new int[len][2 * k + 1];
        for(int i = 1; i < 2 * k ; i += 2){
            dp[0][i] = -prices[0];
        }

        for(int i = 1; i < len; i++){
            for(int j = 0; j < 2 * k - 1; j += 2){
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[len - 1][2 * k];
    }

    /**
     * 题目：309 最佳买卖股票时机含冷冻期
     */
    //一共是4中状态 持有股票 今天卖出股票 保持卖出股票的状态  冷冻期
    public static int maxProfitV(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        //创建dp数组 初试化
        //0 持有股票状态 1 保持卖出股票的状态（度过一天冷冻期） 2 卖出股票 3 冷冻期
        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        for(int i = 1; i < len; i++){
            //三种状态 首先是持有前一天的股票  前一天是冷冻期今天买入 前一天是保持卖出股票状态 今天买入
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));
            //两种状态 前一天是冷冻期 前一天是卖出股票的状态
            dp[i][1] = Math.max(dp[i - 1][3], dp[i - 1][1]);
            //前一天一定是持有股票的状态
            dp[i][2] = dp[i - 1][0] + prices[i];
            //前一天卖出股票
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));
    }

    /**
     * 题目：714 买卖股票的最佳时机含手续费
     */
    //使用贪心算法 左侧的最小值和右侧的最大值 考虑手续费
    public static int maxProfit(int[] prices, int fee){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int low = prices[0];
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > low + fee){//买了不亏 但这一次可能不是最后一次 也就是这一次买入的最高的一次
                res += prices[i] - low - fee;
                low = prices[i] - fee;//这里要和prices[i] > low + fee进行比较
            }
            if(prices[i] < low){
                low = prices[i];
            }
            if(prices[i] > low && prices[i] <= low + fee){
                continue;
            }
        }
        return res;
    }

    //使用动态规划
    public static int maxProfit2(int[] prices, int fee){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        //创建dp数组 初始化
        //0 持有股票 1 卖出股票
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0] - fee;
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }

    /**
     * 题目：674 最长连续递增序列
     */
    //使用贪心
    public static int findLengthOfLCIS(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int res = 1;
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                count++;
            }else {//断了
                res = Math.max(res, count);
                count = 1;
            }
        }
        res = Math.max(res, count);//检查最后一个
        return res;
    }

    //使用动态规划
    public static int findLengthOfLCIS2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        //创建dp数组
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }
            if(res < dp[i]){
                res = dp[i];
            }
        }
        return res;
    }

    /**
     * 题目：300 最长递增子序列
     */
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //创建dp数组 并初始化
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] > result){
                result = dp[i];
            }
        }

        return result;

    }

    /**
     * 题目：718 最长重复子数组
     */
    public static int findLength(int[] nums1, int[] nums2){
        int result = 0;
        //创建dp数组
        //这里创建的时候 + 1 是为了简单 不需要初始化
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if(dp[i][j] > result){
                    result = dp[i][j];
                }
            }
        }
        return result;

    }

    //滚动数组
    public static int findLength2(int[] nums1, int[] nums2){
        int[] dp = new int[nums2.length + 1];
        int res = 0;

        for(int i = 1; i <= nums1.length; i++){
            //从后向前进行遍历
            for(int j = nums2.length; j > 0; j--){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[j] = dp[j - 1] + 1;
                }else {
                    dp[j] = 0;
                }
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }

    /**
     * 题目：1143 最长公共子序列
     */
    public static int longestCommonSubsequence(String text1, String text2){
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for(int i = 1; i <= text1.length(); i++){
            char t1 = text1.charAt(i - 1);
            for(int j = 1; j <= text2.length(); j++){
                char t2 = text2.charAt(j - 1);
                if(t1 == t2){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 题目：1035 不相交的线
     */
    //求最长公共子序列
    public static int maxUncrossedLines(int[] nums1, int[] nums2){
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        //前i - 1
        for(int i = 1; i <= nums1.length; i++){
            int t1 = nums1[i - 1];
            for(int j = 1; j <= nums2.length; j++){
                int t2 = nums2[j - 1];
                if(t1 == t2){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 题目：53 最大子序和
     */
    //使用贪心
    public static int maxSubArray(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        int res = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            if(res < count){
                res = count;
            }
            if(count <= 0){
                count = 0;
            }
        }
        return res;
    }

    //使用动态规划
    public static int maxSumArray2(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if(res < dp[i]){
                res = dp[i];
            }
        }
        return res;
    }

    /**
     * 题目：392 判断子序列
     */
    //使用双指针法
    public static boolean isSubsequence(String s, String t){
        if(t.length() < s.length()){
            return false;
        }
        int indexT = 0;
        int indexS = 0;
        while(indexS < s.length() && indexT < t.length()){
            if(s.charAt(indexS) == t.charAt(indexT)){
                indexS++;
            }
            indexT++;
        }
        return indexS == s.length();

    }

    //使用动态规划
    public static boolean isSubsequence2(String s, String t){
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        //初始化

        for(int i = 1; i <= sLen; i++){
            for(int j = 1; j <= tLen; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        if(dp[sLen][tLen] == sLen){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 题目：115 不同的子序列
     */
    public static int numDistinct(String s, String t){
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        //初始化
        for(int i = 0; i <= sLen; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= sLen; i++){
            char sChar = s.charAt(i - 1);
            for(int j = 1; j <= tLen; j++){
                char tChar = t.charAt(j - 1);
                if(sChar == tChar){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sLen][tLen];
    }

    /**
     * 题目：583 两个字符串的删除操作
     */
    //求最长子序列？模仿1143
    public static int minDistance(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++){
            char char1 = word1.charAt(i - 1);
            for(int j = 1; j <= len2; j++){
                char char2 = word2.charAt(j - 1);
                if(char1 == char2){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return len1 + len2 - dp[len1][len2] * 2;
    }

    //模仿115
    public static int minDistance2(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        //初始化
        //dp[i][j]：以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，
        //想要达到相等，所需要删除元素的最少次数
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            char char1 = word1.charAt(i - 1);
            for(int j = 1; j <= len2; j++){
                char char2 = word2.charAt(j - 1);
                if(char1 == char2){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 题目：72 编辑距离
     */
    public static int minDistance3(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        //初始化
        //dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++){
            char char1 = word1.charAt(i - 1);
            for(int j = 1; j <= len2; j++){
                char char2 = word2.charAt(j - 1);
                if(char1 == char2){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {//word1变成word2  增删改
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 题目：647 回文子串
     */
    //从下到上 从左向右进行遍历
    public static int countSubstrings(String s){
        int len = s.length();
        int result = 0;
        //初始化
        //dp[i][j]：s字符串下标i到下标j的字串是否是一个回文串，即s[i, j]
        boolean[][] dp = new boolean[len][len];

        //从左向右 从下到上进行遍历
        //情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
        //情况二：下标i 与 j相差为1，例如aa，也是回文子串
        //情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，
        // 我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
        for(int i = len - 1; i >= 0; i--){
            //注意看定义，所以只有右上角的一半是值得讨论的，及i<j
            for(int j = i; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }else {
                    dp[i][j] = false;
                }
            }
        }

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(dp[i][j]){
                    result++;
                }
            }
        }
        return result;
    }

    //双指针法
    public static int countSubstrings2(String s){
        int len = s.length();
        int res = 0;
        for(int i = 0; i < len; i++){
            res += extend(s, i, i, len);//以i为中心
            res += extend(s, i, i + 1, len);//以i和i+1为中心
        }
        return res;
    }

    public static int extend(String s, int i, int j, int len){
        int result = 0;
        while(i >= 0 && j < len && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
            result++;
        }
        return result;
    }

    /**
     * 题目：516 最长回文子序列
     */
    public static int longestPalindromeSubseq(String s){
        int len = s.length();
        int[][] dp = new int[len][len];
        //初始化 对角线是1
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;
        }

        //从下到上 从左向右进行遍历
        for(int i = len - 1; i >= 0; i--){
            char char1 = s.charAt(i);
            for(int j = i + 1; j < len; j++){
                char char2 = s.charAt(j);
                if(char1 == char2){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    //只能选一个
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 题目：7 最长回文子串
     */

}
