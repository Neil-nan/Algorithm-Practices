package leecode02;

public class DoublePointer {

    /**
     * 题目：27 移除元素
     */
    public static int removeElement(int[] nums, int val){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    //左右指针
    public static int removeElement2(int[] nums, int val){
        //判读
        if(nums == null || nums.length == 0){
            return 0;
        }
        //左右指针
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
        //判断
        if(nums == null || nums.length == 0){
            return 0;
        }
        //创建快慢指针
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
    //保持相对顺序 只能使用快慢指针
    public static void moveZeroes(int[] nums){
        if(nums == null || nums.length == 0){
            return;
        }
        //创建快慢指针
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != 0){
                //和slow进行交换
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }
    }

    /**
     * 题目：844 比较含退格的字符串
     */
    public static boolean backspaceCompare(String s, String t){
        //使用指针从后向前进行遍历
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;
        //用来记录
        int sNum = 0;
        int tNum = 0;
        //用来记录退格后的最后结果
        StringBuffer sSb = new StringBuffer();
        StringBuffer tSb = new StringBuffer();
        while(sIndex >= 0){
            if(s.charAt(sIndex) != '#'){
                if(sNum <= 0){
                    sSb.append(s.charAt(sIndex));
                }else {
                    sNum--;
                }
            }else {
                sNum++;
            }
            sIndex--;
        }

        while(tIndex >= 0){
            if(t.charAt(tIndex) != '#'){
                if(tNum <= 0){
                    tSb.append(t.charAt(tIndex));
                }else {
                    tNum--;
                }
            }else {
                tNum++;
            }
            tIndex--;
        }
        return sSb.toString().equals(tSb.toString());
    }

    /**
     * 题目：977 有序数组的平方
     */
    public static int[] sortedSquares(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        //使用左右双指针
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        int index = res.length - 1;
        while(left <= right && index >= 0){
            int tempLeft = nums[left] * nums[left];
            int tempRight = nums[right] * nums[right];
            if(tempLeft >= tempRight){
                res[index] = tempLeft;
                left++;
            }else {
                res[index] = tempRight;
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
        if(s == null || s.length == 0){
            return;
        }
        //创建左右双指针
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
    public static String replaceSpace(String s){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                sb.append("%20");
            }else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 题目：151 反转字符串中的单词
     */
    //去除多余空格 反转整个字符串 反抓每个单项
    public static String reverseWords(String s){
        //去除多余空格
        StringBuilder sb = removeSpace(s);
        //反转整个字符串
        reverse(sb, 0, sb.length() - 1);
        int index = 0;
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == ' '){
                reverse(sb, index, i - 1);
                index = i + 1;
            }
        }
        //反转最后一个
        reverse(sb, index, sb.length() - 1);
        return sb.toString();
    }

    //去除空格
    public static StringBuilder removeSpace(String s){
        int left = 0;
        int right = s.length() - 1;
        //去除首尾的空格
        while(s.charAt(left) == ' '){
            left++;
        }
        while(s.charAt(right) == ' '){
            right--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = left; i <= right; i++){
            if(s.charAt(i) != ' ' || s.charAt(i - 1) != ' '){
                sb.append(s.charAt(i));
            }
        }
        return sb;
    }

    //反转字符串
    public static void reverse(StringBuilder sb, int left, int right){
        while(left <= right){
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    /**
     * 题目：206 反转链表
     */
    public static ListNode reverseList(ListNode head){
        //创建指针
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 题目：24 两两交换链表中的节点
     */
    public static ListNode swapPairs(ListNode head){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;

        while(cur.next != null && cur.next.next != null){
            ListNode temp = cur.next;
            ListNode temp1 = cur.next.next.next;

            cur.next = cur.next.next;
            cur.next.next = temp;
            cur.next.next.next = temp1;

            cur = cur.next.next;
        }

        return dummy.next;

    }

    /**
     * 题目：19 删除链表的倒数第N个节点
     */
    //创建快慢指针
    public static ListNode removeNthFromEnd(ListNode head, int n){
        //创建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //慢指针在删除节点的前面
        slow.next = slow.next.next;
        return dummy.next;
    }
}
