package com.ygj;

public interface Heap<E> {

	int size(); //Ԫ�ص�����
	
	boolean isEmpty();//�Ƿ�Ϊ��
	
	void clear();//���Ԫ��
	
	void add(E element);//���Ԫ��
	
	E get();//��ȡ�Ѷ�Ԫ��
	
	E remove();//ɾ���Ѷ�Ԫ��
	
	E replace(E element);//ɾ���Ѷ�Ԫ�ز�����һ���µ�Ԫ��
}
