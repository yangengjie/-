package com.ygj;

import com.ygj.list.ArrayList;
import com.ygj.list.List;

public class Stack<E> {
	private List<E> list = new ArrayList<>();

	// Ԫ�ص�����
	public int size() {
		return list.size();
	}

	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// ��ջ
	public void push(E element) {
		list.add(element);
	}

	// ��ջ
	public E pop() {
		return list.remove(list.size() - 1);
	}

	// ��ȡջ��Ԫ��
	public E top() {
		return list.get(list.size() - 1);
	}

	// ���Ԫ��
	public void clear() {
		list.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size=" + size()+" ");
		while (!isEmpty()) {
			sb.append(pop()).append(",");
		}
		return sb.toString();
	}

}
