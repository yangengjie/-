package com.ygj;

public class RBTree<E> extends BST<E> {
	public static final boolean RED = true;
	public static final boolean BLACK = false;

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<E>(element, parent);
	}
	
	@Override
	protected void afterRemove(Node<E> node, Node<E> replacement) {
		// 如果删除的是红色节点，不进行处理
		if (isRed(node))
			return;

		// 来到这里说明删除的黑色节点
		// 如果替代的子节点红色子节点
		if (isRed(replacement)) {
			// 将替代的子节点染成BLACK即可
			black(replacement);
			return;
		}

		// 来到这里,说明删除的是BLACK叶子节点
		// 如果删除的是根节点
		Node<E> parent = node.parent;
		if (parent == null)
			return;

		// 如果删除的是黑色叶子节点，如果兄弟节点是黑色的，看兄弟节点能不能借元素，能借进行旋转操作，不能借需要合并
		// 合并还会造成下溢，需要将合并的父节点作为删除的节点，执行删除逻辑。
		// 不能使用node.sibling()获取兄弟节点，因为此时parent的left或right指向的不再是node，它已被删除了
//				Node<E> sibling=node.sibling();
		boolean isLeft = parent.left == null || node.isLeftChild();
		Node<E> sibling = isLeft ? parent.right : parent.left;
		if (isLeft) { // 被删除的叶子节点在左边，兄弟节点在右边

			if (isRed(sibling)) {
				black(sibling);
				red(parent);

				rotateLeft(parent);

				sibling = parent.right;
			}

			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 表示不能借

				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack)
					afterRemove(parent, null);
			} else {
				if (isBlack(sibling.right)) {//RL
					rotateRight(sibling);
					sibling = parent.right;
				}

				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.right);
				rotateLeft(parent);
			}
		} else { // 被删除的叶子节点在右边，兄弟节点在左边
			// 如果兄弟节点是红色 ,由于兄弟节点是红色，需要转换成兄弟节点是黑色（将侄子节点变成兄弟节点）
			// 所以为了减少重复代码，先判断红色兄弟节点
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				// parent右旋转
				rotateRight(parent);
				// 更换兄弟
				sibling = parent.left;
			}

			// 来到这里兄弟节点必然是黑色的
			// 判断兄弟节点能不能借元素，就是兄弟节点至少有一个红色子节点
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 表示不能借
				// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack)
					afterRemove(parent, null);
			} else { // 兄弟节点至少有一个红色的子节点
				// 将LR转换成LL，然后统一执行LL情况的代码
				if (isBlack(sibling.left)) { // LR
					rotateLeft(sibling);
					sibling = parent.left;
				}
				// 旋转中心继承parent的颜色，左右子节点染成BLACK
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.left);
				rotateRight(parent);
			}

		}

	}

	@Override
	protected void afterAdd(Node<E> node) {
		// 添加总共分为12种情况
		// 如果添加的red节点的parent是黑色的不需要任何处理(4种情况)
		Node<E> parent = node.parent;
		if (parent == null) { // 添加的是根节点
			black(node);
			return;
		}

		if (isBlack(parent))
			return;

		// parent是RED，分为parent的sibling是RED和parent的sibling是BLACK
		// 如果parent的sibling是RED，会上溢，将parent和parent的sibling染成BLACK，将grand节点染成RED向上合并，作为新添加的节点
		// 向上合并可能会依然会造成上溢
		Node<E> sibling = parent.sibling();
		Node<E> grand = parent.parent;
		if (isRed(sibling)) {
			black(parent);
			black(sibling);
			grand = red(grand);
			afterAdd(grand);
			return;
		}

		// parent的sibling是黑色的
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
				red(grand);
				rotateRight(grand);
			} else {// LR
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
			} else {// RR
				black(parent);
				red(grand);
				rotateLeft(grand);
			}
		}

	}

	private void rotateRight(Node<E> grand) { // LL
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;

		// 让parent称为根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // 根节点
			root = parent;
		}

		grand.parent = parent;
		if (child != null)
			child.parent = grand;
	}

	private void rotateLeft(Node<E> grand) { // RR
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;

		// 让parent称为根节点
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

	/**
	 * 获取节点的颜色
	 */
	public boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode) node).color;
	}

	/**
	 * 判断节点是否是黑色
	 */
	public boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}

	/**
	 * 判断节点是否是红色
	 */
	public boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}

	/**
	 * 对节点进行染色
	 * 
	 * @return 返回染色后的节点
	 */
	public RBNode<E> color(Node<E> node, boolean color) {
		if (node != null)
			((RBNode<E>) node).color = color;
		return ((RBNode<E>) node);
	}

	/**
	 * 将节点染成黑色
	 */
	public RBNode<E> black(Node<E> node) {
		return color(node, BLACK);
	}

	/**
	 * 将节点染成红色
	 */
	public RBNode<E> red(Node<E> node) {
		return color(node, RED);
	}

	public static class RBNode<E> extends Node<E> {
		// 新添加的节点默认是红色，这样就能满足1、2、3、5四条性质，不能保证性质4一定满足
		public boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}

		@Override
		public String toString() {
			String colorString = "";
			if (color == RED)
				colorString = "_RED_";
			return colorString + element.toString();

		}

	}

}
