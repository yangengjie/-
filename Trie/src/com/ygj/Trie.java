package com.ygj;

import java.util.HashMap;

public class Trie<V> {
	private int size;
	private Node<V> root;

	// 返回前缀树中单词的数量
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
	 * @return 是否包含某个单词
	 */
	public boolean contains(String key) {
		Node<V> node = node(key);
		return node != null && node.isWord;
	}

	/**
	 * 返回key对应的value
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
	 * @return 返回旧的value
	 */
	public V add(String key, V value) {
		keyCheck(key);

		// 创建根节点
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

		// 单词已经存在
		if (node.isWord) {
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}

		// 新增一个单词
		node.isWord = true;
		node.value = value;
		size++;

		return null;
	}

	/**
	 * 删除前缀中中存储的单词str并返回对应的value
	 * 
	 * @param key
	 * @return
	 */
	public V remove(String key) {
		// 找到最后一个节点
		Node<V> node = node(key);
		// 如果不是单词的末尾不用处理
		if (node == null || !node.isWord)
			return null;

		size--;
		V oldValue = node.value;

		// 如果node有子节点，说明key是其他单词的一部分所以不能直接删除
		if (node.children != null && !node.children.isEmpty()) {
			node.isWord = false;
			node.value = null;
			return oldValue;
		}

		// 如果node没有子节点，需要从下向上进行删除
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
	 * 返回前缀树中是否存储有prefix前缀的单词
	 * 
	 * @param prefix
	 * @return
	 */
	public boolean startWith(String prefix) {
		Node<V> node = node(prefix);
		return node != null;
	}

	/**
	 * 返回key对应的Node
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
		// 当Node对应的是单词的末尾，将key对应的value存入Node中
		V value;
		// 是否是一个完整单词
		boolean isWord;

		public Node(Node<V> parent) {
			this.parent = parent;
		}
	}
}
