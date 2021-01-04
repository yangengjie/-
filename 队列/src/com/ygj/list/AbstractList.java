package com.ygj.list;

public abstract class AbstractList<E> implements List<E> {
	protected int size;

	/**
	 * @return ����������Ԫ�صĸ���
	 */
	public int size() {
		return size;
	}

	/**
	 * @return �����Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param element
	 * @return �Ƿ����Ԫ��element
	 */
	public boolean contains(E element) {
		return indexOf(element) != -1;
	}

	/**
	 * ���Ԫ��
	 * 
	 * @param element
	 */
	public void add(E element) {
		add(size, element);
	}

	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index is " + index + " size is " + size);
	}

	protected void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index is " + index + " size is " + size);
	}

	/**
	 * ɾ��Ԫ��
	 * 
	 * @param element
	 * @return
	 */
	public E remove(E element) {
		return remove(indexOf(element));
	}

}
