package com.ygj;

public interface Heap<E> {

	int size(); //元素的数量
	
	boolean isEmpty();//是否为空
	
	void clear();//清空元素
	
	void add(E element);//添加元素
	
	E get();//获取堆顶元素
	
	E remove();//删除堆顶元素
	
	E replace(E element);//删除堆顶元素并新增一个新的元素
}
