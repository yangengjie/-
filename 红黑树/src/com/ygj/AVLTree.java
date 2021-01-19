package com.ygj;

import java.sql.Blob;

import com.ygj.BinaryTree.Node;

/**
 *AVLTree
 */
public class AVLTree<E> extends BST<E> {

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode(element, parent);
	}

	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) { // ƽ��
				// ���¸߶�
				updateHeight(node);
			} else { // ʧ��
				// �ָ�ƽ��
				rebalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node,Node<E> replacement) {
		while ((node=node.parent)!=null) {
			if (isBalanced(node)) { // ƽ��
				// ���¸߶�
				updateHeight(node);
			} else { // ʧ��
				// �ָ�ƽ��
				rebalance(node);
			}
		}
	}

	/**
	 * ��4�����ν���ͳһ����
	 * @param grand
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = getTallerChildNode(grand);
		Node<E> node = getTallerChildNode(parent);
		if (parent == grand.left) { // L
			if (node == parent.left) { // LL
				rotate(grand, node, parent, grand, node.left, node.right, parent.right, grand.right);
			} else { // LR
				rotate(grand, parent, node, grand, parent.left, node.left, node.right, grand.right);
			}
		} else { // R
			if (node == parent.left) {// RL
				rotate(grand, grand, node, parent, grand.left, node.left, node.right, parent.right);
			} else {// RR
				rotate(grand, grand, parent, node, grand.left, parent.left, node.left, node.right);
			}
		}
	}
	
	private void rotate(
			Node<E> r,// �����ĸ��ڵ�
			Node<E> b,Node<E> d,Node<E> f,
			Node<E> a,Node<E> c,Node<E> e,Node<E> g
			) {
		
		//��d��Ϊ���ڵ�
		d.parent=r.parent;
		if(r.isLeftChild())
			r.parent.left=d;
		else if(r.isRightChild())
			r.parent.right=d;
		else 
			root=d;
		
		//b-c
		b.parent=d;
		b.right=c;
		if(c!=null)
			c.parent=b;
		
		
		// e-f
		f.parent=d;
		f.left=e;
		if(e!=null)
			e.parent=f;
		
		//b d f
		d.left=b;
		d.right=f;
		
		
		updateHeight(f);
		updateHeight(b);
		updateHeight(d);
	}
	
	
	/**
	 * �ָ�ƽ��
	 * 
	 * @param grand �߶���͵��Ǹ��游�ڵ�
	 */
	private void rebalance2(Node<E> grand) {
		// ������ת����LL��RR��LR��RL
		/**
		 * ��ô����ҵ�parent��node�ڵ���? ��ʵparent�ڵ�����游�ڵ����������и߶Ƚϸߵ������ĸ��ڵ�
		 * node�ڵ���parent�ڵ����������и߶Ƚϸߵ������ĸ��ڵ�
		 */
		Node<E> parent = getTallerChildNode(grand);
		Node<E> node = getTallerChildNode(parent);
		// �������֮��ʧ���һ�����游�ڵ㣬����grand��parent��nodeһ������null��
		if (parent == grand.left) { // L
			if (node == parent.left) { // LL
				// ��������
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node == parent.left) {// RL
				rotateRight(parent);
				rotateLeft(grand);
			} else {// RR
				rotateLeft(grand);
			}
		}

	}

	/**
	 * ����
	 * 
	 * @param grand Ҫ��ת�Ľڵ�
	 */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;

		grand.left = child;
		parent.right = grand;

		// ��parent��Ϊ�����ĸ��ڵ�
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // ���ڵ�
			root = parent;
		}
		// ����grand��parent
		grand.parent = parent;
		// ����child��parent
		if (child != null)
			child.parent = grand;

		// ���¸߶�
		updateHeight(grand);
		updateHeight(parent);
	}

	/**
	 * ����
	 * 
	 * @param grand Ҫ��ת�Ľڵ�
	 */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;

		grand.right = child;
		parent.left = grand;

		// ��parent��Ϊ���ڵ�
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // ���ڵ�
			root = parent;
		}

		// ����grand��parent
		grand.parent = parent;
		// ����child��parent
		if (child != null)
			child.parent = grand;

		// ���ø߶�
		updateHeight(grand);
		updateHeight(parent);

	}

	private AVLNode<E> getTallerChildNode(Node<E> grand) {
		return ((AVLNode) grand).getTallerChildNode();
	}

	// �жϽڵ��Ƿ�ƽ��
	private boolean isBalanced(Node<E> node) {
		return ((AVLNode<E>) node).isBalanced();
	}

	// ���½ڵ�ĸ߶�
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}

	public static class AVLNode<E> extends Node<E> {
		// ��������ӵ�һ����Ҷ�ӽڵ㣬����Ĭ�ϸ߶ȿ�������Ϊ1
		private int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}

		// �ж��Ƿ�ʧ��
		public boolean isBalanced() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			return Math.abs(leftHeight - rightHeight) < 2;
		}

		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}

		public AVLNode<E> getTallerChildNode() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight)
				return (AVLNode<E>) left;
			if (leftHeight < rightHeight)
				return (AVLNode<E>) right;
			/**
			 * ��leftHeight == rightHeightʱ������ڵ��Ǹ��ڵ�����ӽڵ㣬��ô���������� ��֮�򷵻ظ��ڵ�����ӽڵ�
			 * ע�⣺�����������߶����ʱ�����������������ǿ��Եģ����Լ���ô���
			 */
			return isLeftChild() ? (AVLNode<E>) left : (AVLNode<E>) right;
		}

		@Override
		public String toString() {
			return element + "_p_" + (parent == null ? "null" : parent.element) + "_h_" + height;
		}
	}
}
