package com.ygj;

/**
 * 双向链表
 * 
 * @author glen
 *
 */
public class LinkedList<E> extends AbstractList<E> {
	private Node<E> first;
	private Node<E> last;

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
		// 向末尾添加元素
		// 当链表为空时 index==size==0,
		if (index == size) {
			Node<E> oldLast = last;
			last = new Node<>(oldLast, element, null);
			// 链表为空时oldLast为null
			if (oldLast == null) {
				first = last;
			} else {
				oldLast.next = last;
			}
		} else {
			Node<E> nextNode = nodeIndex(index);
			Node<E> preNode = nextNode.prev;
			Node<E> node = new Node<>(preNode, element, nextNode);
			// 等价于index==0，向链表头添加元素
			if (preNode == null) {
				first = node;
			} else {
				preNode.next = node;
			}
			nextNode.prev = node;
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
		Node<E> oldNode = nodeIndex(index);
		Node<E> preNode = oldNode.prev;
		Node<E> nexNode = oldNode.next;
		// index==0时
		if (preNode == null) {
			first = nexNode;
		} else {
			preNode.next = nexNode;
		}
		// index==size-1
		if (nexNode == null) {
			last = preNode;
		} else {
			nexNode.prev = preNode;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("size=" + size + " [");
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
