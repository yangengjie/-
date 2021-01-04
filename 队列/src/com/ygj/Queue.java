package com.ygj;

import com.ygj.list.LinkedList;

public class Queue<E> {
	private LinkedList<E> list = new LinkedList<>();

	// 元素的数量
	public int size() {
		return list.size();
	}

	// 是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// 清空
	public void clear() {
		list.clear();
	}

	// 队尾入队
	public void enQueue(E element) {
		list.add(element);
	}

	// 队头出队
	public E deQueue() {
		return list.remove(0);
	}

	// 获取队首元素
	public E front() {
		return list.get(0);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		while (!isEmpty()) {
			sb.append(deQueue()).append(",");
		}
		return sb.toString();
	}
}
