package leecode03;

import java.util.List;

public class LinkedList {

    /**
     * 题目：206 反转链表
     */
    public static ListNode reverseList(ListNode head){
        //创建虚拟头结点
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;

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

    //递归法
    public static ListNode reverseList2(ListNode head){
        return reverse(null, head);
    }

    public static ListNode reverse(ListNode pre, ListNode cur){
        //终止条件
        if(cur == null){
            return pre;
        }

        ListNode temp = null;
        temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

    /**
     * 题目：25 K个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k){
        //创建虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while(end.next != null){
            for(int i = 0; i < k && end != null; i++){
                end = end.next;
            }
            //这里是没到K个的最后一组
            if(end == null){
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            //断掉待翻转 和 未反转部分
            end.next = null;
            pre.next = reverse(start);//返回头节点
            start.next = next;//链接到下一个翻转部分
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 题目：21 合并两个有序链表
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(-1);
        ListNode index = dummy;
        //创建两个指针
        ListNode index1 = list1;
        ListNode index2 = list2;

        while(index1 != null || index2 != null){
            while(index1 != null && index2 != null){
                if(index1.val <= index2.val){
                    index.next = index1;
                    index1 = index1.next;
                }else {
                    index.next = index2;
                    index2 = index2.next;
                }
                index = index.next;
            }
            if(index1 != null){
                while(index1 != null){
                    index.next = index1;
                    index1 = index1.next;
                    index = index.next;
                }
            }
            if(index2 != null){
                while(index2 != null){
                    index.next = index2;
                    index2 = index2.next;
                    index = index.next;
                }
            }
        }
        return dummy.next;
    }

    /**
     * 题目：141 环形链表
     */
    public static boolean hasCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * 题目：160 相交链表
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        //先计算长度
        ListNode indexA = headA;
        int lenA = 0;
        while(indexA != null){
            indexA = indexA.next;
            lenA++;
        }
        ListNode indexB = headB;
        int lenB = 0;
        while(indexB != null){
            indexB = indexB.next;
            lenB++;
        }

        //让A的长度小于B
        if(lenA > lenB){
            int tempLen = lenA;
            lenA = lenB;
            lenB = tempLen;
            ListNode tempNode = headA;
            headA = headB;
            headB = tempNode;
        }
        //让B先走
        indexB = headB;
        for(int i = 0; i < lenB - lenA; i++){
            indexB = indexB.next;
        }
        indexA = headA;
        while(indexA != null && indexB != null){
            if(indexA == indexB){
                return indexA;
            }
            indexA = indexA.next;
            indexB = indexB.next;
        }
        return null;
    }

    /**
     * 题目：92 反转链表II
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        //虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        for(int i = 0; i < left - 1; i++){
            pre = pre.next;
        }
        for(int i = 0; i < right; i++){
            end = end.next;
        }
        ListNode start = pre.next;
        ListNode next = end.next;

        end.next = null;
        pre.next = reverse2(start);
        start.next = next;

        return dummy.next;

    }

    public static ListNode reverse2(ListNode head){
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

}

class ListNode{

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
