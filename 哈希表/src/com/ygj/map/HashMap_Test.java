package com.ygj.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import javax.security.auth.kerberos.KerberosKey;

import com.ygj.printer.BinaryTreeInfo;
import com.ygj.printer.BinaryTrees;

public class HashMap_Test<K, V> implements Map<K, V> {
	private int size;
	public static final boolean RED = true;
	public static final boolean BLACK = false;

	private Node<K, V>[] table;
	private static final int DEFAULT_CAPACITY = 1 << 4;

	public HashMap_Test() {
		table = new Node[DEFAULT_CAPACITY];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		if (size == 0)
			return;
		size = 0;
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	@Override
	public V put(K key, V value) {
		// ���֮ǰ����Ҫʹ��key������õ�����
		int index = index(key);
		// �����д����Ԫ���ǲ�ͬ������ĸ��ڵ㣬ȡ��indexλ�ö�Ӧ�ĺ�����ĸ��ڵ�
		Node<K, V> root = table[index];
		if (root == null) { // ��ӵĵ�һ���ڵ�
			root = new Node<>(key, value, null);
			table[index] = root;
			size++;
			afterPut(root);
			return null;
		}
		// ��������˵�������˹�ϣ��ͻ��key1��key2���������������ͬ��,��ʱ�����Ҫ����������ӽڵ�
		int cmp = 0;
		Node<K, V> node = root;
		Node<K, V> parent = root;
		// ��¼�Ƿ��Ѿ�ɨ����������������ظ�ɨ��
		boolean isSearched = false;
		// ��¼���ҽ��
		Node<K, V> result = null;
		K k1 = key;
		int h1 = key == null ? 0 : key.hashCode();
		while (node != null) {
			K k2 = node.key;
			if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (isSearched) {// ��ʾ�Ѿ�ɨ�����
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			} else {// searched == false; ��û��ɨ�裬Ȼ���ٸ����ڴ��ַ��С��������
				if ((node.left != null && (result = node(node.left, k1)) != null)
						|| (node.right != null && (result = node(node.right, k1)) != null)) {
					// �ҵ�����Node��result������node
					node = result;
					cmp = 0;
				} else {
					isSearched = true;
					// ���ɨ��������Ҳû���ҵ�Ԫ�أ���ʹ���ڴ��ַ���бȽ�
					cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
				}
			}
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				V oldValue = node.value;
				node.key = key;
				node.value = value;
				node.hash = h1;
				return oldValue;
			}
		}

		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		afterPut(newNode);
		size++;
		return null;
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node == null ? null : node.value;
	}

	@Override
	public V remove(K key) {
		Node<K, V> node = node(key);
		if (node == null)
			return null;
		V oldValue = node.value;
		if (node.hasTwoChildren()) { // ��Ϊ2�Ľڵ�
			// �ҵ�ǰ���ڵ��̽ڵ� Ȼ�󸲸ǽڵ��ֵ
			Node<K, V> preNode = precursor(node);
			node.key = preNode.key;
			node.value = preNode.value;
			// ע���滻hash
			node.hash = preNode.hash;
			// ɾ��ǰ���ڵ���̽ڵ�
			// ǰ���ڵ���̽ڵ�϶��Ƕ�Ϊ1 ��0�Ľڵ�
			node = preNode;
		}
		int index = index(node);
		// ɾ����Ϊ1��0�Ľڵ�
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.isLeftChild()) {
				node.parent.left = replacement;
			} else if (node.isRightChild()) {
				node.parent.right = replacement;
			} else {
				// ���ڵ�
				table[index] = replacement;
			}
			afterRemove(node, replacement);
		} else if (node.parent == null) {
			table[index] = null;
		} else {
			// Ҷ�ӽڵ�
			if (node.isLeftChild()) {
				node.parent.left = null;
			} else if (node.isRightChild()) {
				node.parent.right = null;
			}
			afterRemove(node, null);
		}

		size--;
		return oldValue;
	}

	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if (size == 0)
			return false;
		// ���������е�������
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> root = table[i];
			if (root == null)
				continue;
			queue.offer(root);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (Objects.equals(node.value, value))
					return true;
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (size == 0 || visitor == null)
			return;
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> root = table[i];
			if (root == null)
				continue;

			queue.offer(root);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (visitor.visitor(node.key, node.value, node.color))
					return;
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
		}
	}

	// ��ӡ�����
	public void print() {
		if (size == 0)
			return;
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("��index = " + i + "��");
			BinaryTrees.println(new BinaryTreeInfo() {
				@Override
				public Object string(Object node) {
					return node;
				}

				@Override
				public Object root() {
					return root;
				}

				@Override
				public Object right(Object node) {
					return ((Node<K, V>) node).right;
				}

				@Override
				public Object left(Object node) {
					return ((Node<K, V>) node).left;
				}
			});
			System.out.println("---------------------------------------------------");
		}
	}

	private void afterRemove(Node<K, V> node, Node<K, V> replacement) {

		// ���ɾ���ĺ�ɫ�ڵ� ���账��
		if (isRed(node))
			return;

		// ��������ɾ����BLACK�ڵ�
		// ɾ����һ��RED�ӽڵ��
		if (isRed(replacement)) {
			// ��������ӽڵ�Ⱦ��BLACK
			black(replacement);
			return;
		}

		// ɾ��BLACKҶ�ӽڵ�
		Node<K, V> parent = node.parent;
		if (parent == null)// ɾ�����Ǹ��ڵ�
			return;

		// ���ֵܽڵ�
		boolean isLeft = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = isLeft ? parent.right : parent.left;

		if (isLeft) {
			// ת�����ֵܽڵ�ΪBLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateLeft(parent);
				sibling = parent.right;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // ���ܽ�
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// ���ڵ����ºϲ������ܻ���������磬��parent��Ϊɾ���Ľڵ㴦��
				if (isParentBlack)
					afterRemove(parent, null);
			} else {

				if (isBlack(sibling.right)) { // RL
					rotateRight(sibling);
					sibling = parent.right;
				}
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.right);
				rotateLeft(parent);
			}
		} else {
			// ת�����ֵܽڵ�ΪBLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateRight(parent);
				sibling = parent.left;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // ���ܽ�
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// ���ڵ����ºϲ������ܻ���������磬��parent��Ϊɾ���Ľڵ㴦��
				if (isParentBlack)
					afterRemove(parent, null);
			} else {

				if (isBlack(sibling.left)) { // LR
					rotateLeft(sibling);
					sibling = parent.left;
				}
				color(sibling, colorOf(parent));
				black(parent);
				black(sibling.left);
				rotateRight(parent);
			}

		}

	}

	private Node<K, V> precursor(Node<K, V> node) {
		Node<K, V> leftNode = node.left;
		if (leftNode != null) { // node.left.right.right.....
			while (leftNode.right != null) {
				leftNode = leftNode.right;
			}
			return leftNode;

		} else { // node.parent.parent....
			while (node.parent != null && node.isLeftChild()) {
				node = node.parent;
			}
			return node.parent;
		}
	}

	/**
	 * @param key
	 * @return ����key��Ӧ�Ľڵ�
	 */
	private Node<K, V> node(K key) {
		Node<K, V> root = table[index(key)];
		return root == null ? null : node(root, key);
	}

	private Node<K, V> node(Node<K, V> node, K k1) {
		int cmp = 0;
		// �洢���ҽ��
		Node<K, V> result = null;
		while (node != null) {
			K k2 = node.key;
			if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else {
				// ��ϣֵ��ͬ������equals�Ҳ����бȽ��ԣ���ʱ��û�����취�Ƚϣ�ֻ��ȥɨ��������
				if (node.right != null && (result = node(node.right, k1)) != null) {
					return result;
				} else if (node.left != null && (result = node(node.left, k1)) != null) {
					return result;
				} else {
					return null;
				}
			}
			if (cmp == 0)
				return node;
			if (cmp > 0)
				node = node.right;
			else
				node = node.left;
		}
		return null;
	}

//	private int compare(K k1, int h1, K k2, int h2) {
//		// �ȽϹ�ϣֵ
//		int result = h1 - h2;
//		if (result != 0)
//			return result;
//
//		// ��������˵����ϣֵ���
//		// �Ƚ�equals
//		if (Objects.equals(k1, k2))
//			return 0;
//
//		// ��������˵����ϣֵ��ȵ���equals
//		// �Ƚ�����
//		if (k1 != null && k2 != null) {
//			String k1Cls = k1.getClass().getName();
//			String k2Cls = k2.getClass().getName();
//			result = k1Cls.compareTo(k2Cls);
//			if (result != 0)
//				return result;
//
//			// ������ͬ�����ʾͬ�����ͣ��Ҿ��пɱȽ���
//			if (k1 instanceof Comparable) {
//				return ((Comparable) k1).compareTo(k2);
//			}
//		}
//
//		// ��������Ŀ��������k1��k2������ͬʱΪnull ����Objects.equals(k1,k2)�ض�����true
//		// 1��k1 = null && k2 != null
//		// 2��k1 != null && k2 = null
//		// 3��ͬ�������Ҳ����пɱȽ���
//		// Ŀǰû�������ıȽϷ�ʽ�ˣ�����ֻ��ʹ���ڴ��ַ���бȽ���
//		return System.identityHashCode(k1) - System.identityHashCode(k2);
//	}

	/**
	 * @param key
	 * @return key��Ӧ������
	 */
	private int index(K key) {
		if (key == null)
			return 0;
		int hash = key.hashCode();
		return (hash ^ (hash >>> 16)) & (table.length - 1);
	}

	/**
	 * @param node
	 * @return �ҳ�node��Ӧ������
	 */
	private int index(Node<K, V> node) {
		if (node == null)
			return 0;
		return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
	}

	/**
	 * //������Ӻ���޸�
	 * 
	 * @param node
	 */
	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		if (parent == null) { // ���ڵ�
			black(node);
			return;
		}
		// �����12��
		// 4��parentΪ��ɫ�����账��
		if (isBlack(parent))
			return;

		// ��������parent��RED���ж�uncle�ڵ��Ƿ��ɫ
		Node<K, V> sibling = parent.sibling();
		Node<K, V> grand = parent.parent;
		if (isRed(sibling)) { // uncle�ڵ�Ϊ��ɫ
			// ��parent��uncleȾ�ɺ�ɫ
			black(parent);
			black(sibling);
			// ��grandȾ�ɺ�ɫ��Ϊ����ӵĽڵ�
			grand = red(grand);
			afterPut(grand);
			return;
		}

		// ��������uncle�ڵ�Ϊ��ɫ
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
				red(grand);
				rotateRight(grand);
			} else { // LR
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
			} else { // RR
				black(parent);
				red(grand);
				rotateLeft(grand);
			}
		}

	}

	private void rotateLeft(Node<K, V> grand) { // RR
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;

		grand.right = child;
		parent.left = grand;

		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			// ����������˵��������������нڵ��е�key���������������һ����
			// ����root=table[index(grand)]��index(Node node)������һ�ú�����ϵ�����ڵ㶼����
			table[index(grand)] = parent;
		}

		grand.parent = parent;
		if (child != null)
			child.parent = grand;
	}

	private void rotateRight(Node<K, V> grand) { // LL
		Node<K, V> parent = grand.left;
		Node<K, V> child = parent.right;

		grand.left = child;
		parent.right = grand;

		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			// ����������˵��������������нڵ��е�key���������������һ����
			// ����root=table[index(grand)]��index(Node node)������һ�ú�����ϵ�����ڵ㶼����
			table[index(grand)] = parent;
		}

		grand.parent = parent;
		if (child != null)
			child.parent = grand;
	}

	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}

	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}

	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}

	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}

	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}

	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node != null)
			node.color = color;
		return node;
	}

	private static class Node<K, V> {
		K key;
		V value;
		int hash;
		// ����ӵĽڵ�Ĭ��ΪRED
		boolean color = RED;

		Node<K, V> parent;
		Node<K, V> left;
		Node<K, V> right;

		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
			this.hash = key == null ? 0 : key.hashCode();
		}

		public boolean isLeftChild() {
			return parent != null && this.equals(parent.left);
		}

		public boolean isRightChild() {
			return parent != null && this.equals(parent.right);
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {
			return left != null && right != null;
		}

		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			else if (isRightChild()) {
				return parent.left;
			}
			return null;
		}

		@Override
		public String toString() {
			return "K" + "_" + key + "_v_" + value;
		}
	}

}
