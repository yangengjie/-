package com.ygj.map;

public class LinkedHashMap<K, V> extends HashMap<K, V> {

	private LinkedNode<K, V> first;
	private LinkedNode<K, V> last;

	@Override
	public void clear() {
		super.clear();
		first = null;
		last = null;
	}

	/**
	 * HashMap中的遍历是遍历哈希表取出每一个桶中的红黑树 然后再遍历红黑树中的每一个节点，对于LinkedHashMap来说完全可以直接遍历链表即可
	 */
	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (visitor == null)
			return;
		LinkedNode<K, V> node = first;
		while (node != null) {
			visitor.visitor(node.key, node.value, node.color);
			node = node.next;
		}
	}

	/**
	 * 添加元素时维护链表中元素的next和prev属性
	 */
	@Override
	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		LinkedNode<K, V> node = new LinkedNode(key, value, parent);
		if (first == null)
			first = last = node;
		else {
			last.next = node;
			node.prev = last;
			last = node;
		}
		return node;
	}

	@Override
	protected void afterRemove(Node<K, V> willNode, Node<K, V> node) {
		LinkedNode<K, V> node1 = (LinkedNode<K, V>) willNode;
		LinkedNode<K, V> node2 = (LinkedNode<K, V>) node;
		if (node1 != node2) {
			// 交换linkedWillNode和linkedRemovedNode在链表中的位置
			// 交换prev
			LinkedNode<K, V> temp = node1.prev;
			node1.prev = node2.prev;
			node2.prev = temp;

			if (node1.prev != null)
				node1.prev.next = node1;
			else
				first = node1;

			if (node2.prev != null)
				node2.prev.next = node2;
			else
				first = node2;

			// 交换next
			temp = node1.next;
			node1.next = node2.next;
			node2.next = temp;

			if (node1.next != null)
				node1.next.prev = node1;
			else
				last = node1;

			if (node2.next != null)
				node2.next.prev = node2;
			else
				last = node2;
		}

		LinkedNode<K, V> prev = node2.prev;
		LinkedNode<K, V> next = node2.next;
		if (prev != null)
			prev.next = next;
		else
			first = next;

		if (next != null)
			next.prev = prev;
		else
			last = next;
	}

	private static class LinkedNode<K, V> extends Node<K, V> {
		LinkedNode<K, V> prev;
		LinkedNode<K, V> next;

		public LinkedNode(K key, V value, Node<K, V> parent) {
			super(key, value, parent);
		}
	}

}
