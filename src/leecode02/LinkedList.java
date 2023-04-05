package leecode02;


import java.util.List;

public class LinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(7);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        removeElement(node1, 7);
    }

    /**
     * 题目：203 移除链表元素
     */
    public static ListNode removeElement(ListNode head, int val){
        //移除元素 创建虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //创建指针
        ListNode index = dummy;
        while(index != null && index.next != null){
            //找到目标节点的前一个位置
            if(index.next != null && index.next.val == val){
                index.next = index.next.next;
            }else {
                index = index.next;
            }

        }
        return dummy.next;
    }

    /**
     * 题目：206 反转链表
     */
    public static ListNode reverseList(ListNode head){
        //使用双指针
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;

        while(cur.next != null && cur.next.next != null){
            //要按照结果的箭头顺序一个一个连接 &-> 2 -> 1 -> 3
            //记录临时节点
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
     * 题目：19 删除链表中的倒数第N个节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
        //快慢双指针 快指针先走
        //创建虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fastIndex = dummy;
        ListNode slowIndex = dummy;

        //快指针先走
        for(int i = 0; i < n; i++){
            fastIndex = fastIndex.next;
        }
        //一起走
        while(fastIndex.next != null){
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next;
        }
        //删除
        slowIndex.next = slowIndex.next.next;
        return dummy.next;
    }

    /**
     * 题目：面试题 02.07 链表相交
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        //计算两个链表的长度
        int lenA = 0;
        int lenB = 0;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while(nodeA != null){
            lenA++;
            nodeA = nodeA.next;
        }
        while(nodeB != null){
            lenB++;
            nodeB = nodeB.next;
        }
        //将lena 和 lenb进行调整 使得a<b
        if(lenA > lenB){
            int tempLen = lenA;
            ListNode tempNode = headA;
            lenA = lenB;
            lenB = tempLen;
            headA = headB;
            headB = tempNode;
        }
        //调整指针位置
        nodeA = headA;
        nodeB = headB;
        //B先走
        for(int i = 0; i < lenB - lenA; i++){
            nodeB = nodeB.next;
        }
        //一起走
        while(nodeA != null){
            if(nodeA == nodeB){
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }

    /**
     * 题目：142 环形链表II
     */
    public static ListNode detectCycle(ListNode head){
        //快慢指针追逐
        ListNode slowIndex = head;
        ListNode fastIndex = head;
        while(fastIndex != null && fastIndex.next != null){
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;
            //有环形
            if(slowIndex == fastIndex){
                ListNode index = head;
                while(index != slowIndex){
                    index = index.next;
                    slowIndex = slowIndex.next;
                }
                return index;
            }
        }
        return null;
    }
}

class MyLinkedList{
    //size存储表元素的个数
    int size;
    //虚拟头节点
    ListNode head;

    //初始化
    public MyLinkedList(){
        size = 0;
        head = new ListNode();
    }

    public int get(int index){
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode current = head;
        for(int i = 0; i <= index; i++){
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val){
        addAtIndex(0, val);
    }

    public void addAtTail(int val){
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size){
            return;
        }
        if(index < 0){
            index = 0;
        }
        ListNode cur = head;
        //找到插入节点的前一个节点
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        //创建新节点
        ListNode node = new ListNode(val);
        node.next = cur.next;
        cur.next = node;
        size++;

    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        size--;
        if(index == 0){
            head = head.next;
            return;
        }
        ListNode cur = head;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;

    }
}

/**
 * 构造节点
 */
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
