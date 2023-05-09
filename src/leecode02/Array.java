package leecode02;

import javax.swing.plaf.IconUIResource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Array {

    /**
     * 题目：704 二分查找
     */
    public static int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = nums.length;
        while (leftIndex <= rightIndex){
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if(nums[mid] < target){
                leftIndex = mid + 1;
            }else if(nums[mid] > target){
                rightIndex = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 题目：35 搜索插入位置
     */
    public static int searchInsert(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex <= rightIndex){
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if(nums[mid] < target){
                leftIndex = mid + 1;
            }else {
                rightIndex = mid - 1;
            }
        }
        return rightIndex + 1;
    }

    /**
     * 题目：34 在排列数组中查找元素的第一个和最后一个位置
     */
    public static int[] searchRange(int[] nums, int target){
        //判断
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        //一共有三种情况
        //第一种：目标值在数组的两边
        //第二种：目标值在数组中，但是没有
        //第三种：目标值在数组中，并且可以找到
        int leftBorder = searchLeft(nums, target);
        int rightBorder = searchRight(nums, target);
        if(leftBorder == -2 || rightBorder == -2){//情况一
            return new int[]{-1, -1};
        }
        if(rightBorder - leftBorder > 1){//情况二
            return new int[]{leftBorder + 1, rightBorder - 1};
        }
        return new int[]{-1, -1};
    }

    //找右边界
    public static int searchRight(int[] nums, int target){
        int rightBorder = -2;
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while(leftIndex <= rightIndex){
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if(nums[mid] <= target){
                leftIndex = mid + 1;
                rightBorder = leftIndex;
            }else {
                rightIndex = mid - 1;
            }
        }
        //rightBorder = leftIndex;
        return rightBorder;
    }

    //找左边界
    public static int searchLeft(int[] nums, int target){
        int leftBorder = -2;
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while(leftIndex <= rightIndex){
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if(nums[mid] >= target){
                rightIndex = mid - 1;
                leftBorder = rightIndex;
            }else {
                leftIndex = mid + 1;
            }
        }
        return leftBorder;
    }

    /**
     * 题目：69 x的平方根
     */
    //返回右边界
    public static int mySqrt(int x){
        int left = 0;
        int right = x / 2 + 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            //祖传问题 long 要注意mid的变化是否在数据类型范围内
            if((long)mid * mid <= x){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    /**
     * 题目：367 有效的完全平方数
     */
    public static boolean isPerfectSquare(int num){
        int left = 1;
        int right = num / 2 + 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if((long)mid * mid > num){
                right = mid - 1;
            }else if((long)mid * mid < num){
                left = mid + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目：27 移除元素
     */
    //快慢指针
    public static int removeElement1(int[] nums, int val){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != val){
                int temp = nums[slowIndex];
                nums[slowIndex] = nums[fastIndex];
                nums[fastIndex] = temp;
                slowIndex++;
            }
        }
        return slowIndex;
    }

    //使用左右双指针
    public static int removeElement2(int[] nums, int val){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right--];
            }else {
                left++;
            }
        }

        return left;
    }

    /**
     * 题目：26 删除排序数组中的重复项
     */
    public static int removeDuplicates(int[] nums){
        //慢指针 上一个数字 快指针 下一个数字
        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != nums[slowIndex]){
                slowIndex++;
                nums[slowIndex] = nums[fastIndex];
            }
        }
        return slowIndex + 1;
    }

    /**
     * 题目：283 移动零
     */
    public static void moveZeroes(int[] nums){
        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != 0){
                int temp = nums[slowIndex];
                nums[slowIndex] = nums[fastIndex];
                nums[fastIndex] = temp;
                slowIndex++;
            }
        }
    }

    /**
     * 题目：844 比较含退格的字符串
     */
    public static boolean backspaceCompare(String s, String t){
        return backspace(s).equals(backspace(t));
    }

    public static String backspace(String s){
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        int count = 0;
        for(int i = len - 1; i >= 0; i--){
            if(s.charAt(i) == '#'){
                count++;
            }else {
                if(count > 0){
                    count--;
                }else {
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 题目：977 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums){
        //使用左右指针
        int left = 0;
        int right = nums.length - 1;
        int len = nums.length;
        int[] res = new int[len];
        int index = len - 1;
        while (left <= right && index >= 0){
            int leftRes = nums[left] * nums[left];
            int rightRes = nums[right] * nums[right];
            if(leftRes >= rightRes){
                res[index--] = leftRes;
                left++;
            }else {
                res[index--] = rightRes;
                right--;
            }
        }
        return res;
    }

    /**
     * 题目：209 长度最小的子数组
     */
    //最小滑动窗口
    public static int minSubArrayLen(int target, int[] nums){
        int res = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int count = 0;
        while(right < nums.length){
            count += nums[right];
            while(count >= target){
                //更新结果
                res = Math.min(res, right - left + 1);
                count -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 题目：904 水果成篮
     */
    //最大滑动窗口
    public static int totalFruit1(int[] fruits){
        if(fruits.length <= 2){
            return fruits.length;
        }
        //四个指针 两个指向两种不同的水果 一个向下进行遍历 一个存储下一个的第一个水果
        int f1 = 0;
        int f2 = 0;
        int temp = 0;
        int ans = 0;

        for(int i = 0; i < fruits.length; i++){
            if(fruits[i] != fruits[f1] && fruits[i] != fruits[f2]){
                //两种情况 相等和不相等
                if(fruits[f1] != fruits[f2]){
                    f1 = temp;
                }
                f2 = i;
            }

            //更新temp
            if(fruits[temp] != fruits[i]){
                temp = i;
            }
            ans = Math.max(ans, i - f1 + 1);
        }
        return ans;

    }

    //使用滑动窗口法
    public static int totalFruit2(int[] fruits){
        //key 水果种类 value 水果的个数
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0;
        int ans = 0;
        int right = 0;
        while(right < fruits.length){
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while(cnt.size() > 2){
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if(cnt.get(fruits[left]) == 0){
                    cnt.remove(fruits[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    /**
     * 题目：76 最小覆盖子串
     */
    //用数组做
    public static String minWindows(String s, String t){
        int[] need = new int[128];
        int slowIndex = 0;
        int fastIndex = 0;
        int count = t.length();
        String res = s + s;
        //填充需要寻找的字符串t
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        //最小滑动窗口
        while(fastIndex < s.length()){
            //证明s字符串中有，且还没有超
            if(need[s.charAt(fastIndex)] > 0){
                count--;
            }
            need[s.charAt(fastIndex)]--;
            //证明已经达到了要求
            while(count == 0){
                //小于零证明s中没有t的数值或者是多余的，不断进行缩小窗口
                while(need[s.charAt(slowIndex)] < 0){
                    need[s.charAt(slowIndex)]++;
                    slowIndex++;
                }
                res = (fastIndex - slowIndex + 1) < res.length() ? s.substring(slowIndex, fastIndex + 1) : res;
                need[s.charAt(slowIndex)]++;
                slowIndex++;
                count++;
            }
            fastIndex++;
        }
        return res.equals(s + s) ? "" : res;
    }

    /**
     * 题目：59 螺旋矩阵II
     */
    public static int[][] generateMatrix(int n){
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int index = 1;
        int tar = n * n;
        int[][] res = new int[n][n];
        while(index <= tar){
            //从左向右
            for(int i = left; i <= right; i++){
                res[top][i] = index++;
            }
            top++;
            //从上到下
            for(int i = top; i <= bottom; i++){
                res[i][right] = index++;
            }
            right--;
            //从右向左
            for(int i = right; i >= left; i--){
                res[bottom][i] = index++;
            }
            bottom--;
            for(int i = bottom; i >= top; i--){
                res[i][left] = index++;
            }
            left++;
        }
        return res;
    }

    /**
     * 题目：54 螺旋矩阵
     */
    public static List<Integer> spiralOrder(int[][] matrix){
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
        int len = matrix[0].length;
        int wide = matrix.length;
        //设置初始上下左右边界的值
        int left = 0;
        int right = len - 1;
        int top = 0;
        int bottom = wide - 1;
        int count = len * wide;
        while(count > 0){
            //从左向右  上
            for(int i = left; i <= right && count > 0; i++){
                result.add(matrix[top][i]);
                count--;
            }
            top++;
            //从上到下 右
            for(int i = top; i <= bottom && count > 0; i++){
                result.add(matrix[i][right]);
                count--;
            }
            right--;
            //下
            for(int i = right; i >= left && count > 0; i--){
                result.add(matrix[bottom][i]);
                count--;
            }
            bottom--;
            //左
            for(int i = bottom; i >= top && count > 0; i--){
                result.add(matrix[i][left]);
                count--;
            }
            left++;
        }
        return result;
    }

    /**
     * 题目：剑指offer 29 顺时针打印矩阵
     */
    public static int[] spiralOrder2(int[][] matrix){
        //判断
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int len = matrix[0].length;
        int wide = matrix.length;
        //创建边界的初始值
        int left = 0;
        int right = len - 1;
        int top = 0;
        int bottom = wide - 1;
        int[] res = new int[len * wide];
        int count = len * wide;
        int index = 0;
        while(index < count){
            //上
            for(int i = left; i <= right && index < count; i++){
                res[index++] = matrix[top][i];
            }
            top++;
            //右
            for(int i = top; i <= bottom && index < count; i++){
                res[index++] = matrix[i][right];
            }
            right--;
            //下
            for(int i = right; i >= left && index < count; i--){
                res[index++] = matrix[bottom][i];
            }
            bottom--;
            //左
            for(int i = bottom; i >= top && index < count; i--){
                res[index++] = matrix[i][left];
            }
            left++;
        }
        return res;
    }
}
