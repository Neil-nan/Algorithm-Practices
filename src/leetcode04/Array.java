package leetcode04;

public class Array {

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
}
