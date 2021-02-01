package com.ygj;

import java.util.HashMap;

public class Trie<V> {
	private int size;
	private Node<V> root;

	// ����ǰ׺���е��ʵ�����
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		size = 0;
	}

	/**
	 * @param key
	 * @return �Ƿ����ĳ������
	 */
	public boolean contains(String key) {
		Node<V> node = node(key);
		return node != null && node.isWord;
	}

	/**
	 * ����key��Ӧ��value
	 * 
	 * @param key
	 * @return
	 */
	public V get(String key) {
		Node<V> node = node(key);
		return node != null && node.isWord ? node.value : null;
	}

	/**
	 * @param key
	 * @param value
	 * @return ���ؾɵ�value
	 */
	public V add(String key, V value) {
		keyCheck(key);

		// �������ڵ�
		if (root == null)
			root = new Node<>(null);

		int len = key.length();
		Node<V> node = root;
		for (int i = 0; i < len; i++) {
			if (node.children == null)
				node.children = new HashMap<>();
			char c = key.charAt(i);
			Node<V> childeNode = node.children.get(c);
			if (childeNode == null) {
				childeNode = new Node<>(node);
				childeNode.character = c;
				node.children.put(c, childeNode);
			}
			node = childeNode;
		}

		// �����Ѿ�����
		if (node.isWord) {
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}

		// ����һ������
		node.isWord = true;
		node.value = value;
		size++;

		return null;
	}

	/**
	 * ɾ��ǰ׺���д洢�ĵ���str�����ض�Ӧ��value
	 * 
	 * @param key
	 * @return
	 */
	public V remove(String key) {
		// �ҵ����һ���ڵ�
		Node<V> node = node(key);
		// ������ǵ��ʵ�ĩβ���ô���
		if (node == null || !node.isWord)
			return null;

		size--;
		V oldValue = node.value;

		// ���node���ӽڵ㣬˵��key���������ʵ�һ�������Բ���ֱ��ɾ��
		if (node.children != null && !node.children.isEmpty()) {
			node.isWord = false;
			node.value = null;
			return oldValue;
		}

		// ���nodeû���ӽڵ㣬��Ҫ�������Ͻ���ɾ��
		Node<V> parent = null;
		while ((parent = node.parent) != null) {
			parent.children.remove(node.character);
			if (parent.isWord || !parent.children.isEmpty())
				break;
			node = parent;
		}

		return oldValue;
	}

	/**
	 * ����ǰ׺�����Ƿ�洢��prefixǰ׺�ĵ���
	 * 
	 * @param prefix
	 * @return
	 */
	public boolean startWith(String prefix) {
		Node<V> node = node(prefix);
		return node != null;
	}

	/**
	 * ����key��Ӧ��Node
	 * 
	 * @param key
	 * @return
	 */
	private Node<V> node(String key) {
		keyCheck(key);
		Node<V> node = root;
		int len = key.length();
		for (int i = 0; i < len; i++) {
			if (node == null || node.children == null || node.children.isEmpty())
				return null;
			char c = key.charAt(i);
			node = node.children.get(c);
		}
		return node;
	}

	private void keyCheck(String key) {
		if (key == null || key.length() == 0)
			throw new IllegalArgumentException("key must be not null");
	}

	private static class Node<V> {
		Node<V> parent;
		HashMap<Character, Node<V>> children;
		Character character;
		// ��Node��Ӧ���ǵ��ʵ�ĩβ����key��Ӧ��value����Node��
		V value;
		// �Ƿ���һ����������
		boolean isWord;

		public Node(Node<V> parent) {
			this.parent = parent;
		}
	}
}
