package ����;

import java.util.Stack;

//https://leetcode-cn.com/problems/implement-queue-using-stacks/
public class _232_��ջʵ�ֶ��� {
	private Stack<Integer> inStack = new Stack<Integer>();
	private Stack<Integer> outStack = new Stack<Integer>();

	/** Initialize your data structure here. */
	public _232_��ջʵ�ֶ���() {

	}

	/** Push element x to the back of queue. */
	public void push(int x) {
		inStack.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		return outStack.pop();
	}

	/** Get the front element. */
	public int peek() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
		return outStack.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return outStack.isEmpty() && inStack.isEmpty();
	}
}
