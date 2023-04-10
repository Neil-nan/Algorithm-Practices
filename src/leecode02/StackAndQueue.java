package leecode02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class StackAndQueue {
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
