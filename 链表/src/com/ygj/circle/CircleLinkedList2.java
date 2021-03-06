package com.ygj.circle;

import com.ygj.AbstractList;

/**
 * 双向循环链表:解决约瑟夫问题，增加如下几个方法： current用于指向某个节点 void reset()：让current指向头结点 E
 * next()：让current向后走一步 remove()：删除current指向的节点，并让current向后移动一步
 */
public class CircleLinkedList2<E> extends AbstractList<E> {
	private Node<E> first;
	private Node<E> last;
	private Node<E> current;

	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;

		public Node(Node<E> prev, E element, Node<E> next) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();

			sb.append(prev == null ? null : prev.element).append("_");
			sb.append(element).append("_");
			sb.append(next == null ? null : next.element);
			return sb.toString();
		}

	}

	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);
		if (index == size) {
			Node<E> newNode = new Node<>(last, element, first);
			if (size == 0) {
				first = newNode;
				last = newNode;
				newNode.prev = newNode;
				newNode.next = newNode;
			} else {
				last.next = newNode;
				last = newNode;
				first.prev = last;
			}
		} else {
			Node<E> nextNode = nodeIndex(index);
			Node<E> preNode = nextNode.prev;

			Node<E> newNode = new Node<>(preNode, element, nextNode);
			preNode.next = newNode;
			nextNode.prev = newNode;

			if (index == 0) // 在向头部添加元素
				first = newNode;
		}
		size++;
	}

	/**
	 * 查找位置为index的元素
	 */
	private Node<E> nodeIndex(int index) {
		checkIndex(index);
		Node<E> node = first;
		if (index <= size >> 1) {
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
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
		if (size == 1) {
			first = null;
			last = null;
		} else {
			oldNode = nodeIndex(index);
			Node<E> preNode = oldNode.prev;
			Node<E> nexNode = oldNode.next;
			preNode.next = nexNode;
			nexNode.prev = preNode;
			if (index == 0) {
				first = nexNode;
			} else if (index == size - 1) {
				last = preNode;
			}
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
		last = null;
		size = 0;
	}

	public void reset() {
		current = first;
	}

	public E next() {
		if (current == null)
			return null;
		current = current.next;
		return current.element;
	}

	public E remove() {
		if (current == null)
			return null;
		Node<E> nexNode = current.next;
		E element = remove(current.element);
		if (size == 0)
			current = null;
		else
			current = nexNode;
		return element;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size=" + size + " ，first=" + (first == null ? null : first.element) + " ,last="
				+ (last == null ? null : last.element) + " [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			sb.append(node);
			if (i != size - 1)
				sb.append(" , ");
			node = node.next;
		}
		sb.append("]");
		return sb.toString();
	}
}
