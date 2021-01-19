package com.ygj.list;

public abstract class AbstractList<E> implements List<E> {
	protected int size;

	/**
	 * @return 返回数组中元素的个数
	 */
	public int size() {
		return size;
	}

	/**
	 * @return 数组是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param element
	 * @return 是否包含元素element
	 */
	public boolean contains(E element) {
		return indexOf(element) != -1;
	}

	/**
	 * 添加元素
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
	 * 删除元素
	 * 
	 * @param element
	 * @return
	 */
	public E remove(E element) {
		return remove(indexOf(element));
	}

}
