package com.ygj;

import com.ygj.list.LinkedList;

public class Queue<E> {
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

	// ��β���
	public void enQueue(E element) {
		list.add(element);
	}

	// ��ͷ����
	public E deQueue() {
		return list.remove(0);
	}

	// ��ȡ����Ԫ��
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
