package leecode02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
