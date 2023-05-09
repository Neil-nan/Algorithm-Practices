package leetcode04;

import java.util.HashMap;
import java.util.Map;

public class Array {

    public static void main(String[] args) {
        minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    }
    /**
     * 题目：704 二分查找
     */
    public static int search(int[] nums, int target) {
        //判断数组
        if(nums == null || nums.length == 0){
            return -1;
        }
        //创建双指针
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){//左闭右闭
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 题目：35 搜索插入位置
     */
    public static int searchInsert(int[] nums, int target) {
        //判断
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(target < nums[0]){
            return 0;
        }
        if(target > nums[nums.length - 1]){
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right + 1;

    }

    /**
     * 题目：34 在排序数组中查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if(nums == null || nums.length == 0){
            return res;
        }
        if(target < nums[0] || target > nums[nums.length - 1]){
            return res;
        }

        int left = findLeftBoundary(nums, target);
        int right = findRightBoundary(nums, target);
        if(left > right){
            return res;
        }

        return new int[]{left, right};
    }

    //找左边界
    public int findLeftBoundary(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right + 1;
    }

    //找右边界
    public int findRightBoundary(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left - 1;
    }

    /**
     * 题目：69 x的平方根
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x / 2 + 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if((long)mid * mid < x){
                left = mid + 1;
            }else if((long)mid * mid > x){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return right;
    }

    /**
     * 题目：367 有效的完全平方数
     */
    public static boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num / 2 + 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if((long)mid * mid < num){
                left = mid + 1;
            }else if((long)mid * mid > num){
                right = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 题目：27 移除元素
     */
    //快慢双指针
    public static int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //使用快慢指针
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    //左右双指针
    public static int removeElement2(int[] nums, int val) {
        //使用左右指针
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
            }else{
                left++;
            }
        }
        return left;
    }

    /**
     * 题目：26 删除排序数组中的重置项
     */
    public static int removeDuplicates(int[] nums) {
        //快慢指针
        if(nums == null || nums.length == 0){
            return 0;
        }
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /**
     * 题目：283 移动零
     */
    public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        //创建快慢指针
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != 0){
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }
        return;
    }

    /**
     * 题目：844 比较含退格的字符串
     */
    public static boolean backspaceCompare(String s, String t) {
        return backspace(s).equals(backspace(t));
    }

    public static String backspace(String s){
        int len = s.length();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for(int i = len - 1; i >= 0; i--){
            if(s.charAt(i) == '#'){
                count++;
            }else{
                if(count > 0){
                    count--;
                }else{
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 题目：977 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        int len = nums.length;
        int[] res = new int[len];
        //创建左右指针
        int left = 0;
        int right = len - 1;
        int index = len - 1;;
        while(left <= right && index >= 0){
            int leftNum = nums[left];
            int rightNum = nums[right];
            if(leftNum * leftNum >= rightNum * rightNum){
                res[index] = leftNum * leftNum;
                left++;
            }else{
                res[index] = rightNum * rightNum;
                right--;
            }
            index--;
        }
        return res;
    }

    /**
     * 题目：209 长度最小的子数组
     */
    public static int minSubArrayLen(int target, int[] nums) {
        //最小滑动窗口
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int len = nums.length;
        int right = 0;
        int left = 0;
        while(right < len){
            sum += nums[right];
            //right++;
            //res = Math.min(res, right - left + 1);
            while(sum >= target){
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 题目：904 水果成篮
     */
    public static int totalFruit(int[] fruits) {
        //最长滑动窗口
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0;
        int right = 0;
        int ans = 0;
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

    public static int totalFruit2(int[] fruits) {
        if(fruits.length <= 2){
            return fruits.length;
        }
        //四个指针 指向左右边界 和两个水果
        int f1 = 0;
        int f2 = 0;
        int temp = 0;
        int ans = 0;

        for(int i = 0; i < fruits.length; i++){
            if(fruits[i] != fruits[f1] && fruits[i] != fruits[f2]){
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

}
