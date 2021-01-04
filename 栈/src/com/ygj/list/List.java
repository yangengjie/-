package com.ygj.list;

public interface List<E> {
	int size(); // Ԫ�ص�����

	boolean isEmpty(); // Ԫ���Ƿ�Ϊ��

	boolean contains(E element); // �����ĳ��Ԫ��

	void add(E element); // ���Ԫ�ص������

	void add(int index, E element); // ��indexλ�����Ԫ��

	E get(int index); // ��ȡindexλ�õ�Ԫ��

	void set(int index, E element); // ����indexλ�õ�Ԫ��

	E remove(int index); // ɾ��ĳ��λ�õ�Ԫ��

	E remove(E element); // ɾ��ĳ��Ԫ��

	int indexOf(E element); // ��ȡĳ��Ԫ�ص�index

	void clear(); // ���Ԫ��
}
