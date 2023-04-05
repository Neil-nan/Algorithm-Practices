package leecode;

import java.util.*;

public class DoublePointer {
    public static void main(String[] args) {
        String s = "  hello world  ";
        reverseWords(s);
        fourSum(new int[]{1, -2, -5, -4, 3, 3, 3, 5}, -11);

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
        int len = nums.length;
        for(int fastIndex = 0; fastIndex < len; fastIndex++){
            if(nums[fastIndex] != val){
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    //左右指针
    public static int removeElement(int[] nums, int val){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
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
        int slowIndex = 0;
        int len = nums.length;
        for(int fastIndex = 0; fastIndex < len; fastIndex++){
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
    //快慢指针
    public static void moveZeroes(int[] nums){
        int slowIndex = 0;
        int len = nums.length;
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
        s = backspace(s);
        t = backspace(t);
        return s.equals(t);
    }

    public static String backspace(String str){
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        int count = 0;
        for(int right = len - 1; right >= 0; right--){
            if(str.charAt(right) == '#'){
                count++;
            }else {
                if(count > 0){
                    count--;
                }else {
                    sb.append(str.charAt(right));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 题目：977 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        int index = nums.length - 1;
        while(left <= right && index >= 0){
            int leftAns = nums[left] * nums[left];
            int rightAns = nums[right] * nums[right];
            if(leftAns >= rightAns){
                res[index] = leftAns;
                left++;
            }else {
                res[index] = rightAns;
                right--;
            }
            index--;
        }
        return res;
    }

    /**
     * 题目：344 反转字符串
     */
    public static void reverseString(char[] s){
        int left = 0;
        int right = s.length - 1;
        while(left <= right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 题目：剑指offer 05 替换空格
     */
    public static String replaceSpace1(String s){
        if(s == null || s.length() == 0){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ' '){
                sb.append("%20");
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    //不重新申请数组，只进行数组的扩容
    public static String replaceSpace2(String s){
        //判断
        if(s == null || s.length() == 0){
            return s;
        }
        //扩充空间，空格数量的两倍
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                sb.append("  ");
            }
        }
        //判断
        //若是没有空格直接返回
        if(sb.length() == 0){
            return s;
        }
        //若是有空格，创建两个指针
        int leftIndex = s.length() - 1;//左指针指向的是原始字符串的最后一个的位置
        s += sb.toString();
        int rightIndex = s.length() - 1;//右指针指向的是结果字符串的最后一个的位置
        char[] chars = s.toCharArray();
        while(leftIndex >= 0){
            //进行判断
            if(chars[leftIndex] == ' '){
                chars[rightIndex--] = '0';
                chars[rightIndex--] = '2';
                chars[rightIndex] = '%';
            }else {
                chars[rightIndex] = chars[leftIndex];
            }
            leftIndex--;
            rightIndex--;
        }
        return new String(chars);
    }

    /**
     * 题目：151 翻转字符串里的单词
     */
    public static String reverseWords(String s){
        StringBuffer sb = space(s);
        reverseAll(sb, 0, sb.length() - 1);
        reverseEach(sb);
        return sb.toString();
    }

    //去除两边和中间的多余空格
    public static StringBuffer space(String s){
        int left = 0;
        int right = s.length() - 1;
        //去除首尾两端的空格
        while(s.charAt(left) == ' '){
            left++;
        }
        while(s.charAt(right) == ' '){
            right--;
        }
        //去除中间的多余空格
        StringBuffer sb = new StringBuffer();
        for(int i = left; i <= right; i++){
            if(s.charAt(i) != ' ' || s.charAt(i - 1) != ' '){
                sb.append(s.charAt(i));
            }
        }
        return sb;
    }

    //反转整个字符串(左闭右闭)
    public static void reverseAll(StringBuffer sb, int left, int right){
//        int left = 0;
//        int right = sb.length() - 1;
        while(left <= right){
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    //反转每个单词
    public static void reverseEach(StringBuffer sb){
        int left = 0;
        int right = 0;
        while(right < sb.length()){
            while(right < sb.length() && sb.charAt(right) != ' '){
                right++;
            }
            reverseAll(sb, left, right - 1);
            left = right +1;
            right = left;
        }
    }

    /**
     * 题目：206 反转链表
     */
    public static ListNode reverseList(ListNode head){
        //创建虚拟头节点
//        ListNode dummy = new ListNode();
//        dummy.next = head;
        //创建节点
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode temp;
        while(curNode != null){
            temp = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = temp;
        }
        return preNode;
    }

    /**
     * 题目：19 删除链表的倒数第N个节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
        //创建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //快慢指针
        ListNode slowIndex = dummy;
        ListNode fastIndex = dummy;
        //快指针先跑
        for(int i = 0; i < n; i++){
            fastIndex = fastIndex.next;
        }
        //快慢指针一起跑
        while(fastIndex.next != null){
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        //这时慢指针在删除节点的前一个
        slowIndex.next = slowIndex.next.next;
        return dummy.next;

    }

    /**
     * 题目：面试题 02 07 链表相交
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        int lenA = 0;
        int lenB = 0;
        //指针
        ListNode curA = headA;
        ListNode curB = headB;
        //求两个链表的长度
        while(curA != null){
            curA = curA.next;
            lenA++;
        }
        while(curB != null){
            curB = curB.next;
            lenB++;
        }
        //判断 使A比B长
        if(lenB > lenA){
            //交换长度
            int temp = lenA;
            lenA = lenB;
            lenB = temp;
            //交换链表
            ListNode tempNode = headA;
            headA = headB;
            headB = tempNode;
        }
        //将指针进行还原
        curA = headA;
        curB = headB;
        //将curA放到和curB的相同位置
        int len = lenA - lenB;
        for(int i = 0; i < len; i++){
            curA = curA.next;
        }
        //找节点
        while(curA != null && curB != null){
            if(curA == curB){
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    /**
     * 题目：142 环形链表II
     */
    public static ListNode detectCycle(ListNode head){
        ListNode slowIndex = head;
        ListNode fastIndex = head;
        //条件判断想好
        while(fastIndex.next != null && fastIndex != null){
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            //证明是有环形的
            if(slowIndex == fastIndex){
                //x==z
                //一个从相遇节点，一个从节点开走
                ListNode index = head;
                while(index != slowIndex){
                    slowIndex = slowIndex.next;
                    index = index.next;
                }
                return index;
            }
        }
        return null;
    }

    /**
     * 题目：15 三数之和
     */
    public static List<List<Integer>> threeSum(int[] nums){
        //a + b c
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            //判断
            if(nums[i] > 0){
                return res;
            }
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            //创建左右指针
            int left = i + 1;
            int right = nums.length - 1;
            //注意边界
            while(left < right){
                int sum = nums[left] + nums[right];
                if(sum + nums[i] < 0){//左指针右移
                    left++;
                }else if(sum + nums[i] > 0){//右指针左移
                    right--;
                }else {//找到结果
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //对左右指针进行去重
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
     * 题目：18 四数之和
     */
    public static List<List<Integer>> fourSum(int[] nums, int target){
        //结果
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            //去重
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1; j < nums.length; j++){
                //去重
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                //创建左右指针
                int left = j + 1;
                int right = nums.length - 1;
                while(left < right){
                    //改成long
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum > target){//右指针左移
                        right--;
                    }else if(sum < target){//左指针右移
                        left++;
                    }else {//记录结果
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
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
        }
        return res;
    }

}
