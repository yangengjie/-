package com.ygj.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.ygj.printer.BinaryTreeInfo;

/**
 * 二叉树
 */
public class BinaryTree<E> implements BinaryTreeInfo {

	protected int size;
	// 根节点
	protected Node<E> root;

	public static abstract class Visitor<E> {
		public boolean stop;

		public abstract boolean visit(E e);
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

	public void preorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
//		preorder(root, visitor);
//		preorder2(visitor);
		preorder3(visitor);
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
	private void preorder2(Visitor<E> visitor) {
		if (root == null)
			return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		stack.add(node);
		while (!stack.isEmpty()) {
			node = stack.pop();
			if (visitor.visit(node.element))
				break;
			if (node.right != null)
				stack.add(node.right);
			if (node.left != null)
				stack.add(node.left);
		}
	}

	/**
	 * 前序遍历：非递归方式
	 */
	private void preorder3(Visitor<E> visitor) {
		if (root == null)
			return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		while (true) {
			if (node != null) {
				// 访问
				if (visitor.visit(node.element))
					break;
				// 将node的右子节点加到栈中
				if (node.right != null)
					stack.push(node.right);
				// 向左访问
				node = node.left;
			} else { // 当访问到最左边时，出栈栈顶元素，如果栈顶元素有左子节点，则执行上面的逻辑
				if (stack.isEmpty())
					break;
				// 将右子节点出栈
				node = stack.pop();
			}

		}
	}

	public void inorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
//		inorder(root, visitor);
		inorder2(visitor);
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
	private void inorder2(Visitor<E> visitor) {
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
				if (visitor.visit(node.element))
					return;
				node = node.right;
			}

		}

	}

	// 后序遍历
	public void postorder(Visitor<E> visitor) {
		if (visitor == null)
			return;
//		postorder(root, visitor);
		postorder11(visitor);
	}

	private void postorder11(Visitor<E> visitor) {
		if (root == null || visitor.stop)
			return;

		Node<E> prev = null;
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<E> top = stack.peek();
			if (top.isLeaf() || (prev != null && prev.parent == top)) {
				if (stack.isEmpty())
					return;
				prev = stack.pop();
				if (visitor.visit(prev.element))
					return;
			} else {
				if (top.right != null)
					stack.push(top.right);
				if (top.left != null)
					stack.push(top.left);
			}
		}
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
	private void postorder2(Visitor<E> visitor) {
		if (root == null || visitor == null)
			return;
		Node<E> preNode = null;
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node<E> top = stack.peek();
			if (top.isLeaf() || (preNode != null && preNode.parent == top)) {
				preNode = stack.pop();
				// 访问节点
				if (visitor.visit(preNode.element))
					return;
			} else {
				if (top.right != null)
					stack.push(top.right);
				if (top.left != null)
					stack.push(top.left);
			}
		}
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

	public int height(E element) {
		return height(root);
	}

	private int height;// 树的高度
	private int levelCount = 1;// 一层的元素数量
	// 计算二叉树的高度 使用层序遍历的方式

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

			// 如果node.left!=null就入队
			if (node.left != null)
				queue.offer(node.left);
			else { //
				if (node.right != null)
					return false;
//				和下面叶子节点判断重复，可去掉
//				else if (node.right == null)
//					leaf = true;
			}

			if (node.right != null)
				queue.offer(node.right);
			else {
//				if(node.left!=null) {
//					leaf = true;
//				}else if(node.left==null) { //重复
//					leaf = true;
//				}
				// 上面判断重复,简化为
				leaf = true;
			}
		}

		return true;
	}

	// 获取指定节点的前驱结点
	protected Node<E> precursor(Node<E> node) {
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

		// 走到这里条件 node.parent===null || node == node.parent.right
		// 这两种情况下都会返回node.parent
		return node.parent;
	}

	// 后继节点
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

	protected void elementNotNullCheck(E element) {
		if (element == null)
			throw new IllegalArgumentException("element must not be null");
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

	protected static class Node<E> {
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
}
