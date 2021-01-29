package com.ygj.queue;

import java.util.Comparator;

import com.ygj.heap.BinaryHeap;

public class PriorityQueue<E> {
	private BinaryHeap<E> heap;

	public PriorityQueue() {
		this(null);
	}

	public PriorityQueue(Comparator<E> comparator) {
		heap = new BinaryHeap<>(comparator);
	}

	// 元素的数量
	public int size() {
		return heap.size();
	}

	// 是否为空
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	// 清空
	public void clear() {
		heap.clear();
	}

	// 队尾入队
	public void enQueue(E element) {
		heap.add(element);
	}

	// 队头出队
	public E deQueue() {
		return heap.remove();
	}

	// 获取队首元素
	public E front() {
		return heap.get();
	}
}
