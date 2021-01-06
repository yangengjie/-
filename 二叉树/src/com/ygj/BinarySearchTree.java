package com.ygj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.ygj.printer.BinaryTreeInfo;

public class BinarySearchTree<E> implements BinaryTreeInfo {

	private int size;
	// ���ڵ�
	private Node<E> root;

	private Comparator<E> comparator;

	public static abstract class Visitor<E> {
		public boolean stop;

		public abstract boolean visit(E e);
	}

	public BinarySearchTree() {
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Ԫ�ص�����
	public int size() {
		return size;
	}

	// Ԫ���Ƿ�Ϊ��
	public boolean isEmpty() {
		return size == 0;
	}

	// ���Ԫ��
	public void clear() {
	}

	// ���Ԫ��
	public void add(E element) {
		// ���ڶ�����������ӵ�Ԫ�ز���Ϊnull
		elementNotNullCheck(element);

		// root����null ��һ�����
		if (root == null) {
			root = new Node<>(element, null);
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
		Node<E> newNode = new Node<>(element, parent);
		// 3���ýڵ���parent�����ӽڵ㻹�����ӽڵ�
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		size++;
	}

	public void preorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
		preorder(root, visitor);
//		preorder2();
	}

	// ǰ�����---�ݹ鷽ʽ
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop)
			return;
		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}

	// ǰ�����---������ʽ��ʹ��ջ��ʵ��
	/**
	 * 1����root��ջ 2������ջ��Ԫ�� 3����ջ��Ԫ�ص����ӽڵ���ջ 4����ջ��Ԫ�ص����ӽڵ���ջ 5��ջΪ�����������
	 */
	private void preorder2() {
		if (root == null)
			return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		stack.add(node);
		while (!stack.isEmpty()) {
			node = stack.pop();
			System.out.println(node.element);
			if (node.right != null)
				stack.add(node.right);
			if (node.left != null)
				stack.add(node.left);
		}
	}

	public void inorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
		inorder(root, visitor);
//		inorder2();
	}

	// �������--�ݹ鷽ʽʵ��
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop)
			return;
		inorder(node.left, visitor);
		// �ڱ���������ʱvisitor.stop�����Ѿ�Ϊtrue��������Ҫ�ڴ˴������ж�
		if (visitor.stop)
			return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}

	/**
	 * �������--ʹ�õ�����ʽʵ��--ʹ��ջ��ʵ�� 1������node=root 2����������ѭ�������� 1�����node��=null a����node��ջ
	 * b������node=node.left 2�����node==null a�����ջΪ�������ѭ�� b����ջ��Ԫ�س�ջ������ֵ��node c����node���з���
	 * d������node=node.right
	 */
	private void inorder2() {
		if (root == null)
			return;
		Stack<Node<E>> stack = new Stack<>();
		// ����node=root
		Node<E> node = root;
		while (true) {
			/**
			 * ���node��Ϊ�գ���node���뵽ջ�� ����node=node.left
			 */
			if (node != null) {
				stack.add(node);
				node = node.left;
			} else {
				/**
				 * ���node==null ��ʱ���ջΪ�գ����˳�ѭ�� ��ջ��Ԫ�س�ջ������ֵ��node ����node ����node=node.right
				 */
				if (stack.isEmpty())
					break;
				node = stack.pop();
				System.out.println(node.element);
				node = node.right;
			}

		}

	}

	// �������
	public void postorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
		postorder(root, visitor);
	}

	// �ݹ鷽ʽʵ�ֺ������
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop)
			return;
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop)
			return;
		visitor.stop = visitor.visit(node.element);
	}

	/**
	 * �������---ʹ�õ�����ʽʵ��
	 * 
	 */
	private void postorder2() {

	}

	// �������
	public void levelorder(Visitor<E> visitor) {
		if (root == null || visitor == null)
			return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element))
				break;
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
	}

	private int compare(E e1, E e2) {
		if (comparator != null)
			return comparator.compare(e1, e2);
		return ((Comparable<E>) e1).compareTo(e2);
	}

	private void elementNotNullCheck(E element) {
		if (element == null)
			throw new IllegalArgumentException("element must not be null");
	}

	// ɾ��Ԫ��
	public void remove(E element) {
	}

	// �Ƿ����ĳ��Ԫ��
	public boolean contains(E element) {
		return false;
	}

	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>) node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>) node).right;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		Node<E> newNode = (Node<E>) node;
		return newNode.element + "_p_" + (newNode.parent == null ? "null" : newNode.parent.element);
	}

}
