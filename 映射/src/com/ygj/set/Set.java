package com.ygj.set;

//Î¨Ò» ÎÞÐò
public interface Set<E> {

	int size();

	boolean isEmpty();

	void clear();

	boolean contains(E element);

	void add(E element);

	void remove(E element);

	void traversal(Vistor<E> vistor);

	public static abstract class Vistor<E> {
		boolean stop;
		public abstract boolean vistor(E element);
	}

}
