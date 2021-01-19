package com.ygj.map;

public interface Map<K, V> {

	int size();

	boolean isEmpty();

	void clear();

	/**
	 * @param key
	 * @param value
	 * @return key��ͬ�Ḳ�Ǿɵ�value�����ر����ǵ�value
	 */
	V put(K key, V value);

	V get(K key);

	V remove(K key);

	boolean containsKey(K key);

	boolean containsValue(V value);

	void traversal(Visitor<K, V> visitor);

	public static abstract class Visitor<K, V> {
		boolean stop;

		public abstract boolean visitor(K key, V value,boolean color);
	}

}
