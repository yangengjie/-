package com.ygj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.ygj.printer.BinaryTreeInfo;

/**
 * ����������
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

	// ���Ԫ��
	public void add(E element) {
		// ���ڶ�����������ӵ�Ԫ�ز���Ϊnull
		elementNotNullCheck(element);

		// root����null ��һ�����
		if (root == null) {
			root = createNode(element, null);
			afterAdd(root);
			size++;
			return;
		}
		// �ǵ�һ����� Ԫ����ӵĲ���
		// 1���ҵ�Ҫ��ӽڵ��parent�ڵ�
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
				// ��Ԫ�ص�ֵ���ʱ���и���
				node.element = element;
				return;
			}
		}
		// 2�������µĽڵ�
		Node<E> newNode = createNode(element, parent);
		// 3���ýڵ���parent�����ӽڵ㻹�����ӽڵ�
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
	 * @param node ����ӵĽڵ�
	 */
	protected void afterAdd(Node<E> node) {

	}
	
	/**
	 * @param node Ҫɾ���Ľڵ�
	 */
	protected void afterRemove(Node<E> node) {
		
	}

	// ͨ��Ԫ�ز��ҽڵ�
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

	// ɾ��Ԫ��
	public void remove(E element) {
		remove(node(element));
	}

	private void remove(Node<E> node) {
		if (node == null)
			return;
		// ɾ����Ϊ2�Ľڵ�
		if (node.hasTwoChildren()) {
			// �ҵ�ǰ���ڵ�
			Node<E> precurNode = precursor(node);
			// ��ǰ���ڵ��ֵ����Ҫɾ���ڵ��ֵ
			node.element = precurNode.element;
			// ɾ��ǰ���ڵ�
			node = precurNode;
		}

		// ɾ����node�ڵ�Ķ�Ϊ1��0
		Node<E> child = node.left != null ? node.left : node.right;
		if (child != null) { // ��Ϊ1�Ľڵ�
			// ʹ���ӽڵ����Ҫɾ���ڵ��λ��
			child.parent = node.parent;
			if (node.parent == null)
				root = child;
			else if (node == node.parent.left)
				node.parent.left = child;
			else if (node == node.parent.right)
				node.parent.right = child;
		} // ɾ��Ҷ�ӽڵ�
		else if (node.parent == null)
			root = null;
		else if (node == node.parent.left)
			node.parent.left = null;
		else if (node == node.parent.right)
			node.parent.right = null;
		size--;
		afterRemove(node);
	}

	// �Ƿ����ĳ��Ԫ��
	public boolean contains(E element) {
		return node(element) != null;
	}

}
