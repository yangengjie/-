package com.ygj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.ygj.printer.BinaryTreeInfo;

public class BinarySearchTree<E> implements BinaryTreeInfo {

	private int size;
	// 根节点
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

	// 元素的数量
	public int size() {
		return size;
	}

	// 元素是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	// 清空元素
	public void clear() {
	}

	// 添加元素
	public void add(E element) {
		// 由于二叉搜索树添加的元素不能为null
		elementNotNullCheck(element);

		// root等于null 第一次添加
		if (root == null) {
			root = new Node<>(element, null);
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
		Node<E> newNode = new Node<>(element, parent);
		// 3、该节点是parent的左子节点还是右子节点
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

	// 前序遍历---递归方式
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop)
			return;
		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}

	// 前序遍历---迭代方式：使用栈来实现
	/**
	 * 1、将root入栈 2、弹出栈顶元素 3、将栈顶元素的右子节点入栈 4、将栈顶元素的左子节点入栈 5、栈为空则结束遍历
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

	// 中序遍历--递归方式实现
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop)
			return;
		inorder(node.left, visitor);
		// 在遍历左子树时visitor.stop可能已经为true，所以需要在此处加上判断
		if (visitor.stop)
			return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}

	/**
	 * 中序遍历--使用迭代方式实现--使用栈来实现 1、设置node=root 2、进行如下循环操作： 1、如果node！=null a、将node入栈
	 * b、设置node=node.left 2、如果node==null a、如果栈为空则结束循环 b、将栈顶元素出栈，并赋值给node c、对node进行访问
	 * d、设置node=node.right
	 */
	private void inorder2() {
		if (root == null)
			return;
		Stack<Node<E>> stack = new Stack<>();
		// 设置node=root
		Node<E> node = root;
		while (true) {
			/**
			 * 如果node不为空，将node加入到栈中 设置node=node.left
			 */
			if (node != null) {
				stack.add(node);
				node = node.left;
			} else {
				/**
				 * 如果node==null 此时如果栈为空，则退出循环 将栈顶元素出栈，并赋值给node 访问node 设置node=node.right
				 */
				if (stack.isEmpty())
					break;
				node = stack.pop();
				System.out.println(node.element);
				node = node.right;
			}

		}

	}

	// 后序遍历
	public void postorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
		postorder(root, visitor);
	}

	// 递归方式实现后序遍历
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
	 * 后序遍历---使用迭代方式实现
	 * 
	 */
	private void postorder2() {

	}

	// 层序遍历
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

	// 删除元素
	public void remove(E element) {
	}

	// 是否包含某个元素
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
