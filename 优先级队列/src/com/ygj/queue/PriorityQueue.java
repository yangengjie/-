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

	// Ԫ�ص�����
	public int size() {
		return heap.size();
	}

	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	// ���
	public void clear() {
		heap.clear();
	}

	// ��β���
	public void enQueue(E element) {
		heap.add(element);
	}

	// ��ͷ����
	public E deQueue() {
		return heap.remove();
	}

	// ��ȡ����Ԫ��
	public E front() {
		return heap.get();
	}
}
