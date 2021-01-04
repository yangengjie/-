package com.ygj.list;

public interface List<E> {
	int size(); // 元素的数量

	boolean isEmpty(); // 元素是否为空

	boolean contains(E element); // 否包含某个元素

	void add(E element); // 添加元素到最后面

	void add(int index, E element); // 向index位置添加元素

	E get(int index); // 获取index位置的元素

	void set(int index, E element); // 设置index位置的元素

	E remove(int index); // 删除某个位置的元素

	E remove(E element); // 删除某个元素

	int indexOf(E element); // 获取某个元素的index

	void clear(); // 清空元素
}
