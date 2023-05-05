package leecode03;

public class DynamicProgramming {

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
}
