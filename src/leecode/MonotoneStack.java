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

    /**
     * 题目：42 接雨水
     */
    //双指针解法
    public static int trap1(int[] height){
        int sum = 0;
        int len = height.length;
        for(int i = 0; i < len; i++){
            //第一个柱子和最后一个柱子不接水
            if(i == 0 || i == len - 1){
                continue;
            }
            int rHeight = height[i];//记录右边柱子的最高高度
            int lHeight = height[i];//记录左边柱子的最高高度
            for(int r = i + 1; r < len; r++){
                if(height[r] > rHeight){
                    rHeight = height[r];
                }
            }
            for(int l = i - 1; l >= 0; l--){
                if(height[l] > lHeight){
                    lHeight = height[l];
                }
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if(h > 0){
                sum += h;
            }
        }
        return sum;
    }

    //单调栈解法
    public static int trap2(int[] height){
        int len = height.length;
        if(len <= 2){
            return 0;
        }

        //栈中存放柱子的位置
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);

        int sum = 0;
        for(int index = 1; index < len; index++){
            int stackTop = stack.peek();
            if(height[index] < height[stackTop]){
                stack.push(index);
            }else if(height[index] == height[stackTop]){
                // 因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的index, push当前的index
                stack.pop();
                stack.push(index);
            }else {
                //弹出所有低的柱子
                int heightAtIdx = height[index];
                while (!stack.isEmpty() && (heightAtIdx > height[stackTop])){
                    int mid = stack.pop();
                    if(!stack.isEmpty()){
                        int left = stack.peek();

                        int h = Math.min(height[left], height[index]) - height[mid];
                        int w = index - left - 1;
                        int hold = h * w;
                        if(hold > 0){
                            sum += hold;
                        }
                        stackTop = stack.peek();
                    }
                }
                stack.push(index);
            }
        }
        return sum;
    }

    public static int trap(int[] height){
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        int[] maxLeft = new int[len];//每个柱子最左边最高的柱子的位置
        int[] maxRight = new int[len];//每个柱子最右边最高柱子的位置

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
     * 题目：84 柱状图中的最大的矩形
     */
    //求左右第一个最小
    public static int largestRectangleArea(int[] heights){
        int len = heights.length;
        int[] minLeft = new int[len];
        int[] minRight = new int[len];

        //记录左边第一个小于该柱子的下标
        minLeft[0] = -1;
        for(int i = 1; i < len; i++){
            int t = i - 1;
            // 这里不是用if，而是不断向右寻找的过程
            while (t >= 0 && heights[t] >= heights[i]) t = minLeft[t];
            minLeft[i] = t;
        }

        minRight[len - 1] = len;
        for(int i = len - 2; i >= 0; i--){
            int t = i + 1;
            while(t < len && heights[t] >= heights[i]){
                t = minRight[t];
            }
            minRight[i] = t;
        }
        //求和
        int res = 0;
        for(int i = 0; i < len; i++){
            int sum = heights[i] * (minRight[i] - minLeft[i] - 1);
            res = Math.max(sum, res);
        }
        return res;
    }

    public static int largestRectangleArea2(int[] heights){
        Deque<Integer> stack = new LinkedList<>();

        //数组扩容 在头尾各加入一个元素
        int[] newHeight = new int[heights.length + 2];
        newHeight[0] = 0;
        newHeight[newHeight.length - 1] = 0;//可以让所有的高度都弹出
        for(int index = 0; index < heights.length; index++){
            newHeight[index + 1] = heights[index];
        }

        heights = newHeight;
        int len = heights.length;

        stack.push(0);
        int res = 0;
        for(int i = 1; i < len; i++){
            if(heights[i] > heights[stack.peek()]){
                stack.push(i);
            }else if(heights[i] == heights[stack.peek()]){
                stack.pop();
                stack.push(i);
            }else {
                while(heights[i] < heights[stack.peek()]){
                    int mid = stack.peek();
                    stack.pop();
                    int left = stack.peek();
                    int right = i;
                    int w = right - left - 1;
                    int h = heights[mid];
                    res = Math.max(res, w * h);
                }
                stack.push(i);
            }
        }
        return res;
    }
}
