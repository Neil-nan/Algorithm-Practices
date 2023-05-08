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
}
