package leecode;

import java.util.*;

public class BackTracking {
    public static void main(String[] args) {
        //List<String> list = restoreIpAddresses("25525511135");
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list.add("MUC");
        list.add("LHR");
        list1.add("JFK");
        list1.add("MUC");
        list2.add("SFO");
        list2.add("SJC");
        list3.add("LHR");
        list3.add("SFO");
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(list);
        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);
        findItinerary(tickets);
    }

    /**
     * 题目：77 组合
     */
    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combine(int n, int k){
        List<Integer> list = new ArrayList<>();
        backTracking1(n, k, 1, list);
        return result;
    }

    public static void backTracking1(int n, int k, int startIndex, List<Integer> list){
        //终止条件
        if(list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = startIndex; i <= n - (k - list.size()) + 1; i++){
            list.add(i);
            backTracking1(n, k, i + 1, list);
            //回溯
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：216 组合总和III
     */
    //相加之和为 n 的 k 个数的组合
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combinationSum3(int k, int n){
        List<Integer> list = new ArrayList<>();
        backTracking2(k, n, 1, 0, list);
        return result;
    }

    public static void backTracking2(int k, int n, int startIndex, int sum, List<Integer> list){
        //终止条件
        if(list.size() == k){
            if(sum == n){
                result.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i = startIndex; i <= 9 - (k - list.size()) + 1; i++){//从结果的个数上进行剪枝
            list.add(i);
            sum += i;
            if(sum > n){//从结果的大小上进行剪枝
                list.remove(list.size() - 1);
                sum -= i;
                return;//后面的数都是比现在的数大的了，现在的结果不符合，所以直接跳过这一层的for
            }
            backTracking2(k, n, i + 1, sum, list);
            list.remove(list.size() - 1);
            sum -= i;//回溯
        }
    }

    /**
     * 题目：17 电话号码和字母组合
     */
    static List<String> resString = new ArrayList<>();
    public static List<String> letterCombinations(String digits){
        //判断digits的合法性
        if(digits == null || digits.length() == 0){
            return resString;
        }
        for(int i = 0; i < digits.length(); i++){
            if(digits.charAt(i) == '1' || digits.charAt(i) == '*' || digits.charAt(i) == '#'){
                return resString;
            }
        }
        String[] numStrings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuffer sb = new StringBuffer();
        char[] digitsChar = digits.toCharArray();
        backTracking3(digitsChar, 0, sb, numStrings);
        return resString;
    }

    //纵向是电话按键的数字（递归） 横向是每个数字对应的字母的for循环
    //digitsChar 对应数字的char数组  startIndex 对应的第几个 sb 拼接结果 numStrings 字典
    public static void backTracking3(char[] digitsChar, int startIndex, StringBuffer sb, String[] numStrings){
        //终止条件
        if(startIndex == digitsChar.length){
            resString.add(new String(sb));
            return;
        }
        String numString = numStrings[digitsChar[startIndex] - '0'];
        for(int i = 0; i < numString.length(); i++){
            sb.append(numString.charAt(i));
            backTracking3(digitsChar, startIndex + 1, sb, numStrings);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 题目：39 组合总和
     */
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target){
        //排序
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        backTracking4(candidates, target, 0, 0, list);
        return result;
    }

    public static void backTracking4(int[] candidates, int target, int sum, int index, List<Integer> list){
        //终止条件
        if(sum == target){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            list.add(candidates[i]);
            sum += candidates[i];
            if(sum > target){
                list.remove(list.size() - 1);
                sum -= candidates[i];
                return;
            }
            backTracking4(candidates, target, sum, i, list);
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }
    }

    /**
     * 题目：40 组合总和II
     */
    //static Set<List<Integer>> resultInteger = new HashSet<>();
    public static List<List<Integer>> combinationSum2(int[] candidates, int target){
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        backTracking5(candidates, target, 0, 0, list);
        return result;
    }

    public static void backTracking5(int[] candidates, int target, int sum, int index, List<Integer> list){
        //终止条件
        if(target == sum){
            result.add(new ArrayList<>(list));
            return;
        }
        //这里横向是不可以重发的，但是纵向是可以重复的，也就是一种结果里是可以有相同的数字的，但是同一个结果是不能由两个不同位置的但是相同的数字组成，
        //所以要使用数组进行标记，或者对比
        for(int i = index; i < candidates.length; i++){
            if(i > index && candidates[i] == candidates[i - 1]){//已经遍历讨论过
                continue;
            }
            list.add(candidates[i]);
            sum += candidates[i];
            if(sum > target){//排序后超出就不需要考虑了
                list.remove(list.size() - 1);
                sum -= candidates[i];
                return;
            }
            backTracking5(candidates, target, sum, i + 1, list);
            sum -= candidates[i];
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：131 分割回文串
     */
    static List<List<String>> resList = new ArrayList<>();
    public static List<List<String>> partition(String s){
        List<String> list = new ArrayList<>();
        backTracking6(s, 0, list);
        return resList;
    }

    public static void backTracking6(String s, int left, List<String> list){
        //终止条件
        if(left >= s.length()){
            resList.add(new ArrayList<>(list));
            return;
        }
        for(int i = left; i < s.length(); i++){
            //如果是回文子串 则记录
            if(isPalindrome(s, left, i)){
                String str = s.substring(left, i + 1);
                list.add(str);
            }else {
                continue;
            }
            backTracking6(s, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    //判断回文子串的方法
    //前闭后闭区间
    public static boolean isPalindrome(String s, int left, int right){
        while(left <= right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 题目：93 复原IP地址
     */
    //static List<String> resString = new ArrayList<>();
    public static List<String> restoreIpAddresses(String s){
        if(s.length() > 12){
            return resString;
        }
        backTracking7(s, 0, 0);
        return resString;
    }

    public static void backTracking7(String s, int index, int pointNum){
        //终止条件
        if(pointNum == 3){
            //判断最后一组
            if(isValid(s, index, s.length() - 1)){
                resString.add(s);
            }
            return;
        }
        for(int i = index; i < s.length(); i++){
            if(isValid(s, index, i)){
                s = s.substring(0, i + 1) + '.' + s.substring(i + 1);
                pointNum++;
                backTracking7(s, i + 2, pointNum);
                pointNum--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }else {
                break;//return也行
            }
        }
    }

    //判断分割的字符串是否合法（左闭右闭）
    public static boolean isValid(String s, int left, int right){
        if(left > right){
            return false;
        }
        if(s.charAt(left) == '0' && left != right){
            return false;
        }
        int sum = 0;
        for(int i = left; i <= right; i++){
            if(s.charAt(i) > '9' || s.charAt(i) < '0'){
                return false;
            }
            sum = sum * 10 + (s.charAt(i) - '0');
            if(sum > 225){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：78 子集
     */
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums){
        List<Integer> list = new ArrayList<>();
        backTracking8(nums, 0, list);
        return result;
    }

    //收集所有节点的结果 和之前不同 之前是收集所有叶子节点的结果
    public static void backTracking8(int[] nums, int index, List<Integer> list){
        result.add(new ArrayList<>(list));
        //终止条件
        if(index >= nums.length){
            return;
        }
        for(int i = index; i < nums.length; i++){
            list.add(nums[i]);
            backTracking8(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：90 子集II
     */
    //之前遇到过 试试 树层去重
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> subsetsWithDup(int[] nums){
        //先排序
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        backTracking9(nums, 0, list);
        return result;
    }

    public static void backTracking9(int[] nums, int index, List<Integer> list){
        result.add(new ArrayList<>(list));
        //终止条件
        if(index >= nums.length){
            return;
        }
        for(int i = index; i < nums.length; i++){
            //跳过相同的数字避免被重复讨论
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }
            list.add(nums[i]);
            backTracking9(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：491 递增子序列
     */
    //这道题不能改变数组的排序 所以只能使用use数组
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> findSubsequences(int[] nums){
        List<Integer> list = new ArrayList<>();
        backTracking10(nums, 0, list);
        return result;
    }

    public static void backTracking10(int[] nums, int index, List<Integer> list){
        if(list.size() > 1){
            result.add(new ArrayList<>(list));
        }
        //终止条件
        if(index >= nums.length){
            return;
        }
        //树层去重
        int[] used = new int[201];
        for(int i = index; i < nums.length; i++){
            if((used[nums[i] + 100] == 1) || !list.isEmpty() && nums[i] < list.get(list.size() - 1)){
                continue;//两种情况不考虑 一个是同一层中已经使用过该元素 另一种是新节点比前面的小（不是递增）
            }
            list.add(nums[i]);
            used[nums[i] + 100] = 1;//used数组只适用于本层的去重，递归之后会重新定义 所以不需要回溯
            backTracking10(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：46 全排列
     */
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums){
        int len = nums.length;
        int[] used = new int[len];
        List<Integer> list = new ArrayList<>();
        backTracking11(nums, used, list);
        return result;
    }

    //纵向去重
    public static void backTracking11(int[] nums, int[] used, List<Integer> list){
        //终止条件
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i] == 1){
                continue;
            }
            used[i] = 1;
            list.add(nums[i]);
            backTracking11(nums, used, list);
            used[i] = 0;
            list.remove(list.size() - 1);
        }
    }

    /**
     * 题目：47 全排列II
     */
    //static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> permuteUnique(int[] nums){
        //先排序
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        backTracking12(nums, used, list);
        return result;
    }

    //横向和纵向都需要去重
    public static void backTracking12(int[] nums, int[] used, List<Integer> list){
        //终止条件
        if(list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            //纵向去重
            if(used[i] == 1){
                continue;
            }
            //横向去重
            //当前数字和前一个是一样的 并且前面一个数字在整个树枝上没有被使用过
            if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0){
                continue;
            }
            list.add(nums[i]);
            used[i] = 1;
            backTracking12(nums, used, list);
            used[i] = 0;
            list.remove(list.size() - 1);
        }
    }


    /**
     * 题目：332 重新安排行程
     */
    //Map<出发机场, Map<到达机场, 航班次数>> map
    static Map<String, Map<String, Integer>> map;
    //static List<String> resString = new ArrayList<>();
    public static List<String> findItinerary(List<List<String>> tickets){
        map = new HashMap<>();
        for (List<String> ticket : tickets) {
            if(map.containsKey(ticket.get(0))){
                Map<String, Integer> temp = map.get(ticket.get(0));
                temp.put(ticket.get(1), temp.getOrDefault(ticket.get(1), 0) + 1);
                map.put(ticket.get(0), temp);
            }else {
                Map<String, Integer> temp = new TreeMap<>();//升序map
                temp.put(ticket.get(1), 1);
                map.put(ticket.get(0), temp);
            }
        }
        resString.add("JFK");
        backTracking13(tickets.size());
        return new ArrayList<>(resString);

    }

    public static boolean backTracking13(int tickerNum){
        //终止条件
        if(resString.size() == tickerNum + 1){
            return true;
        }
        String last = resString.get(resString.size() - 1);
        if(map.containsKey(last)){//防止出现null
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    resString.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking13(tickerNum)){
                        return true;
                    }
                    resString.remove(resString.size() - 1);
                    target.setValue(count);
                }
            }

        }
        return false;
    }

    /**
     * 题目：51 N皇后
     */
    static List<List<String>> que = new ArrayList<>();
    public static List<List<String>> solveNQueens(int n){
        char[][] chessboard = new char[n][n];
        for (char[] chars : chessboard) {
            Arrays.fill(chars, '.');
        }
        backTracking14(n, 0, chessboard);
        return que;
    }

    //全部结果
    public static void backTracking14(int n, int row, char[][] chessboard){
        //终止条件
        if(row == n){
            que.add(arrayToList(chessboard));
            return;
        }
        for(int col = 0; col < n; col++){
            if(isValid(row, col, n, chessboard)){
                chessboard[row][col] = 'Q';
                backTracking14(n, row + 1, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    public static List arrayToList(char[][] chessboard){
        List<String> list = new ArrayList<>();
        for(char[] c : chessboard){
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    public static boolean isValid(int row, int col, int n, char[][] chessboard){
        //检查列
        for(int i = 0; i < row; i++){
            if(chessboard[i][col] == 'Q'){
                return false;
            }
        }

        //检查45度角
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if(chessboard[i][j] == 'Q'){
                return false;
            }
        }
        //检查135度角
        for(int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++){
            if(chessboard[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    /**
     * 题目：37 解数独
     */
    public static void solveSudoku(char[][] board){
        backTracking15(board);
    }

    public static boolean backTracking15(char[][] board){
        //一个for循环遍历棋盘的行，一个for循环遍历棋盘的列
        //这里棋盘中一个一个的进行遍历，如果有数就跳过，每次回溯都会给棋盘中多添加一个数
        //这样一定可以遍历完
        for(int i = 0; i < 9; i++){//遍历行
            for(int j = 0; j < 9; j++){//遍历列
                if(board[i][j] != '.'){//跳过原始数字
                    continue;
                }
                for(char k = '1'; k <= '9'; k++){//(i, j)这个位置放k是否合法
                    if(isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if(backTracking15(board)){
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }

        }
        return true;
    }

    //判断棋盘是否合法
    //1 同行是否重复
    //2 同列是否重复
    //3 9宫格里是否重复
    public static boolean isValidSudoku(int row, int col, char val, char[][] board){
        //同行是否重复
        for(int i = 0; i < 9; i++){
            if(board[row][i] == val){
                return false;
            }
            if(board[i][col] == val){
                return false;
            }
        }

        //判断9宫格内是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for(int i = startRow; i < startRow + 3; i++){
            for(int j = startCol; j < startCol + 3; j++){
                if(board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
