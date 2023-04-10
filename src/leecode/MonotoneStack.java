package leecode;

import java.util.*;
import java.util.LinkedList;

public class MonotoneStack {

    /**
     * 题目：739 每日温度
     */
    //使用暴力算法
    public static int[] dailyTemperatures(int[] temperatures){
        int len = temperatures.length;
        int[] res = new int[len];

        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                if(temperatures[j] > temperatures[i]){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    //使用单调栈
    public static int[] dailyTemperatures2(int[] temperatures){
        int len = temperatures.length;
        int[] res = new int[len];

        /**
         如果当前遍历的元素 大于栈顶元素，表示 栈顶元素的 右边的最大的元素就是 当前遍历的元素，
         所以弹出 栈顶元素，并记录
         如果栈不空的话，还要考虑新的栈顶与当前元素的大小关系
         否则的话，可以直接入栈。
         注意，单调栈里 加入的元素是 下标。
         */
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for(int i = 1; i < len; i++){
            if(temperatures[i] <= temperatures[stack.peek()]){
                stack.push(i);
            }else {
                while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }

    /**
     * 题目：496 下一个更大元素I
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2){
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1];
        Deque<Integer> stack = new LinkedList<>();
        Arrays.fill(res, -1);
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < len1; i++){
            hashMap.put(nums1[i], i);//key数值 value 对应下标
        }
        stack.push(0);
        for(int i = 1; i < len2; i++){
            if(nums2[i] <= nums2[stack.peek()]){
                stack.push(i);
            }else {
                while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i]){
                     if(hashMap.containsKey(nums2[stack.peek()])){
                         Integer index = hashMap.get(nums2[stack.peek()]);
                         res[index] = nums2[i];
                     }
                     stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }

    /**
     * 题目：503 下一个更大元素II
     */
    //拼接成两个数组
    public static int[] nextGreaterElement(int[] nums){
        //边界判断
        if(nums == null || nums.length <= 1){
            return new int[]{-1};
        }
        int len = nums.length;
        int[] res = new int[len];//存放结果
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < len * 2; i++){
            while(!stack.isEmpty() && nums[i % len] > nums[stack.peek()]){
                res[stack.peek()] = nums[i % len];
                stack.pop();
            }
            stack.push(i % len);
        }
        return res;
    }
}
