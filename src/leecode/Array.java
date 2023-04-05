package leecode;

import java.util.*;

public class Array {

    public static void main(String[] args) {
        //int i = searchInsert2(new int[]{1, 3, 5, 6}, 2);
        int[] nums = new int[]{3, 3, 3, 1, 2, 1, 1, 1, 3, 3, 4};
        totalFruit2(nums);
        minWindow("ADOBECODEBANC", "ABC");
    }

//    /**
//     * 704 二分查找
//     */
//    public static int search(int[] nums, int target){
//        //判断
//        if(nums == null || nums.length == 0){
//            return  -1;
//        }
//
//        int n = nums.length;
//        //创建指针
//        int left = 0;
//        int right = n - 1;
//        //二分查找
//        while (left <= right){
//            //防止超过容量
//            int mid = left + (right - left) / 2;
//
//            //变化左右边界
//            if(nums[mid] < target){
//                left = mid + 1;
//            }else if(nums[mid] > target){
//                right = mid - 1;
//            }else {
//                //输出结果
//                return mid;
//            }
//        }
//
//        return -1;
//    }
//
//    /**
//     * 35 搜索插入位置
//     */
//    //分四种情况，在数组外两种（数组左边，数组右边），在数组里两种（数组中有，数组中没有）
//    public static int searchInsert(int[] nums, int target){
//        //判断
//        if(nums == null || nums.length == 0){
//            return -1;
//        }
//        //判断在数组外围
//        //左边
//        if(target < nums[0]){
//            return 0;
//        }
//        if(target > nums[nums.length - 1]){
//            return nums.length;
//        }
//        //判断在数组内
//        //创建指针
//        int n = nums.length;
//        int left = 0;
//        int right = n - 1;
//
//        //使用右指针求左边界
//        while(left <= right){
//            int mid = left + (right - left) / 2;
//            if(nums[mid] < target){
//                left = mid + 1;
//            }else {
//                //等于时右指针也动
//                right = mid - 1;
//            }
//        }
//
//        return right + 1;
//    }
//
//
//    /**
//     * 34 在排列数组中查找元素的第一个和最后一个位置
//     */
//    //三种情况，一是在数组的左右两侧，二是在数组中，但是没有目标值，三是在数组中且有目标值
//    public static int[] searchRange(int[] nums, int target){
//        if(nums == null || nums.length == 0){
//            return new int[]{-1, -1};
//        }
//        //求左右边界
//        int leftBorder = searchLeft(nums, target);
//        int rightBorder = searchRight(nums, target);
//        //情况一
//        if(leftBorder == -2 || rightBorder == -2){
//            return new int[]{-1, -1};
//        }
//        //情况三
//        if(rightBorder - leftBorder > 1){
//            return new int[]{leftBorder + 1, rightBorder - 1};
//        }
//        //情况二
//        return new int[]{-1, -1};
//    }
//
//    //使用右指针寻找左边界
//    public static int searchLeft(int[] nums, int target){
//        int n = nums.length;
//        int left = 0;
//        int right = n - 1;
//        int leftBorder = -2;//记录左边界没有被记录的情况
//        while (left <= right){
//            int mid = left + (right - left) / 2;
//            if(nums[mid] < target){
//                left = mid + 1;
//            }else {
//                right = mid - 1;
//                leftBorder = right;
//            }
//        }
//
//        return leftBorder;
//    }
//
//    //使用左指针寻找右边界
//    public static int searchRight(int[] nums, int target){
//        int n = nums.length;
//        int left = 0;
//        int right = n - 1;
//        int rightBorder = -2;
//        while (left <= right){
//            int mid = left + (right - left) / 2;
//            if(nums[mid] <= target){
//                left = mid + 1;
//                rightBorder = left;
//            }else {
//                right = mid - 1;
//            }
//        }
//        return rightBorder;
//    }
//
//    /**
//     * 69 x的平方根
//     */
//    //想法，所要找的target值有两种情况，一种是正好是整数，另一种是小数，思路数找到这个target的右边界
//    //也就是左指针，这样无论是情况一还是情况二都可以进行 "- 1"处理
//    public static int mySqrt(int x){
//        //创建指针
//        int left = 0;
//        int right = x;
//        while(left <= right){
//            int mid = left + (right - left) / 2;
//            //这里使用long转型防止超过int的数值范围
//            if((long)mid * mid <= x){
//                left = mid + 1;
//            }else if((long)mid * mid > x){
//                right = mid - 1;
//            }
//        }
//
//        return left - 1;
//    }
//
//    /**
//     * 367 有效的完全平方根
//     */
//    public static boolean isPerfectSquare(int num){
//        //创建指针
//        int left = 0;
//        int right = num;
//        while (left <= right){
//            int mid = left + (right - left) / 2;
//            if((long)mid * mid < num){
//                left = mid + 1;
//            }else if((long)mid * mid > num){
//                right = mid - 1;
//            }else {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 27 移除元素
//     */
//    //快慢指针
//    public static int removeElement1(int[] nums, int val){
//        int slow = 0;
//        int fast = 0;
//        while (fast < nums.length){
//            //快指针找非目标值同慢指针的目标值进行交换
//            if(nums[fast] != val){
//                //交换
//                nums[slow] = nums[fast];
//                slow++;
//            }
//            fast++;
//        }
//        return slow;
//    }
//
//    //因为不需要注意输出数组的元素顺序，所以可以使用左右双指针
//    //双指针优化
//    public static int removeElement2(int[] nums, int val){
//        int left = 0;
//        int right = nums.length - 1;
//        while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置
//        while(left <= right) {
//            if(nums[left] == val) { //left位置的元素需要移除
//                //将right位置的元素移到left（覆盖），right位置移除
//                nums[left] = nums[right];
//                right--;
//            }
//            left++;
//            while(right >= 0 && nums[right] == val) right--;
//        }
//        return left;
//    }
//
//    /**
//     * 26 删除排序数组中的重复项
//     */
//    public static int removeDuplicates(int[] nums){
//        int n = nums.length;
//        if(nums == null || n == 0){
//            return 0;
//        }
//        if(n == 1){
//            return 1;
//        }
//        int slow = 1;
//        int fast = 1;
//        while(fast < n){
//            //注意判断条件，不是慢指针和快指针进行判断，因为有可能出现慢指针的下一个数是之前的数字，导致和快指针不一样，进行判断交换，应该判断快指针和快指针的前一个指针
//            //看数字有没有更新，有更新就用慢指针进行交换
//            if(nums[fast - 1] != nums[fast]){
//                nums[slow] = nums[fast];
//                slow++;
//            }
//            fast++;
//        }
//        return slow;
//    }
//
//    /**
//     * 283 移动零
//     */
//    public static void moveZeroes(int[] nums){
//        //判断
//        if(nums == null || nums.length == 0){
//            return;
//        }
//        //创建快慢指针
//        int slow = 0;
//        int fast = 0;
//        while (fast < nums.length){
//            if(nums[fast] != 0){
//                int temp = 0;
//                temp = nums[slow];
//                nums[slow] = nums[fast];
//                nums[fast] = temp;
//                slow++;
//            }
//            fast++;
//        }
//    }
//
//    /**
//     * 844 比较含退格的字符串
//     */
//    //指针从后往前
//    public static boolean backspaceCompare(String s, String t){
//        int sLen = s.length();
//        int tLen = t.length();
//        //字符串s 和 t的指针
//        int indexS = sLen - 1;
//        int indexT = tLen - 1;
//        //保存#的个数
//        int skipS = 0;
//        int skipT = 0;
//
//        while (indexS >= 0 || indexT >= 0){
//            //字符串s消除#后生成的字母
//            while (indexS >= 0){
//                //情况一，遇到#
//                if(s.charAt(indexS) == '#'){
//                    //#存储，指针指向下一位
//                    skipS++;
//                    indexS--;
//                }else if(skipS > 0){//情况二，遇见字母，有#
//                    //消耗#的存储，指针指向下一位
//                    skipS--;
//                    indexS--;
//                }else {//情况三，遇见字母且没有#
//                    break;
//                }
//            }
//
//            //字符串t消除#后生成的字母
//            while (indexT >= 0){
//                if(t.charAt(indexT) == '#'){
//                    skipT++;
//                    indexT--;
//                }else if(skipT > 0){
//                    skipT--;
//                    indexT--;
//                }else {
//                    break;
//                }
//            }
//
//            //将s和t的字母进行比较
//            if(indexS >= 0 && indexT >= 0){
//                if(s.charAt(indexS) != t.charAt(indexT)){
//                    return false;
//                }
//            }else {
//                if (indexS >= 0 || indexT >= 0){
//                    return false;
//                }
//            }
//            indexS--;
//            indexT--;
//        }
//
//        return true;
//
//    }
//
//    /**
//     * 977 有序数组的平方
//     */
//    public static int[] sortedSquares(int[] nums){
//        //判断
//        if(nums == null || nums.length == 0){
//            return new int[0];
//        }
//
//        int len = nums.length;
//        int left = 0;
//        int right = len - 1;
//        //创建答案
//        int[] res = new int[len];
//        int index = len - 1;
//
//        while(left <= right && index >= 0){
//            if(nums[right] * nums[right] >= nums[left] * nums[left]){
//                res[index] = nums[right] * nums[right];
//                right--;
//            }else {
//                res[index] = nums[left] * nums[left];
//                left++;
//            }
//            index--;
//        }
//
//        return res;
//    }
//
//    /**
//     * 209 长度最小的子数组
//     */
//    //审错题了，重新来
//    public static int minSubArrayLen(int target, int[] nums){
//        //数组长度
//        int ans = Integer.MAX_VALUE;
//        int left = 0;
//        int sum = 0;
//        for(int right = 0; right < nums.length; right++){
//            sum += nums[right];
//            while (sum >= target){
//                ans = Math.min(ans, right - left + 1);
//                //左指针进一位
//                sum -= nums[left++];
//            }
//        }
//
//        return ans == Integer.MAX_VALUE ? 0 : ans;
//    }
//
//    /**
//     * 904 水果成篮
//     */
//    //自己想的方法，有重复的情况进行讨论， 可能需要去重(这部分感觉没办法进行优化)
//    //其实和官方解法类似，只是没有了未来两个篮子里的第一个篮子的起始索引这个指针
//    public static int totalFruit1(int[] fruits){
//        //判断
//        if(fruits.length < 3){
//            return fruits.length;
//        }
//
//        int res = 0;
//        //创建3个指针，前两个指针分别记录着放入篮中的两种水果，第3个指针寻找最远的值
//        int index1 = 0;
//        int index2 = 0;
//        int index3 = 0;
//        //仿照官方解，未来两个篮子第一个篮子的起始索引
//        int index4 = 0;
//
//        while (index3 < fruits.length){
//            //寻找第二种水果
//            while (index1 < fruits.length && index2 < fruits.length && fruits[index2] == fruits[index1]){
//                index2++;
//                //index3++;
//            }
//            index3 = index2;
//            //通过第三个指针找出两种水果的最大值
//            while (index3 < fruits.length && (fruits[index3] == fruits[index1] || fruits[index3] == fruits[index2])){
//                index3++;
//            }
//
//            res = Math.max(res, index3 - index1);
//            //将指针1移动到指针2的位置
//            index1 = index2;
//        }
//
//        return res;
//    }
//
//    //官方解答
//    public static int totalFruits(int[] fruits){
//        //判断
//        if(fruits.length < 3){
//            return fruits.length;
//        }
//
//        //返回结果，res
//        int res = 0;
//        //j为遍历fruits数组的指针，也是滑动窗口的右边界
//        //f1,f2分别为第一个和第二个篮子的起始索引，f1同时也是滑动窗口的左边界
//        //t为未来两个篮子里的第一个篮子的起始索引
//        for(int j = 0, f1 = 0, f2 = 0, t = 0; j < fruits.length; j++){
//            //当f1=f2时，fruits[j]!=fruits[f1]&&fruits[j]!=fruits[f2]说明遇到的是第二个篮子要装的第二种水果；
//            //当f1!=f2时，fruits[j]!=fruits[f1]&&fruits[j]!=fruits[f2]说明遇到的是第三种水果
//            if(fruits[j] != fruits[f1] && fruits[j] != fruits[f2]){
//                //当f1=f2时，说明第一个篮子已经装了一种水果，第二个篮子里还没有装，不更新f1，只更新f2，
//                //即往第二个篮子里装第二种水果；
//                //当f1!=f2时，fruits[j]!=fruits[f1]&&fruits[j]!=fruits[f2]说明遇到的是第三种水果，则更新f1和f2，
//                //即当前两个篮子的两种水果已装满，更新f1和f2为未来两个篮子里的两种水果
//                if (f1 != f2){
//                    f1 = t;
//                    //f2 = j;
//                }
//                f2 = j;
//            }
//            //t寻找未来两个篮子中第一个篮子要装的一种水果的起始索引，每次t与当前的j作对比，当fruits[t]!=fruits[j]时，更新t=j
//            if(fruits[t] != fruits[j]){
//                t = j;
//            }
//            res = res > (j - f1 + 1) ? res : (j - f1 + 1);
//        }
//        return res;
//    }
//
//
//
//    /**
//     * 76 最小覆盖子串
//     */
//     public static String minWindow(String s, String t){
//         //判断
//         if(s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()){
//             return "";
//         }
//         //1.维护两个map记录窗口中的符合条件的字符以及need的字符
//         Map<Character,Integer> window = new HashMap<>();
//         Map<Character,Integer> need = new HashMap<>();//need中存储的是需要的字符以及需要的对应的数量
//
//         //将need中进行上弹
//         for(char c : t.toCharArray()) {
//             need.put(c, need.getOrDefault(c, 0) + 1);
//         }
//
//         int left = 0,right = 0;//双指针
//         int count = 0;//count记录当前窗口中符合need要求的字符的数量,当count == need.size()时即可shrik窗口
//         int start = 0;//start表示符合最优解的substring的起始位序
//         int len = Integer.MAX_VALUE;//len用来记录最终窗口的长度，并且以len作比较，淘汰选出最小的substring的len
//
//         //一次遍历找“可行解”
//         while(right < s.length()){
//             //更新窗口
//             char c = s.charAt(right);
//             right++;//窗口扩大
//             // window.put(c,window.getOrDefault(c,0)+1);其实并不需要将s中所有的都加入windowsmap，只需要将need中的加入即可
//             if(need.containsKey(c)){
//                 window.put(c,window.getOrDefault(c,0)+1);
//                 if(need.get(c).equals(window.get(c))){
//                     count++;
//                 }
//             }
//             //System.out.println****Debug位置
//             //shrink左边界，找符合条件的最优解
//             while(count == need.size()){
//                 if(right - left < len){//不断“打擂”找满足条件的len最短值,并记录最短的子串的起始位序start
//                     len = right - left;
//                     start = left;
//                 }
//                 //更新窗口——这段代码逻辑几乎完全同上面的更新窗口
//                 char d = s.charAt(left);
//                 left++;//窗口缩小
//                 if(need.containsKey(d)){
//                     //window.put(d,window.get(d)-1);——bug：若一进去就将window对应的键值缩小，就永远不会满足下面的if，while也会一直执行，知道left越界，
//                     // 因此，尽管和上面对窗口的处理几乎一样，但是这个处理的顺序还是很关键的！要细心！
//                     if(need.get(d).equals(window.get(d))){
//                         count--;
//                     }
//                     window.put(d,window.get(d)-1);
//
//                 }
//             }
//         }
//         return len == Integer.MAX_VALUE ? "" : s.substring(start,start+len);
//
//     }

    /**
     * 题目：704 二分查找
     */
    //方法一 左闭右开区间
    public static int search(int[] nums, int target){
        //数组判断
        if(nums == null || nums.length == 0){
            return -1;
        }

        //创建左右指针(左闭右开区间)
        int left = 0;
        int right = nums.length;
        while (left < right){
            int mid = (right - left) / 2 + left;//防止int溢出
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid;
            }else {
                return mid;
            }
        }

        return -1;
    }

    //方法二
    public static int search2(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
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
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;//等于的时候也是让右指针先动，保证右指针的右面一位是结果(这里=不适合放在左指针)
            }
        }

        return right + 1;
    }

    /**
     * 题目：34 在排序数组中查找元素的第一个和最后一个位置
     */
    //方法一 先找一个 然后向左右扩散找
    public static int[] searchRange1(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }

        int index = binarySearch(nums, target);
        if(index == -1){
            return new int[]{-1, -1};
        }
        //向左向右进行寻找
        int left = index;
        int right = index;
        while(left >= 0 && nums[left] == nums[index]){
            left--;
        }
        while(right < nums.length && nums[right] == nums[index]){
            right++;
        }

        return new int[]{left++, right--};
    }

    public static int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else {
                return mid;
            }
        }

        return -1;
    }

    //方法二 通过左右指针去分别找左右边界
    public static int[] searchRange2(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        //如果目标值是在数组两侧的，那查找其左右边界的时候必有一个边界是不动的
        int leftBorder = findLeft(nums, target);
        int rightBorder = findRight(nums, target);
        //情况一
        if(leftBorder == -2 || rightBorder == -2){
            return new int[]{-1, -1};
        }
        //情况三
        if(rightBorder - leftBorder > 1){
            return new int[]{leftBorder + 1, rightBorder - 1};
        }
        //情况二
        return new int[]{-1, -1};
    }

    public static int findLeft(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
                leftBorder = right;
            }
        }
        return leftBorder;
    }

    public static int findRight(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if(nums[mid] <= target){
                left = mid + 1;
                rightBorder = left;
            }else {
                right = mid - 1;
                Math.sqrt(4);
            }
        }
        return rightBorder;
    }

    /**
     * 题目：69 x的平方根
     */
    //左右指针其实都可以，看方法
    //使用左指针
    public static int mySqrt1(int x){
        int left = 0;
        int right = x / 2 + 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if((long)mid * mid <= x){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    //使用右指针
    public static int mySqrt2(int x){
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
    public static boolean isPerfectSquare(int num){
        int left = 0;
        int right = num / 2 + 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if((long)mid * mid <= num){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return (left - 1) * (left - 1) == num;
    }

    /**
     * 题目：27 移除元素
     */
    //使用相向指针
    public static int removeElement1(int[] nums, int val){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex <= rightIndex){
            if(nums[leftIndex] == val){
                nums[leftIndex] = nums[rightIndex];
                rightIndex--;
            }else {
                leftIndex++;
            }
        }
        return leftIndex;
    }

    //使用快慢指针
    public static int removeElement(int[] nums, int val){
        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != val){
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    /**
     * 题目：26 删除排序数组中的重置项
     */
    //自己想的
    public static int removeDuplicates(int[] nums){
//        if(nums.length == 1){
//            return 1;
//        }
        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[slowIndex] != nums[fastIndex]){
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

        //交换之后就需要把慢指针后面的数进行修改了
//        for(int i = slowIndex; i < nums.length; i++){
//            nums[i] = 0;
//        }
    }

    /**
     * 题目：844 比较含退格的字符串
     */
    //自创
    public static boolean backspaceCompare1(String s, String t){
        return backspace(s).equals(backspace(t));
    }

    public static String backspace(String s){
        int len = s.length();
        int count = 0;
        StringBuilder sb = new StringBuilder();
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

    //重新整合数组
    public static boolean backspaceCompare2(String s, String t){
        return build(s).equals(build(t));
    }

    public static String build(String s){
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) != '#'){
                //添加字符
                sb.append(s.charAt(i));
            }else {
                //删除字符
                if(sb.length() > 0){
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }

        return sb.toString();
    }

    //解法2 双指针 从前向后进行遍历
    public static boolean backspaceCompare3(String s, String t){
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while(i >= 0 || j >= 0){
            while(i >= 0){
                if(s.charAt(i) == '#'){
                    skipS++;
                    i--;
                }else if(skipS > 0){
                    skipS--;
                    i--;
                }else {
                    break;
                }
            }

            while(j >= 0){
                if(t.charAt(j) == '#'){
                    skipT++;
                    j--;
                }else if(skipT > 0){
                    skipT--;
                    j--;
                }else {
                    break;
                }
            }

            //比较
            if(i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)){
                    return false;
                }
            }else {
                if(i >= 0 || j >= 0){
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }

    /**
     * 题目：977 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums){
        int len = nums.length;
        int leftIndex = 0;
        int rightIndex = len - 1;
        int[] res = new int[len];
        int index = len - 1;
        while (leftIndex <= rightIndex && index >= 0){
            int left = nums[leftIndex] * nums[leftIndex];
            int right = nums[rightIndex] * nums[rightIndex];
            if(left <= right){
                res[index] = right;
                rightIndex--;
            }else {
                res[index] = left;
                leftIndex++;
            }
            index--;
        }
        return res;
    }

    /**
     * 题目：209 长度最小的子数组
     */
    public static int minSubArrayLen(int target, int[] nums){
        int slowIndex = 0;
        int len = nums.length;
        int ans = Integer.MAX_VALUE;
        int sum = 0;

        for(int fastIndex = 0; fastIndex < len; fastIndex++){
            sum += nums[fastIndex];
            while(sum >= target){
                ans = Math.min(ans, fastIndex - slowIndex + 1);
                sum -= nums[slowIndex];
                slowIndex++;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 题目：904 水果成篮
     */
    public static int totalFruit1(int[] fruits){
        //创建3个指针，两个指针指向水果的种类，一个指针指向两种水果可以达到的最远距离
        int fruitIndex1 = 0;
        int fruitIndex2 = 0;
        int fastIndex = 0;
        int ans = Integer.MIN_VALUE;
        while (fastIndex < fruits.length){
            while ( fruitIndex2 < fruits.length && fruits[fruitIndex1] == fruits[fruitIndex2]){
                fruitIndex2++;
                fastIndex = fruitIndex2;
            }
            while (fastIndex < fruits.length && (fruits[fastIndex] == fruits[fruitIndex1] || fruits[fastIndex] == fruits[fruitIndex2])){
                fastIndex++;
            }

            ans = Math.max(ans, fastIndex - fruitIndex1);
            fruitIndex1 = fruitIndex2;
        }
        return ans;
    }

    //使用一个和我类似的思路但是比我的好，多添加了一个指针，避免我我方法中的重复查找
    //具体思路看之前的文件
    public static int totalFruit2(int[] fruits){
        if(fruits.length < 3){
            return fruits.length;
        }
        //共四个指针，两个指针指向两种不同的水果，一个指针指向未来一组的第一种水果的下标，另一个指针负责进行向后遍历
        int f1 = 0;
        int f2 = 0;
        int temp = 0;
        int ans = 0;
        for(int j = 0; j < fruits.length; j++){
            if(fruits[j] != fruits[f1] && fruits[j] != fruits[f2]){
                //两种情况，f1 和 f2 相等 f1 和 f2 不相等
                if(fruits[f1] != fruits[f2]){
                    f1 = temp;
                }
                f2 = j;
            }

            //更新特temp指针(与j不相同的第一次，之后要将这个指针传给第一个水果指针)
            if(fruits[temp] != fruits[j]){
                temp = j;
            }

            ans = Math.max(ans, j - f1 + 1);
        }
        return ans;
    }

    //使用滑动窗口法
    public static int totalFruit3(int[] fruits){
        int len = fruits.length;
        //key 水果的种类  value 水果的个数
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0;
        int ans = 0;
        for(int right = 0; right < len; right++){
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2){
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if(cnt.get(fruits[left]) == 0){
                    cnt.remove(fruits[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    //使用滑动窗口法
    public static int totalFruit4(int[] f){
        int left=0;
        int right=0;
        int max=0;
        int count=0; //记录目前摘取到的水果种类
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0 ; i<f.length ;i++){
            map.put(f[i],0);
        }
        while (right<f.length){
            //判断是否拥有当前水果种类
            if (map.get(f[right]) == 0){
                //判断通过，当前无此类水果，则种类+1；
                count++;
            }
            //采摘此树上的水果。
            map.put(f[right],map.get(f[right])+1);
            //判断水果种类是否大于2
            while (count>2){
                if (map.get(f[left]) == 1){
                    count--;
                }
                map.put(f[left],map.get(f[left])-1);
                left++;
            }
            max = Math.max(max,right-left+1);
            right++;
        }
        return max;
    }

    /**
     * 题目：76 最小覆盖子串
     */
    //使用滑动窗口法
    public static String minWindow(String s, String t){
        int[] need = new int[128];
        int slowIndex = 0;
        int fastIndex = 0;
        int count = t.length();
        String res = s + s;
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        while (fastIndex < s.length()){
            if(need[s.charAt(fastIndex)] > 0){
                count--;
            }
            need[s.charAt(fastIndex)]--;
            while(count == 0){
                while (need[s.charAt(slowIndex)] < 0){
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
        int[][] res = new int[n][n];
        int loop = n / 2 ;
        int l = n - 1;
        int row = 0;
        int column = 0;
        int dot = 0;
        for(int i = 0; i < loop; i++){
            int count = 0;
            int a = row;
            int b = column;
            //上
            for(int j = 0; j < l; j++){
                res[a][b++] = j + 1 + l * count + dot;
            }
            count++;
            //右
            for(int j = 0; j < l; j++){
                res[a++][b] = j + 1 + l * count + dot;
            }
            count++;
            //下
            for(int j = 0; j < l; j++){
                res[a][b--] = j + 1 + l * count + dot;
            }
            count++;
            //左
            for(int j = 0; j < l; j++){
                res[a--][b] = j + 1 + l * count + dot;
            }

            row++;
            column++;
            l -= 2;
            dot = res[++a][b];
        }

        if(n % 2 == 1){
            int i = (n - 1) / 2;
            res[i][i] = n * n;
        }

        return res;
    }

    //完美方法
    public int[][] generateMatrix2(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }

    /**
     * 题目：54 螺旋矩阵
     */
    //真心不会，看答案
    public static List<Integer> spiralOrder1(int[][] matrix){
        List<Integer> list = new ArrayList<>();
        //判断
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        //边界
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int numEle = matrix.length * matrix[0].length;
        while (numEle > 0){
            //上
            for(int i = left; i <= right && numEle > 0; i++){
                list.add(matrix[top][i]);
                numEle--;
            }
            top++;
            //右
            for(int i = top; i <= bottom && numEle > 0; i++){
                list.add(matrix[i][right]);
                numEle--;
            }
            right--;
            //下
            for(int i = right; i >= left && numEle > 0; i--){
                list.add(matrix[bottom][i]);
                numEle--;
            }
            bottom--;
            //左
            for(int i = bottom; i >= top && numEle > 0; i--){
                list.add(matrix[i][left]);
                numEle--;
            }
            left++;
        }
        return list;
    }


    /**
     * 题目：剑指offer 29 顺时针打印矩阵
     */
    public static int[] spiralOrder(int[][] matrix){
        //判断
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        //边界
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int numEle = matrix.length * matrix[0].length;
        int[] res = new int[numEle];
        int count = 0;

        while (count < numEle){
            //上
            for(int i = left; i <= right && count < numEle; i++){
                res[count++] = matrix[top][i];
            }
            top++;
            //右
            for(int i = top; i <= bottom && count < numEle; i++){
                res[count++] = matrix[i][right];
            }
            right--;
            //下
            for(int i = right; i >= left && count < numEle; i--){
                res[count++] = matrix[bottom][i];
            }
            bottom--;
            //左
            for(int i = bottom; i >= top && count < numEle; i--){
                res[count++] = matrix[i][left];
            }
            left++;
        }
        return res;
    }

    public int[] spiralOrder2(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;
    }


}

