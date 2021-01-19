package com.ygj.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.omg.CORBA.PUBLIC_MEMBER;


/**
 * 二叉搜索树
 * 
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {

	private Comparator<E> comparator;

	public BST() {
		this(null);
	}

	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// 添加元素
	public void add(E element) {
		// 由于二叉搜索树添加的元素不能为null
		elementNotNullCheck(element);

		// root等于null 第一次添加
		if (root == null) {
			root = createNode(element, null);
			afterAdd(root);
			size++;
			return;
		}
		// 非第一次添加 元素添加的步骤
		// 1、找到要添加节点的parent节点
		Node<E> node = root;
		Node<E> parent = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				// 当元素的值相等时进行覆盖
				node.element = element;
				return;
			}
		}
		// 2、创建新的节点
		Node<E> newNode = createNode(element, parent);
		// 3、该节点是parent的左子节点还是右子节点
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		afterAdd(newNode);
		size++;
	}

	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}

	/**
	 * @param node 新添加的节点
	 */
	protected void afterAdd(Node<E> node) {

	}

	/**
	 * @param node 要删除的节点
	 */
	protected void afterRemove(Node<E> node,Node<E> replacement) {

	}

	// 通过元素查找节点
	public Node<E> node(E element) {
		if (root == null)
			return null;
		Node<E> node = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			if (cmp == 0)
				return node;
			if (cmp > 0)
				node = node.right;
			else
				node = node.left;
		}
		return null;
	}

	private int compare(E e1, E e2) {
		if (comparator != null)
			return comparator.compare(e1, e2);
		return ((Comparable<E>) e1).compareTo(e2);
	}

	// 删除元素
	public void remove(E element) {
		remove(node(element));
	}

	private void remove(Node<E> node) {
		if (node == null)
			return;
		// 删除度为2的节点
		if (node.hasTwoChildren()) {
			// 找到前驱节点
			Node<E> precurNode = precursor(node);
			// 用前驱节点的值覆盖要删除节点的值
			node.element = precurNode.element;
			// 删除前驱节点
			node = precurNode;
		}
		// 删除的node节点的度为1或0
		Node<E> replacement = node.left != null ? node.left : node.right;
		if (replacement != null) { // 度为1的节点
			// 使用子节点替代要删除节点的位置
			replacement.parent = node.parent;
			if (node.parent == null)
				root = replacement;
			else if (node == node.parent.left)
				node.parent.left = replacement;
			else if (node == node.parent.right)
				node.parent.right = replacement;
			afterRemove(node,replacement);
		} // 删除叶子节点
		else if (node.parent == null) {// node是叶子节点并且是根节点
			root = null;
			afterRemove(node,null);
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}
			// 删除节点之后的处理
			afterRemove(node,null);
		}
		size--;
	}

	// 是否包含某个元素
	public boolean contains(E element) {
		return node(element) != null;
	}

}
