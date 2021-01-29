package com.ygj;

import java.util.Comparator;

import com.ygj.printer.BinaryTreeInfo;

/**
 * 二叉堆 ，默认为最大堆
 * 
 * @param <E>
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
	private int size;
	private Comparator<E> comparator;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;

	public BinaryHeap() {
		this(null, null);
	}

	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}

	public BinaryHeap(E[] elements) {
		this(elements, null);
	}

	public BinaryHeap(E[] elements, Comparator<E> comparator) {
		this.comparator = comparator;
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			size = elements.length;
			int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				this.elements[i] = elements[i];
			}
			heapify1();
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementEmptyCheck(element);
		ensureCapacity(size + 1);
		// 1、先添加到数组的最后
		elements[size] = element;
		siftUp(size);
		size++;
	}

	/**
	 * 获取堆顶元素
	 */
	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	/**
	 * 删除堆顶元素
	 */
	@Override
	public E remove() {
		emptyCheck();
		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[size];
		elements[size] = null;
		siftDown(0);
		return root;
	}

	/**
	 * 删除堆顶元素，并添加新元素
	 */
	@Override
	public E replace(E element) {
		elementEmptyCheck(element);
		E root = null;
		if (size == 0) {
			elements[0] = element;
			size++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}

	/**
	 * 自上而下的上滤
	 */
	public void heapify1() {
		for (int i = 1; i < size; i++) {
			siftUp(i);
		}
	}

	/**
	 * 自下而上的下滤
	 */
	public void heapify2() {
		for (int i = ((size >> 1) - 1); i >= 0; i--) {
			siftDown(i);
		}
	}

	/**
	 * 让指定位置的元素下滤
	 * 
	 * @param index
	 */
	private void siftDown(int index) {
		E element = elements[index];
		// 退出没有子节点或者指定位置的值大于子节点的值，二叉堆的逻辑结构是完全二叉树
		// 所以叶子节点的个数为n/2
		int half = size >> 1;
		while (index < half) {
			// 完全二叉树如果有一个子节点肯定是左子节点
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];

			// 右子节点的位置
			int rightIndex = childIndex + 1;
			// 选出左右子节点的最大节点
			if (rightIndex < size && compare(elements[rightIndex], child) > 0)
				child = elements[childIndex = rightIndex];
			if (compare(element, child) >= 0)
				break;
			// 将子节点移动到index位置
			elements[index] = child;
			// 给index重新赋值
			index = childIndex;
		}
		elements[index]=element;

	}

	/**
	 * 上滤指定位置的元素
	 * 
	 * @param index
	 */
	private void siftUp(int index) {
//		E element = elements[index];
//		// index>0表示有父节点
//		while (index > 0) {
//			int parentIndex = (index - 1) >> 1;
//			E parentElement = elements[parentIndex];
//			if (compare(element, parentElement) <= 0)
//				break;
//			E temp = elements[index];
//			elements[index] = elements[parentIndex];
//			elements[parentIndex] = temp;
//			// 重新赋值index
//			index = parentIndex;
//		}

		E element = elements[index];
		// index > 0 表示有父节点
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parentElement = elements[parentIndex];
			if (compare(element, parentElement) <= 0)
				break;
			// 将父元素存储在index位置
			elements[index] = parentElement;
			// 将父元素index赋值给
			index = parentIndex;
		}
		elements[index] = element;
	}

	private int compare(E e1, E e2) {
		if (comparator != null)
			return comparator.compare(e1, e2);
		return ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * 扩容
	 * 
	 * @param size
	 */
	private void ensureCapacity(int size) {
		int oldLength = elements.length;
		if (size >= oldLength) {
			E[] oldElements = elements;
			elements = (E[]) new Object[oldLength + oldLength >> 1];
			for (int i = 0; i < oldLength; i++) {
				elements[i] = oldElements[i];
			}
		}
	}

	private void elementEmptyCheck(E element) {
		if (element == null)
			throw new IllegalArgumentException("element must not be null");
	}

	private void emptyCheck() {
		if (size == 0)
			throw new IndexOutOfBoundsException("step is empty");
	}

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int) node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int) node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int) node];
	}
}
