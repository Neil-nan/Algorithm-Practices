package leecode03;

import java.util.Arrays;
import java.util.Map;

public class DynamicProgramming {
    public static void main(String[] args) {
        lengthOfLIS4(new int[]{10,9,2,5,3,7,101,18});
    }

    /**
     * 题目：5 最长回文子串
     */
    //使用动态规划
    public static String longestPalindrome(String s){
        int len = s.length();
        String res = s.substring(0, 1);

        //初始化
        boolean[][] dp = new boolean[len][len];

        //j>= i
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i <= 2){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }else{
                    dp[i][j] = false;
                }
            }
        }

        for(int i = 0; i < len; i++){
            for(int j = i; j <len; j++){
                if(dp[i][j]){
                    String temp = s.substring(i, j + 1);
                    if(temp.length() > res.length()){
                        res = temp;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 题目：300 最长递增子序列
     */
    public static int lengthOfLIS(int[] nums){
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < dp.length; i++){
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //动态规划 + 二分查找
    public static int lengthOfLIS2(int[] nums){
        int[] tails = new int[nums.length];
        int res = 1;
        for(int num : nums) {
            int i = 0, j = res - 1;
            while(i <= j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m - 1;
            }
            tails[j + 1] = num;
            if(res - 1 == j) res++;
        }
        return res;
    }

    //打印路径
    //https://leetcode.cn/problems/longest-increasing-subsequence/solutions/1169964/xiao-bai-lang-jing-dian-dong-tai-gui-hua-px0v/
    public static int lengthOfLIS3(int[] nums){
        int n = nums.length;
        int[] tail = new int[n];
        //用于记录长度的dp数组
        int[] dp = new int[n];
        //初始化dp
        dp[0] = 1;
        int end = 0;
        for(int i = 1; i < n; i++){
            if(tail[end] < nums[i]){
                tail[++end] = nums[i];
                dp[i] = end + 1;
            }else {
                int left = 0;
                int right = end - 1;
                while(left <= right){
                    int mid = left + (right - left) / 2;
                    if(tail[mid] >= nums[i]){
                        right = mid - 1;
                    }else {
                        left = mid + 1;
                    }
                }
                //更新tail
                tail[right + 1] = nums[i];
                dp[i] = right + 2;
            }
        }
        int[] path = new int[end + 1];
        int j = end + 1;
        // 需要反向从后往前找，因为相同长度的dp[i]，后面的肯定比前面的字典序小（值小）
        // 反证：如果后面的比前面大，那么必定后面的长度>前面的长度
        for(int i = n - 1; i >= 0 && j >= 0; i--){
            if(dp[i] == j){
                path[--j] = nums[i];
            }
        }
        // 打印
        for (int i = 0; i < end + 1; i++) {
            System.out.print(path[i] + " ");
        }
        return end + 1;
    }

    public static int lengthOfLIS4(int[] nums){
        int len = nums.length;
        int[] tail = new int[len];
        tail[0] = nums[0];
        int res = 1;//表示结果 默认1
        for(int i = 1; i < len; i++){
            int left = 0;
            int right = res - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(tail[mid] < nums[i]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            tail[right + 1] = nums[i];//right + 1 是要写入的位置 如果要写入的位置比结果大，那么结果也要+1
            if(right + 1 == res){
                res++;
            }
        }
        return res;
    }
}
