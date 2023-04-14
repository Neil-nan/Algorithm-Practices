package leecode03;

import java.util.*;

/**
 * @decription 按照codeTop的顺序刷的
 */
public class Array {

    /**
     * 题目：15 三数之和
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(nums[len - 1] < 0){
            return res;
        }
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){//去重
                return res;
            }
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            //创建左右指针
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum + nums[i] < 0){
                    left++;
                }else if(sum + nums[i] > 0){
                    right--;
                }else{//找到结果
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(new ArrayList<>(list));
                    //去重
                    while(left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;

    }

    /**
     * 题目：53 最大子数组和
     */
    //返回数组的解法 https://leetcode-cn.com/problems/maximum-subarray/solution/zheng-li-yi-xia-kan-de-dong-de-da-an-by-lizhiqiang/
    //贪心算法
    public static int maxSubArray(int[] nums){
        int res = Integer.MIN_VALUE;
        int count = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            count += nums[i];
            if(res < count){
                res = count;
            }
            if(count < 0){
                count = 0;
            }
        }
        return res;
    }

    //使用动态规划
    public static int maxSubArray2(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int len = nums.length;

        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = dp[0];

        for(int i = 1; i < len; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if(dp[i] > res){
                res = dp[i];
            }
        }

        return res;
    }

    /**
     * 题目：1 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        //创建hash表 key 数值 value 位置
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                res[1] = i;
                res[0] = map.get(target - nums[i]);
                return res;
            }else {
                map.put(nums[i], i);
            }
        }
        return res;
    }

    /**
     * 题目：33 搜索旋转排序数组
     */
    public static int search(int[] nums, int target){
        //二分查找 先找中间数
        if(nums == null || nums.length == 0){
            return -1;
        }
        int index = 0;
        int len = nums.length;
        while(index < len - 1 && nums[index] < nums[index + 1]){
            index++;
        }
        int res = -1;
        //二分查找
        if(target <= nums[len - 1]){
            res = binarySearch(nums, index + 1, len - 1, target);
            if(res != -1){
                return res;
            }
        }
        if(target <= nums[index]){
            res = binarySearch(nums, 0, index, target);
            if(res != -1){
                return res;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int left, int right, int target){
        while(left <= right){
            int mid = right - (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    //另外一种思路
    //将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
    //此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
    public static int search2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[left] <= nums[mid]){
                //left 到 mid 是顺序区间
                if(target >= nums[left] && target < nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                // mid 到 right 是顺序区间
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 题目：121 买卖股票的最佳时机
     */
    //所有股票问题的想法  https://leetcode.cn/circle/article/qiAgHn/
    public static int maxProfit(int[] prices){
        //左边的最小值和右边的最大值
        int left = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < prices.length; i++){
            left = Math.min(left, prices[i]);
            result = Math.max(result, prices[i] - left);
        }
        return result;
    }

    /**
     * 题目：88 合并两个有序数组
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1 有两个指针 一个指向nums2存放的位置  一个指向自己 nums2有一个指针指向自己
        //特殊情况进行判断
        if(n == 0){
            return;
        }
        // if(m == 0){
        //     nums1 = nums2;
        //     return;
        // }
        int index1 = m - 1;
        int index = nums1.length - 1;
        int index2 = n - 1;
        while(index >= 0){
            while(index1 >= 0 && index2 >= 0){
                int num1 = nums1[index1];
                int num2 = nums2[index2];
                if(num1 <= num2){
                    nums1[index] = num2;
                    index2--;
                }else {
                    nums1[index] = num1;
                    index1--;
                }
                index--;
            }
            while(index1 >= 0){
                nums1[index] = nums1[index1];
                index1--;
                index--;
            }
            while(index2 >= 0){
                nums1[index] = nums2[index2];
                index2--;
                index--;
            }
        }
    }

    //从后向前 双指针
    //在此遍历过程中的任意一个时刻，nums1 数组中有 m−p1−1个元素被放入 nums1的后半部，
    // nums2数组中有 n−p2−1 个元素被放入 nums1 的后半部，
    // 而在指针 p1的后面，nums1 数组有 m+n−p1−1 个位置。
    // m + n - p1 - 1 >= m - p1 - 1 + n - p2 - 1
    public static void merge2(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while(p1 >= 0 || p2 >= 0){
            if(p1 == -1){
                cur = nums2[p2--];
            }else if(p2 == -1){
                cur = nums1[p1--];
            }else if(nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            }else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
