package ����;

import java.util.LinkedList;
import java.util.Queue;

public class _225_�ö���ʵ��ջ {
	private Queue<Integer> queue;

	/** Initialize your data structure here. */
	public _225_�ö���ʵ��ջ() {
		queue = new LinkedList<Integer>();
	}

	/** Push element x onto stack. */
	public void push(int x) {
		int oldSize = queue.size();
		queue.offer(x);
		for (int i = 0; i < oldSize; i++)
			queue.offer(queue.poll());
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		return queue.poll();
	}

	/** Get the top element. */
	public int top() {
		return queue.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return queue.isEmpty();
	}
}
