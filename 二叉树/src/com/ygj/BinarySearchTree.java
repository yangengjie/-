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
		size = 0;
		root = null;
	}

	public void levelorderTraserval() {
		if (root == null)
			return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			System.out.println(node.element);
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
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

	public int height(E element) {
		return height(node(element));
//		return height2(node(element));
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

	private int height;// ���ĸ߶�
	private int levelCount = 1;// һ���Ԫ������
	// ����������ĸ߶� ʹ�ò�������ķ�ʽ

	private int height(Node<E> node) {
		if (node == null)
			return 0;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			Node<E> newNode = queue.poll();
			levelCount--;
			if (newNode.left != null)
				queue.offer(newNode.left);
			if (newNode.right != null)
				queue.offer(newNode.right);
			if (levelCount == 0) {
				levelCount = queue.size();
				height++;
			}
		}
		return height;
	}

	private int height2(Node<E> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height2(node.left), height2(node.right));
	}

	public boolean isCompleteTree() {
		if (root == null)
			return false;
		boolean leaf = false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf())
				return false;

			// ���node.left!=null�����
			if (node.left != null)
				queue.offer(node.left);
			else { //
				if (node.right != null)
					return false;
//				������Ҷ�ӽڵ��ж��ظ�����ȥ��
//				else if (node.right == null)
//					leaf = true;
			}

			if (node.right != null)
				queue.offer(node.right);
			else {
//				if(node.left!=null) {
//					leaf = true;
//				}else if(node.left==null) { //�ظ�
//					leaf = true;
//				}
				// �����ж��ظ�,��Ϊ
				leaf = true;
			}
		}

		return true;
	}

	public E precursor(E element) {
		Node<E> node = precursor(node(element));
		return node == null ? null : node.element;
	}

	// ��ȡָ���ڵ��ǰ�����
	private Node<E> precursor(Node<E> node) {
		if (node == null)
			return node;
		Node<E> leftNode = node.left;
		// node.left.right.right....
		if (leftNode != null) {
			while (leftNode.right != null) {
				leftNode = leftNode.right;
			}
			return leftNode;
		}

		// node.parent.parent....
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}

		// �ߵ��������� node.parent===null || node == node.parent.right
		// ����������¶��᷵��node.parent
		return node.parent;
	}

	public E successor(E element) {
		Node<E> node = successor(node(element));
		return node == null ? null : node.element;
	}

	// ��̽ڵ�
	private Node<E> successor(Node<E> node) {
		if (node == null)
			return null;
		Node<E> rightNode = node.right;
		// node.right.left.left....
		if (rightNode != null) {
			while (rightNode.left != null) {
				rightNode = rightNode.left;
			}
			return rightNode;
		}

		// node.parent.parent...
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
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
	}

	// �Ƿ����ĳ��Ԫ��
	public boolean contains(E element) {
		return node(element) != null;
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

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, root, "");
		return sb.toString();
	}

	// ʹ��ǰ�������ʽ��ӡ������
	private void toString(StringBuilder sb, Node<E> node, String prefix) {
		if (node == null)
			return;
		sb.append(prefix).append("��").append(node.element).append("��").append("\n");
		toString(sb, node.left, prefix + "��L��");
		toString(sb, node.right, prefix + "��R��");
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
