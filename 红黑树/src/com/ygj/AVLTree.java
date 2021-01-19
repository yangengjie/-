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
			if (isBalanced(node)) { // 平衡
				// 更新高度
				updateHeight(node);
			} else { // 失衡
				// 恢复平衡
				rebalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node,Node<E> replacement) {
		while ((node=node.parent)!=null) {
			if (isBalanced(node)) { // 平衡
				// 更新高度
				updateHeight(node);
			} else { // 失衡
				// 恢复平衡
				rebalance(node);
			}
		}
	}

	/**
	 * 对4种情形进行统一处理
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
			Node<E> r,// 子树的根节点
			Node<E> b,Node<E> d,Node<E> f,
			Node<E> a,Node<E> c,Node<E> e,Node<E> g
			) {
		
		//将d作为根节点
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
	 * 恢复平衡
	 * 
	 * @param grand 高度最低的那个祖父节点
	 */
	private void rebalance2(Node<E> grand) {
		// 进行旋转处理：LL、RR、LR、RL
		/**
		 * 那么如何找到parent和node节点呢? 其实parent节点就是祖父节点左右子树中高度较高的子树的根节点
		 * node节点是parent节点左右子树中高度较高的子树的根节点
		 */
		Node<E> parent = getTallerChildNode(grand);
		Node<E> node = getTallerChildNode(parent);
		// 由于添加之后失衡的一定是祖父节点，所以grand、parent、node一定不会null。
		if (parent == grand.left) { // L
			if (node == parent.left) { // LL
				// 进行右旋
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
	 * 右旋
	 * 
	 * @param grand 要旋转的节点
	 */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;

		grand.left = child;
		parent.right = grand;

		// 让parent成为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // 根节点
			root = parent;
		}
		// 更新grand的parent
		grand.parent = parent;
		// 更新child的parent
		if (child != null)
			child.parent = grand;

		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}

	/**
	 * 左旋
	 * 
	 * @param grand 要旋转的节点
	 */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;

		grand.right = child;
		parent.left = grand;

		// 让parent成为根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // 根节点
			root = parent;
		}

		// 设置grand的parent
		grand.parent = parent;
		// 设置child的parent
		if (child != null)
			child.parent = grand;

		// 设置高度
		updateHeight(grand);
		updateHeight(parent);

	}

	private AVLNode<E> getTallerChildNode(Node<E> grand) {
		return ((AVLNode) grand).getTallerChildNode();
	}

	// 判断节点是否平衡
	private boolean isBalanced(Node<E> node) {
		return ((AVLNode<E>) node).isBalanced();
	}

	// 更新节点的高度
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}

	public static class AVLNode<E> extends Node<E> {
		// 由于新添加的一定是叶子节点，所以默认高度可以设置为1
		private int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}

		// 判断是否失衡
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
			 * 在leftHeight == rightHeight时，如果节点是父节点的左子节点，那么返回左子树 反之则返回父节点的右子节点
			 * 注意：在左右子树高度相等时，返回左右子树都是可以的，看自己怎么设计
			 */
			return isLeftChild() ? (AVLNode<E>) left : (AVLNode<E>) right;
		}

		@Override
		public String toString() {
			return element + "_p_" + (parent == null ? "null" : parent.element) + "_h_" + height;
		}
	}
}
