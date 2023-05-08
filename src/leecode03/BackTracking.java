package leecode03;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {

    /**
     * 题目：46 全排列
     */
    static List<List<Integer>> resList;
    public static List<List<Integer>> permute(int[] nums){
        resList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] used = new int[nums.length];
        backtracking1(nums, used, list);
        return resList;
    }

    public static void backtracking1(int[] nums, int[] used, List<Integer> list){
        //终止条件
        if(list.size() == nums.length){
            resList.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(used[i] == 0){
                list.add(nums[i]);
                used[i] = 1;
                backtracking1(nums, used, list);
                //回溯
                used[i] = 0;
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 题目：415 字符串相加
     */
    public static String addStrings(String num1, String num2){
        StringBuffer res = new StringBuffer();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            res.append(temp % 10);
            i--;
            j--;
        }
        if(carry == 1){
            res.append(1);
        }
        return res.reverse().toString();
    }
}
