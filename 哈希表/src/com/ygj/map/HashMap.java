package com.ygj.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import javax.security.auth.kerberos.KerberosKey;

import com.ygj.printer.BinaryTreeInfo;
import com.ygj.printer.BinaryTrees;

public class HashMap<K, V> implements Map<K, V> {
	private int size;
	public static final boolean RED = true;
	public static final boolean BLACK = false;

	private Node<K, V>[] table;
	private static final int DEFAULT_CAPACITY = 1 << 4;
	private static final float LOAD_FACTOR = 0.75f;

	public HashMap() {
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

	/**
	 * 扩容
	 */
	private void resize() {
		// 装填因子小于等于0.75不进行扩容
		if (size * 1.0f / table.length <= LOAD_FACTOR)
			return;

		Node<K, V>[] oldTable = table;
		table = new Node[table.length << 1];
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < oldTable.length; i++) {
			Node<K, V> root = oldTable[i];
			if (root == null)
				continue;
			queue.offer(root);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();

				if (node.left != null)
					queue.offer(node.left);

				if (node.right != null)
					queue.offer(node.right);

				// 挪动节点到新的哈希表中，
				// 注意挪动代码需要放到最后，否则会导致获得node.left和node.right是错乱的
				moveNode(node);
			}

		}
	}

	/**
	 * 挪动节点到新的哈希表中 类似于添加元素的逻辑
	 * 
	 * @param node
	 */
	private void moveNode(Node<K, V> newNode) {
		// 注意重置newNode的属性
		newNode.parent = null;
		newNode.left = null;
		newNode.right = null;
		newNode.color = RED;

		// 首先计算出添加到那个桶中
		int index = index(newNode);
		// 取出对应index位置的红黑树的根节点
		Node<K, V> root = table[index];
		// 说明添加进来的是根节点
		if (root == null) {
			root = newNode;
			table[index] = root;
			afterPut(root);
			return;
		}

		// 添加到红黑树上
		Node<K, V> node = root;
		Node<K, V> parent = root;
		int cmp = 0;
		int h1 = newNode.hash;
		K k1 = newNode.key;

		while (node != null) {
			int h2 = node.hash;
			K k2 = node.key;
			parent = node;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
					&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
			} else {
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			}

			if (cmp > 0)
				node = node.right;
			else if (cmp < 0)
				node = node.left;
		}

		// 记得维护parent信息
		newNode.parent = parent;
		if (cmp > 0)
			parent.right = newNode;
		else if (cmp < 0)
			parent.left = newNode;

		afterPut(newNode);
	}

	@Override
	public V put(K key, V value) {
		resize();
		// 添加之前，需要使用key来计算得到索引
		int index = index(key);
		// 数组中存入的元素是不同红黑树的根节点，取出index位置对应的红黑树的根节点
		Node<K, V> root = table[index];
		if (root == null) { // 添加的第一个节点
			root = createNode(key, value, null);
			table[index] = root;
			size++;
			afterPut(root);
			return null;
		}
		// 来到这里说明发生了哈希冲突（key1和key2计算出来的索引相同）,这时候就需要向红黑树中添加节点
		int cmp = 0;
		Node<K, V> node = root;
		Node<K, V> parent = root;
		int h1 = key == null ? 0 : key.hashCode();
		K k1 = key;
		// 记录是否已经扫描过整棵树，避免重复扫描
		boolean isSearched = false;
		// 记录查找结果
		Node<K, V> result = null;
		while (node != null) {
			// 由于在HashMap中并不要求key具有可比较性，该如何进行比较呢？
//			cmp = compare(key, k1, node.key, node.hash);
			K k2 = node.key;
			int h2 = node.hash;
			// 比较hash
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} else if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
					&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {

			} else if (isSearched) {// 表示已经扫描过了
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			} else {// searched == false; 还没有扫描，然后再根据内存地址大小决定左右
				if ((node.left != null && (result = node(node.left, k1)) != null)
						|| (node.right != null && (result = node(node.right, k1)) != null)) {
					// 找到的是Node是result不再是node
					node = result;
					cmp = 0;
				} else {
					isSearched = true;
					// 如果扫描整棵树也没有找到元素，则使用内存地址进行比较
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

		Node<K, V> newNode = createNode(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else if (cmp < 0) {
			parent.left = newNode;
		}
		afterPut(newNode);
		size++;
		return null;
	}

	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		return new Node<>(key, value, parent);
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
		Node<K, V> willNode = node;
		V oldValue = node.value;
		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到前驱节点后继节点 然后覆盖节点的值
			Node<K, V> preNode = precursor(node);
			node.key = preNode.key;
			node.value = preNode.value;
			// 注意替换hash
			node.hash = preNode.hash;
			// 删除前驱节点或后继节点
			// 前驱节点或后继节点肯定是度为1 或0的节点
			node = preNode;
		}
		int index = index(node);
		// 删除度为1或0的节点
		Node<K, V> replacement = node.left != null ? node.left : node.right;
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.isLeftChild()) {
				node.parent.left = replacement;
			} else if (node.isRightChild()) {
				node.parent.right = replacement;
			} else {
				// 根节点
				table[index] = replacement;
			}
			fixAfterRemove(node, replacement);
		} else if (node.parent == null) {
			table[index] = null;
		} else {
			// 叶子节点
			if (node.isLeftChild()) {
				node.parent.left = null;
			} else if (node.isRightChild()) {
				node.parent.right = null;
			}
			fixAfterRemove(node, null);
		}

		size--;

		afterRemove(willNode, node);

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
		// 遍历数组中的所有树
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

	// 打印红黑树
	public void print() {
		if (size == 0)
			return;
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("【index = " + i + "】");
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

	/**
	 * 删除节点后维护双向链表中的元素
	 * 
	 * @param willNode 要删除的节点
	 * @param node     真正删除的节点
	 */
	protected void afterRemove(Node<K, V> willNode, Node<K, V> node) {

	}

	private void fixAfterRemove(Node<K, V> node, Node<K, V> replacement) {

		// 如果删除的红色节点 不需处理
		if (isRed(node))
			return;

		// 来到这里删除是BLACK节点
		// 删除有一个RED子节点的
		if (isRed(replacement)) {
			// 将替代的子节点染成BLACK
			black(replacement);
			return;
		}

		// 删除BLACK叶子节点
		Node<K, V> parent = node.parent;
		if (parent == null)// 删除的是根节点
			return;

		// 找兄弟节点
		boolean isLeft = parent.left == null || node.isLeftChild();
		Node<K, V> sibling = isLeft ? parent.right : parent.left;

		if (isLeft) {
			// 转换成兄弟节点为BLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateLeft(parent);
				sibling = parent.right;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 不能借
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// 父节点向下合并，可能还会造成下溢，将parent作为删除的节点处理
				if (isParentBlack)
					fixAfterRemove(parent, null);
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
			// 转换成兄弟节点为BLACK
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateRight(parent);
				sibling = parent.left;
			}
			if (isBlack(sibling.left) && isBlack(sibling.right)) { // 不能借
				boolean isParentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				// 父节点向下合并，可能还会造成下溢，将parent作为删除的节点处理
				if (isParentBlack)
					fixAfterRemove(parent, null);
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
	 * @return 返回key对应的节点
	 */
	private Node<K, V> node(K key) {
		Node<K, V> root = table[index(key)];
		return root == null ? null : node(root, key);
	}

	private Node<K, V> node(Node<K, V> node, K k1) {
		int h1 = k1 == null ? 0 : k1.hashCode();
		int cmp = 0;
		// 存储查找结果
		Node<K, V> result = null;
		while (node != null) {
			int h2 = node.hash;
			K k2 = node.key;
			// 这里没有使用 int result=h1 - h2;然后比较result的正负
			// 原因很简单：哈希值可能为负值，所以h1 - h2 当h2为负值是result=h1 + h2;可能会造成溢出。
			// 那么比较就不准确了，这里直接比较h1和h2.
			// 先比较hash值
			if (h1 > h2)
				cmp = 1;
			else if (h1 < h2)
				cmp = -1;
			else if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (k1 != null && k2 != null && k1.getClass().equals(k2.getClass()) && k1 instanceof Comparable
					&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
//				cmp = ((Comparable) k1).compareTo(k2);
			} else {
				// 哈希值相同，但不equals且不具有比较性，这时候没其他办法比较，只能去扫描整棵树
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
//		// 比较哈希值
//		int result = h1 - h2;
//		if (result != 0)
//			return result;
//
//		// 来到这里说明哈希值相等
//		// 比较equals
//		if (Objects.equals(k1, k2))
//			return 0;
//
//		// 来到这里说明哈希值相等但不equals
//		// 比较类名
//		if (k1 != null && k2 != null) {
//			String k1Cls = k1.getClass().getName();
//			String k2Cls = k2.getClass().getName();
//			result = k1Cls.compareTo(k2Cls);
//			if (result != 0)
//				return result;
//
//			// 类名相同，则表示同种类型，且具有可比较性
//			if (k1 instanceof Comparable) {
//				return ((Comparable) k1).compareTo(k2);
//			}
//		}
//
//		// 来到这里的可能情况：k1和k2不可能同时为null 否则Objects.equals(k1,k2)必定返回true
//		// 1、k1 = null && k2 != null
//		// 2、k1 != null && k2 = null
//		// 3、同种类型且不具有可比较性
//		// 目前没有其他的比较方式了，这里只能使用内存地址进行比较了
//		return System.identityHashCode(k1) - System.identityHashCode(k2);
//	}

	/**
	 * @param key
	 * @return key对应的索引
	 */
	private int index(K key) {
		if (key == null)
			return 0;
		int hash = key.hashCode();
		return (hash ^ (hash >>> 16)) & (table.length - 1);
	}

	/**
	 * @param node
	 * @return 找出node对应的索引
	 */
	private int index(Node<K, V> node) {
		if (node == null)
			return 0;
		return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
	}

	/**
	 * //用于添加后的修复
	 * 
	 * @param node
	 */
	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		if (parent == null) { // 根节点
			black(node);
			return;
		}
		// 添加有12种
		// 4种parent为黑色，不需处理
		if (isBlack(parent))
			return;

		// 来到这里parent是RED，判断uncle节点是否红色
		Node<K, V> sibling = parent.sibling();
		Node<K, V> grand = parent.parent;
		if (isRed(sibling)) { // uncle节点为红色
			// 将parent、uncle染成黑色
			black(parent);
			black(sibling);
			// 将grand染成红色作为新添加的节点
			grand = red(grand);
			afterPut(grand);
			return;
		}

		// 来到这里uncle节点为黑色
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
			// 能来到这里说明，红黑树上所有节点中的key计算出来的索引都一样，
			// 所以root=table[index(grand)]，index(Node node)传入这一棵红黑树上的任意节点都可以
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
			// 能来到这里说明，红黑树上所有节点中的key计算出来的索引都一样，
			// 所以root=table[index(grand)]，index(Node node)传入这一棵红黑树上的任意节点都可以
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

	protected static class Node<K, V> {
		K key;
		V value;
		int hash;
		// 新添加的节点默认为RED
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
