package leecode;

public class LinkedList {

    /**
     * 题目：203 移除链表元素
     */
    public static ListNode removeElements(ListNode head, int val){
        if(head == null){
            return head;
        }

        //添加虚拟节点
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 题目：206 反转链表
     */
    public static ListNode reverseList(ListNode head){
        //创建虚拟头节点
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            //变换
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 题目：24 两两交换链表中的节点
     */
    public ListNode swapPairs(ListNode head){
        //创建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null && cur.next != null && cur.next.next != null){
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
     * 题目：19 删除链表的倒数第N个节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
        //双指针 到要删除节点的前一个节点上
        //创建虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //创建双指针
        ListNode slow = dummy;
        ListNode fast = dummy;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        //使用slow节点进行删除
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 题目：面试题 02.07. 链表相交
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        //计算两个链表的长度
        int countA = 0;
        int countB = 0;
        ListNode temp = headA;
        while(temp != null){
            temp = temp.next;
            countA++;
        }
        temp = headB;
        while(temp != null){
            temp = temp.next;
            countB++;
        }
        int n = countA - countB;
        if(n < 0){
            //使A大于B
            n = -n;
            temp = headA;
            headA = headB;
            headB = temp;
        }
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        for(int i = 0; i < n; i++){
            temp1 = temp1.next;
        }

        while(temp1 != null){
            if(temp1 == temp2){
                return temp1.next;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return null;
    }

    /**
     * 题目：142 环形链表II
     */
    public static ListNode detectCycle(ListNode head){
        //快慢节点相遇
        ListNode slowIndex = head;
        ListNode fastIndex = head;
        while (fastIndex != null && fastIndex.next != null){
            slowIndex = slowIndex.next;
            fastIndex = fastIndex.next.next;

            //证明有环形链
            if(slowIndex == fastIndex){
                ListNode index1 = fastIndex;
                ListNode index2 = head;
                while(index1 != index2){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }

        return null;
    }


}

/**
 * 题目：707 设计链表
 */
//单向链表
class MyLinkedList1{

    //size存储链表元素的个数
    int size;
    //虚拟头节点
    ListNode head;

    //初始化链表
    public MyLinkedList1(){
        size = 0;
        head = new ListNode(0);
    }

    //获取第index个节点的值，没有返回-1
    public int get(int index){
        //判断
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode current = head;
        //多了一个虚拟节点，所以遍历index + 1个
        for(int i = 0; i <= index; i++){
            current = current.next;
        }
        return current.val;
    }

    //在链表的第一个元素之前添加val节点
    public void addAtHead(int val){
        addAtIndex(0, val);
    }

    //在链表最后添加val值的节点
    public void addAtTail(int val){
        addAtIndex(size, val);
    }

    //在第index个节点之前添加val节点
    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val){
        //大于链表长度， 返回空
        if(index > size){
            return;
        }
        //小于链表长度，插入在头节点
        if(index < 0){
            index = 0;
        }

        ListNode preNode = head;
        //遍历到要添加节点的前一个节点
        for(int i = 0; i < index; i++){
            preNode = preNode.next;
        }
        //创建添加节点
        ListNode toAdd = new ListNode(val);
        toAdd.next = preNode.next;
        preNode.next = toAdd;
        size++;
    }

    //若index有效，删除第index节点
    public void deleteAtIndex(int index){
        if(index < 0 || index >= size){
            return;
        }
        //头节点删除
        if(index == 0){
            //直接将虚拟节点变成虚拟头节点
            head = head.next;
            return;
        }
        ListNode preNode = head;
        for(int i = 0; i < index; i++){
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        size--;
    }
}

//双向链表
class MyLinkedList2{
    int size;
    //记录链表的虚拟头节点和虚拟尾节点
    ListNode head;
    ListNode tail;

    //初始化
    public MyLinkedList2(){
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        //头尾相连
        head.next = tail;
        tail.prev = head;
    }

    //获取第index个节点的值，没有返回-1
    public int get(int index){
        //判断
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode cur = head;
        //判断哪一边遍历最短
        if(index >= size / 2){
            //从尾进行遍历
            cur = tail;
            for(int i = 0; i < size- index; i++){
                cur = cur.prev;
            }
        }else {
            for(int i = 0; i <= index; i++){
                cur = cur.next;
            }
        }
        return cur.val;
    }

    //在链表的第一个元素之前添加val节点
    public void addAtHead(int val){
        //等价于在第0个元素前添加
        addAtIndex(0, val);
    }

    //在链表最后添加val值的节点
    public void addAtTail(int val){
        //等价于在最后一个元素前添加
        addAtIndex(size, val);
    }

    //在第index个节点之前添加val节点
    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val){
        //index大于链表长度
        if(index > size){
            return;
        }
        //index小于0
        if(index < 0){
            index = 0;
        }
        size++;
        ListNode preNode = head;
        for(int i = 0; i < index; i++){
            preNode = preNode.next;
        }
        //添加节点
        ListNode toAdd = new ListNode(val);
        toAdd.next = preNode.next;
        preNode.next.prev = toAdd;
        preNode.next = toAdd;
        toAdd.prev = preNode;
    }

    //若index有效，删除第index节点
    public void deleteAtIndex(int index){
        //判断索引是否有效
        if(index < 0 || index >= size){
            return;
        }
        //删除
        size--;
        ListNode preNode = head;
        for(int i = 0; i < index; i++){
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        preNode.next.prev = preNode;
    }

}

class ListNode{
    int val;
    ListNode next;
    ListNode prev;

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
