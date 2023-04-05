package leecode;

import java.util.*;
import java.util.LinkedList;

public class StackAndQueue {

    public static void main(String[] args) {
        removeDuplicates("abbaca");
    }

    /**
     * 题目：20 有效的括号
     */
    //自己尝试做一下
    public static boolean isValid1(String s){
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            //如果是左括号，入栈
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else {//右括号
                //将右括号改成左括号
                char c2 = changeToLeft(c);
                //如果和栈顶的符号相同 就弹出 否则false
                if(stack.isEmpty() || c2 != stack.peek()){
                    return false;
                }else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public static char changeToLeft(char c){
        char s;
        if(c == ')'){
            s = '(';
        }else if(c == ']'){
            s = '[';
        }else {
            s = '{';
        }
        return s;
    }

    public static boolean isValid2(String s){
        Deque<Character> stack = new LinkedList<>();
        char ch;
        for(int i = 0; i < s.length(); i++){
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if(ch == '('){
                stack.push(')');
            }else if(ch == '['){
                stack.push(']');
            }else if(ch == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || ch != stack.peek()){
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 题目：1047 删除字符串中的所有相邻重复项
     */
    //使用栈
    public static String removeDuplicates(String s){
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        //参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        Deque<Character> stack = new ArrayDeque<>();
        char ch;
        for(int i = 0; i < s.length(); i++){
            ch = s.charAt(i);
            //栈中没有相同元素，入栈
            if(stack.isEmpty() || stack.peek() != ch){
                stack.push(ch);
            }else {//弹栈
                stack.pop();
            }
        }
        String str = "";
        //剩余的元素即为不重复的元素
        while (!stack.isEmpty()){
            str = stack.pop() + str;
        }

        return str;
    }

    /**
     * 题目：150 逆波兰表达式求值
     */
    public static int evalRPN(String[] tokens){
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if(s.equals("+")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            }else if(s.equals("*")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            }else if(s.equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            }else if(s.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            }else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    /**
     * 题目：239 滑动窗口最大值
     */
    //使用代码随想录的思路
    //解法一 自定义数组
    public static int[] maxSlidingWindow(int[] nums, int k){
        if(nums.length == 1){
            return nums;
        }
        int len = nums.length - k + 1;
        //存放结果元素的数组
        int[] res = new int[len];
        int num = 0;
        //自定义队列
        MyQueue1 myQueue1 = new MyQueue1();
        //先将前k的元素放入队列
        for(int i = 0; i < k; i++){
            myQueue1.add(nums[i]);
        }
        res[num++] = myQueue1.peek();
        for(int i = k; i < nums.length; i++){
            //滑动窗口移除对前面的元素，移除时判断该元素是否放入队列
            myQueue1.poll(nums[i - k]);
            //滑动窗口加入最后面的元素
            myQueue1.add(nums[i]);
            //记录对应的最大值
            res[num++] = myQueue1.peek();
        }
        return res;
    }

    /**
     * 题目：347 前K个高频元素
     *
     */
    //超出时间限制
    public static int[] topKFrequent1(int[] nums, int k){
        List<Integer> res = new ArrayList<>();
        //key 数值 value 次数
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        int index = 0;
        while(index < nums.length){
            int num = nums[index];
            if(cnt.get(num) >= k){
                res.add(num);
            }
            while(nums[index] == num && index + 1 < nums.length){
                index++;
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    //基于大顶堆实现
    public static int[] topKFrequent2(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();//key为数组元素值 val为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //在优先队列中存储二元组（num，cnt），cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序时从大到小排，出现次数最多的在队头（相当于大顶堆）
        //优先级队列 默认是从pair1 - pair2 形成小顶堆（不用比较器 默认是进行升序排列）
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){//依次从队头弹出k个，就是出现频率前k高的元素
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}

/**
 * 题目：232 用栈实现队列
 */
class MyQueue{
    //创建一个输入栈 一个输出栈
    Deque<Integer> stackIn;
    Deque<Integer> stackOut;

    //初试数据方法
    public MyQueue(){
        stackIn = new ArrayDeque<>();//负责进栈
        stackOut = new ArrayDeque<>();//负责出栈
    }

    //将元素x堆到队列的后面
    public void push(int x){
        stackIn.push(x);
    }

    //从队列前面移除元素并返回该元素
    public int pop(){
        dumpstackIn();
        return stackOut.pop();
    }

    //得到前面的元素
    public int peek(){
        dumpstackIn();
        return stackOut.peek();
    }

    //判断队列是否为空
    public boolean empty(){
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    //如果stackOut为空，那么将stackIn中的元素全部放到stackOut
    private void dumpstackIn(){
        if(!stackOut.isEmpty()){
            return;
        }
        while(!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }
}

class MyQueue1{
    Deque<Integer> que = new LinkedList<>();
    //弹出元素时，比较当前要弹出的数值时候等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空
    public void poll(int val){
        if(!que.isEmpty() && val == que.peek()){
            que.poll();
        }
    }
    //添加元素是，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    public void add(int val){
        while(!que.isEmpty() && val > que.getLast()){
            que.removeLast();
        }
        que.add(val);
    }
    //队列对顶元素始终为最大值
    public int peek(){
        return que.peek();
    }
}

/**
 * 题目：225 用队列实现栈
 */
//使用两个Deque实现
//使用双端队列
class MyStack1{
    Deque<Integer> que1;//和栈中保持一样元素的队列
    Deque<Integer> que2;//辅助队列

    //初试化
    public MyStack1(){
        que1 = new ArrayDeque<>();
        que2 = new ArrayDeque<>();
    }

    //入栈
    public void push(int x){
        que1.addLast(x);
    }

    //弹栈并返回所弹栈帧
    public int pop(){
        int size = que1.size();
        size--;//留一个
        for(int i = 0; i < size; i++){
            que2.addLast(que1.pollFirst());
        }
        int res = que1.pollFirst();
        //交换que1和que2 把每次弹栈后的数据给que1
        que1 = que2;
        que2 = new ArrayDeque<>();
        return res;
    }

    public int top(){
        return que1.peekLast();
    }

    public boolean empty(){
        return que1.isEmpty();
    }
}

//使用一个queue进行实现
//自创
class MyStack2{

    Queue<Integer> que;

    public MyStack2(){
        que = new LinkedList<>();
    }

    public void push(int x){
        que.add(x);
    }

    public int pop(){
        int size = que.size();
        size--;
        for(int i = 0; i < size; i++){
            que.add(que.poll());
        }
        int res = que.poll();
        return res;
    }

    public int top(){
        int size = que.size();
        size--;
        for(int i = 0; i < size; i++){
            que.add(que.poll());
        }
        int res = que.peek();
        que.add(que.poll());
        return res;
    }

    public boolean empty(){
        return que.isEmpty();
    }
}

