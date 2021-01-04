package com.ygj.circle;

public class CircleDeque<E> {
	private int size;
	private E[] elements = (E[]) new Object[10];
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
		for (E e : elements) {
			e = null;
		}
		size = 0;
		front = 0;
	}

	// �Ӷ�β���
	public void enQueueRear(E element) {
		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;
	}

	// �Ӷ�ͷ����
	public E deQueueFront() {
		E oldElement = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return oldElement;
	}

	// �Ӷ�ͷ���
	public void enQueueFront(E element) {
		ensureCapacity(size + 1);
		front = index(-1);
		elements[front] = element;
		size++;
	}

	// �Ӷ�β����
	public E deQueueRear() {
		E oldElement = elements[index(size - 1)];
		elements[index(size - 1)] = null;
		size--;
		return oldElement;
	}

	// ��ȡ��ͷԪ��
	public E front() {
		return elements[front];
	}

	// ��ȡ��βԪ��
	public E rear() {
		return elements[index(size - 1)];
	}

	private int index(int index) {
		index += front;
		if (index < 0)
			return index + elements.length;
//		return index % elements.length;
		// index + front�������elements.length��2�������Ż�
		return index - (index >= elements.length ? elements.length : 0);
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (capacity >= oldCapacity) {
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			E[] newElements = (E[]) new Object[newCapacity];
			for (int i = 0; i < oldCapacity; i++) {
				newElements[i] = elements[index(i)];
			}
			elements = newElements;
			front = 0;
		}
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
