package com.ygj.circle;

import javax.lang.model.element.Element;

import com.ygj.AbstractList;

/**
 * 单向循环链表
 * @author glen
 *
 * @param <E>
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {

	private Node<E> first;

	private static class Node<E> {
		private E element;
		private Node<E> next;

		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		if (index == 0) {
			if (size == 0) {
				first = new Node<>(element, null);
				first.next = first;
			} else {
				Node<E> lastNode = nodeIndex(size - 1);
				first = new Node<E>(element, first);
				lastNode.next = first;
			}
		} else {
			Node<E> preNode = nodeIndex(index - 1);
			preNode.next = new Node<>(element, preNode.next);
		}

		size++;
	}

	/**
	 * 查找位置为index的元素
	 */
	private Node<E> nodeIndex(int index) {
		checkIndex(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public E get(int index) {
		Node<E> node = nodeIndex(index);
		return node.element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = nodeIndex(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		Node<E> oldNode = first;
		if (index == 0) {
			if (size == 1) {
				first = null;
			} else {
				first = first.next;
				Node<E> lastNode = nodeIndex(size - 1);
				lastNode.next = first;
			}
		} else {
			Node<E> preNode = nodeIndex(index - 1);
			oldNode = preNode.next;
			preNode.next = preNode.next.next;
		}
		size--;
		return oldNode.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null)
					return i;
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element))
					return i;
				node = node.next;
			}
		}
		return -1;
	}

	@Override
	public void clear() {
		first = null;
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size=" + size + " [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			sb.append(node.element).append("_");
			sb.append(node.next.element);
			if (i != size - 1)
				sb.append(" , ");
			node = node.next;
		}
		sb.append("]");
		return sb.toString();
	}

}
