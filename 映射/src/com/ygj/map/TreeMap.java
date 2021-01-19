package com.ygj.map;

import java.awt.Color;
import java.lang.ProcessBuilder.Redirect;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.ygj.printer.BinaryTreeInfo;

public class TreeMap<K, V> implements Map<K, V> {

	private int size;
	private final static boolean RED = true;
	private final static boolean BLACK = false;
	private Node<K, V> root;

	private Comparator<K> comparator;

	public TreeMap() {

	}

	public TreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	private static class Node<K, V> {
		K key;
		V value;
		// 新添加的节点默认为RED
		boolean color = RED;

		Node<K, V> parent;
		Node<K, V> left;
		Node<K, V> right;

		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}

		public boolean isLeftChild() {
			return parent != null && this.equals(parent.left);
		}

		public boolean isRightChild() {
			return parent != null && this.equals(parent.right);
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {
			return left != null && right != null;
		}

		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			else if (isRightChild()) {
				return parent.left;
			}
			return null;
		}

	}

	private void keyNotNullCheck(K key) {
		if (key == null)
			throw new IllegalArgumentException("key must not be null");
	}

	private int compare(K k1, K k2) {
		if (comparator != null)
			return comparator.compare(k1, k2);
		return ((Comparable<K>) k1).compareTo(k2);
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
		size = 0;
		root = null;
	}

	@Override
	public V put(K key, V value) {
		keyNotNullCheck(key);

		if (root == null) { // 添加的第一个节点
			root = new Node<>(key, value, null);
			size++;

			afterPut(root);
			return null;
		}

		int cmp = 0;
		Node<K, V> node = root;
		Node<K, V> parent = root;
		while (node != null) {
			cmp = compare(key, node.key);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				V oldValue = node.value;
				node.key = key;
				node.value = value;
				return oldValue;
			}
		}

		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		afterPut(newNode);
		size++;
		return null;
	}

	/**
	 * //用于添加后的修复
	 * 
	 * @param node
	 */
	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		if (parent == null) { // 根节点
			black(node);
			return;
		}
		// 添加有12种
		// 4种parent为黑色，不需处理
		if (isBlack(parent))
			return;

		// 来到这里parent是RED，判断uncle节点是否红色
		Node<K, V> sibling = parent.sibling();
		Node<K, V> grand = parent.parent;
		if (isRed(sibling)) { // uncle节点为红色
			// 将parent、uncle染成黑色
			black(parent);
			black(sibling);
			// 将grand染成红色作为新添加的节点
			grand = red(grand);
			afterPut(grand);
			return;
		}

		// 来到这里uncle节点为黑色
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
				red(grand);
				rotateRight(grand);
			} else { // LR
				black(node);
				red(grand);
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				black(node);
				red(grand);
				rotateRight(parent);
				rotateLeft(grand);
			} else { // RR
				black(parent);
				red(grand);
				rotateLeft(grand);
			}
		}

	}

	private void rotateLeft(Node<K, V> grand) { // RR
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;

		grand.right = child;
		parent.left = grand;

		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}

		grand.parent = parent;
		if (child != null)
			child.parent = grand;
	}

	private void rotateRight(Node<K, V> grand) { // LL
		Node<K, V> parent = grand.left;
		Node<K, V> child = parent.right;

		grand.left = child;
		parent.right = grand;

		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}

		grand.parent = parent;
		if (child != null)
			child.parent = grand;
	}

	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}

	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}

	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}

	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}

	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}

	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node != null)
			node.color = color;
		return node;
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node == null ? null : node.value;
	}

	/**
	 * 通过key查找元素
	 * 
	 * @param key
	 * @return
	 */
	private Node<K, V> node(K key) {
		Node<K, V> node = root;
		while (node != null) {
			int cmp = compare(key, node.key);
			if (cmp == 0)
				return node;
			if (cmp > 0)
				node = node.right;
			else
				node = node.left;
		}
		return null;
	}

	@Override
	public V remove(K key) {
		keyNotNullCheck(key);
		if (root == null)
			return null;

		Node<K, V> node = node(key);
		Node<K, V> oldNode = node;
		if (node == null)
			return null;

		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到前驱节点后继节点 然后覆盖节点的值
			Node<K, V> preNode = precursor(node);
			node.key = preNode.key;
			node.value = preNode.value;
			// 删除前驱节点或后继节点
			// 前驱节点或后继节点肯定是度为1 或0的节点
			node = preNode;
		}

		// 删除度为1或0的节点
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.isLeftChild()) {
				node.parent.left = replacement;
			} else if (node.isRightChild()) {
				node.parent.right = replacement;
			} else {
				// 根节点
				root = replacement;
			}
			afterRemove(node, replacement);
		} else if (node.parent == null) {
			root = null;
		} else {
			// 叶子节点
			if (node.isLeftChild()) {
				node.parent.left = null;
			} else if (node.isRightChild()) {
				node.parent.right = null;
			}
			afterRemove(node, null);
		}

		size--;
		return oldNode.value;
	}

	private void afterRemove(Node<K, V> node, Node<K, V> replacement) {

		// 如果删除的红色节点 不需处理
		if (isRed(node))
			return;

		// 来到这里删除是BLACK节点
		// 删除有一个RED子节点的
		if (isRed(replacement)) {
			// 将替代的子节点染成BLACK
			black(replacement);
			return;
		}

		// 删除BLACK叶子节点
		Node<K, V> parent = node.parent;
		if (parent == null)// 删除的是根节点
			return;

		// 找兄弟节点
		boolean isLeft = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = isLeft ? parent.right : parent.left;

		if (isLeft) {
			// 转换成兄弟节点为BLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateLeft(parent);
				sibling = parent.right;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 不能借
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// 父节点向下合并，可能还会造成下溢，将parent作为删除的节点处理
				if (isParentBlack)
					afterRemove(parent, null);
			} else {

				if (isBlack(sibling.right)) { // RL
					rotateLeft(sibling);
					sibling = parent.right;
				}
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.right);
				rotateLeft(parent);
			}
		} else {
			// 转换成兄弟节点为BLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateRight(parent);
				sibling = parent.left;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 不能借
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// 父节点向下合并，可能还会造成下溢，将parent作为删除的节点处理
				if (isParentBlack)
					afterRemove(parent, null);
			} else {

				if (isBlack(sibling.left)) { // LR
					rotateLeft(sibling);
					sibling = parent.left;
				}
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.left);
				rotateRight(parent);
			}

		}

	}

	private Node<K, V> precursor(Node<K, V> node) {
		Node<K, V> leftNode = node.left;
		if (leftNode != null) { // node.left.right.right.....
			while (leftNode.right != null) {
				leftNode = leftNode.right;
			}
			return leftNode;

		} else { // node.parent.parent....
			while (node.parent != null && node.isLeftChild()) {
				node = node.parent;
			}
			return node.parent;
		}
	}

	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if (root == null)
			return false;
		Queue<Node<K, V>> queue = new LinkedList<TreeMap.Node<K, V>>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<K, V> node = queue.poll();
			if (isValEquals(value, node.value))
				return true;
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
		return false;
	}

	private boolean isValEquals(V v1, V v2) {
		return v1 == null ? v2 == null : v1.equals(v2);
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		traversal(root, visitor);

	}

	private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
		if (node==null||visitor == null || visitor.stop)
			return;

		traversal(node.left, visitor);
		if (visitor.stop)
			return;
		visitor.stop = visitor.visitor(node.key, node.value,node.color);
		traversal(node.right, visitor);
	}

}
