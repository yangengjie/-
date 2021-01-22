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
		// ���ɾ�����Ǻ�ɫ�ڵ㣬�����д���
		if (isRed(node))
			return;

		// ��������˵��ɾ���ĺ�ɫ�ڵ�
		// ���������ӽڵ��ɫ�ӽڵ�
		if (isRed(replacement)) {
			// ��������ӽڵ�Ⱦ��BLACK����
			black(replacement);
			return;
		}

		// ��������,˵��ɾ������BLACKҶ�ӽڵ�
		// ���ɾ�����Ǹ��ڵ�
		Node<E> parent = node.parent;
		if (parent == null)
			return;

		// ���ɾ�����Ǻ�ɫҶ�ӽڵ㣬����ֵܽڵ��Ǻ�ɫ�ģ����ֵܽڵ��ܲ��ܽ�Ԫ�أ��ܽ������ת���������ܽ���Ҫ�ϲ�
		// �ϲ�����������磬��Ҫ���ϲ��ĸ��ڵ���Ϊɾ���Ľڵ㣬ִ��ɾ���߼���
		// ����ʹ��node.sibling()��ȡ�ֵܽڵ㣬��Ϊ��ʱparent��left��rightָ��Ĳ�����node�����ѱ�ɾ����
//				Node<E> sibling=node.sibling();
		boolean isLeft = parent.left == null || node.isLeftChild();
		Node<E> sibling = isLeft ? parent.right : parent.left;
		if (isLeft) { // ��ɾ����Ҷ�ӽڵ�����ߣ��ֵܽڵ����ұ�

			if (isRed(sibling)) {
				black(sibling);
				red(parent);

				rotateLeft(parent);

				sibling = parent.right;
			}

			if (isBlack(sibling.left) && isBlack(sibling.right)) { // ��ʾ���ܽ�

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
		} else { // ��ɾ����Ҷ�ӽڵ����ұߣ��ֵܽڵ������
			// ����ֵܽڵ��Ǻ�ɫ ,�����ֵܽڵ��Ǻ�ɫ����Ҫת�����ֵܽڵ��Ǻ�ɫ����ֶ�ӽڵ����ֵܽڵ㣩
			// ����Ϊ�˼����ظ����룬���жϺ�ɫ�ֵܽڵ�
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				// parent����ת
				rotateRight(parent);
				// �����ֵ�
				sibling = parent.left;
			}

			// ���������ֵܽڵ��Ȼ�Ǻ�ɫ��
			// �ж��ֵܽڵ��ܲ��ܽ�Ԫ�أ������ֵܽڵ�������һ����ɫ�ӽڵ�
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // ��ʾ���ܽ�
				// �ֵܽڵ�û��1����ɫ�ӽڵ㣬���ڵ�Ҫ���¸��ֵܽڵ�ϲ�
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack)
					afterRemove(parent, null);
			} else { // �ֵܽڵ�������һ����ɫ���ӽڵ�
				// ��LRת����LL��Ȼ��ͳһִ��LL����Ĵ���
				if (isBlack(sibling.left)) { // LR
					rotateLeft(sibling);
					sibling = parent.left;
				}
				// ��ת���ļ̳�parent����ɫ�������ӽڵ�Ⱦ��BLACK
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.left);
				rotateRight(parent);
			}

		}

	}

	@Override
	protected void afterAdd(Node<E> node) {
		// ����ܹ���Ϊ12�����
		// �����ӵ�red�ڵ��parent�Ǻ�ɫ�Ĳ���Ҫ�κδ���(4�����)
		Node<E> parent = node.parent;
		if (parent == null) { // ��ӵ��Ǹ��ڵ�
			black(node);
			return;
		}

		if (isBlack(parent))
			return;

		// parent��RED����Ϊparent��sibling��RED��parent��sibling��BLACK
		// ���parent��sibling��RED�������磬��parent��parent��siblingȾ��BLACK����grand�ڵ�Ⱦ��RED���Ϻϲ�����Ϊ����ӵĽڵ�
		// ���Ϻϲ����ܻ���Ȼ���������
		Node<E> sibling = parent.sibling();
		Node<E> grand = parent.parent;
		if (isRed(sibling)) {
			black(parent);
			black(sibling);
			grand = red(grand);
			afterAdd(grand);
			return;
		}

		// parent��sibling�Ǻ�ɫ��
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

		// ��parent��Ϊ���ڵ�
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // ���ڵ�
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

		// ��parent��Ϊ���ڵ�
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
	 * ��ȡ�ڵ����ɫ
	 */
	public boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode) node).color;
	}

	/**
	 * �жϽڵ��Ƿ��Ǻ�ɫ
	 */
	public boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}

	/**
	 * �жϽڵ��Ƿ��Ǻ�ɫ
	 */
	public boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}

	/**
	 * �Խڵ����Ⱦɫ
	 * 
	 * @return ����Ⱦɫ��Ľڵ�
	 */
	public RBNode<E> color(Node<E> node, boolean color) {
		if (node != null)
			((RBNode<E>) node).color = color;
		return ((RBNode<E>) node);
	}

	/**
	 * ���ڵ�Ⱦ�ɺ�ɫ
	 */
	public RBNode<E> black(Node<E> node) {
		return color(node, BLACK);
	}

	/**
	 * ���ڵ�Ⱦ�ɺ�ɫ
	 */
	public RBNode<E> red(Node<E> node) {
		return color(node, RED);
	}

	public static class RBNode<E> extends Node<E> {
		// ����ӵĽڵ�Ĭ���Ǻ�ɫ��������������1��2��3��5�������ʣ����ܱ�֤����4һ������
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
