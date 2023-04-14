package leecode02;

import java.util.*;
import java.util.LinkedList;

public class StackAndQueue {

    /**
     * 题目：20 有效的括号
     */
    public static boolean isValid(String s){
        if(s.length() % 2 == 1){
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(')');
            }
            if(c == '['){
                stack.push(']');
            }
            if(c == '{'){
                stack.push('}');
            }
            if(c == ')' || c == ']' || c == '}'){
                if(!stack.isEmpty()){
                    char c1 = stack.peek();
                    if(c1 == c){
                        stack.pop();
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 题目：1047 删除字符串中的所有相邻重复项
     */
    //使用栈
    public static String removeDuplicates(String s){
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(stack.isEmpty() || stack.peek() != ch){
                stack.push(ch);
            }else {
                stack.pop();
            }
        }
        String str = "";
        while(!stack.isEmpty()){
            str = stack.pop() + str;
        }

        return str;
    }

    //用字符串直接作为栈
    public static String removeDuplicates2(String s){
        //将res做栈
        StringBuilder res = new StringBuilder();
        //top作为res的长度
        int top = -1;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(top >= 0 && res.charAt(top) == ch){
                res.deleteCharAt(top);
                top--;
            }else {
                res.append(ch);
                top++;
            }
        }
        return res.toString();
    }

    //双指针
    public static String removeDuplicates3(String s){
        char[] chars = s.toCharArray();
        int fastIndex = 0;
        int slowIndex = 0;
        while(fastIndex < s.length()){
            chars[slowIndex] = chars[fastIndex];
            if(slowIndex > 0 && chars[slowIndex] == chars[slowIndex - 1]){
                slowIndex--;
            }else {
                slowIndex++;
            }
            fastIndex++;
        }

        return new String(chars, 0, slowIndex);
    }

    /**
     * 题目：150 逆波兰表达式求值
     */
    public static int evalRPN(String[] tokens){
        //使用栈
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; i++){
            String s = tokens[i];
            if(s.equals("+")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            }else if(s.equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            }else if(s.equals("*")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            }else if(s.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            }else {
                stack.push(Integer.valueOf(s));//注意
            }
        }
        return stack.pop();

    }

    /**
     * 题目：239 滑动窗口最大值
     */
    //单调栈？
    //使用双端队列
    public static int[] maxSlidingWindow(int[] nums, int k){
        Deque<Integer> deque = new ArrayDeque<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int index = 0;
        for(int i = 0; i < len; i++){
            // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点
            // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
            while(!deque.isEmpty() && deque.peek() < i - k + 1){
                deque.pollFirst();
            }
            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }

            deque.offer(i);
            // 因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头节点放入结果就行了
            if(i >= k - 1){
                res[index++] = nums[deque.peek()];
            }
        }
        return res;
    }

    /**
     * 题目：347 前K个高频元素
     */
    public static int[] topKFrequent(int[] nums, int k){
        //key 为数组元素值 val 为对应出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //在优先队列中存储二元组（num，cnt），cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序时从大到小排，出现次数最多的在队头（相当于大顶堆）
        //优先级队列 默认是从pair1 - pair2 形成小顶堆（不用比较器 默认是进行升序排列）
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){//大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){
            ans[i] = pq.poll()[0];
        }
        return ans;
    }


}

/**
 * 题目：232 用栈实现队列
 */
class MyQueue {

    Deque<Integer> stackIn;
    Deque<Integer> stackOut;

    //初始化
    public MyQueue() {
        stackIn = new ArrayDeque<>();
        stackOut = new ArrayDeque<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        //将进栈的元素全部放到出栈上
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

/**
 * 题目：225 用队列实现栈
 */
//使用双端
class MyStack {

    Queue<Integer> que;

    //初始化
    public MyStack() {
        que = new LinkedList<>();
    }

    public void push(int x) {
        que.add(x);
    }

    public int pop() {
        //把队列中的数据吐出去 再吞下
        int len = que.size();
        for(int i = 0; i < len - 1; i++){
            que.add(que.poll());
        }
        return que.poll();
    }

    public int top() {
        int len = que.size();
        for(int i = 0; i < len - 1; i++){
            que.add(que.poll());
        }
        int res = que.peek();
        que.add(que.poll());
        return res;
    }

    public boolean empty() {
        return que.isEmpty();
    }
}
