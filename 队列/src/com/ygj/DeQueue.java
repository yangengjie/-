package com.ygj;

import com.ygj.list.LinkedList;

public class DeQueue<E> {
	private LinkedList<E> list = new LinkedList<>();

	// Ԫ�ص�����
	public int size() {
		return list.size();
	}

	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// ���
	public void clear() {
		list.clear();
	}

	// �Ӷ�β���
	public void enQueueRear(E element) {
		list.add(element);
	}

	// �Ӷ�ͷ����
	public E deQueueFront() {
		return list.remove(0);
	}

	// �Ӷ�ͷ���
	public void enQueueFront(E element) {
		list.add(0, element);
	}

	// �Ӷ�β����
	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}

	// ��ȡ��ͷԪ��
	public E front() {
		return list.get(0);
	}

	// ��ȡ��βԪ��
	public E rear() {
		return list.get(list.size() - 1);
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
