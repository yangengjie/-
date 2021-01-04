package com.ygj.circle;

public class CircleQueue<E> {
	private int size;
	private E[] elements = (E[]) new Object[5];
	private int front;

	// Ԫ�ص�����
	public int size() {
		return size;
	}

	// �Ƿ�Ϊ��
	public boolean isEmpty() {
		return size == 0;
	}

	// ���
	public void clear() {
		for (E element : elements)
			element = null;
		size = 0;
		front = 0;
	}

	// ��β���
	public void enQueue(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}

	// ��ͷ����
	public E deQueue() {
		E oldElement = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return oldElement;
	}

	// ��ȡ����Ԫ��
	public E front() {
		return elements[front];
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (capacity >= oldCapacity) {
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			System.out.println("oldCapacity = " + oldCapacity + "  ����Ϊ��" + newCapacity);
			E[] newElements = (E[]) new Object[newCapacity];
			for (int i = 0; i < oldCapacity; i++) {
				newElements[i] = elements[index(i)];
			}
			elements = newElements;
			front = 0;
		}

	}

	private int index(int index) {
		return (index + front) % elements.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size=" + size + " front= " + front).append(" [");
		for (E e : elements) {
			sb.append(" " + e).append(",");
		}
		sb.append("]");
		return sb.toString();
	}
}
