package com.ygj;

public class RBTree<E> extends BST<E> {
	public static final boolean RED = true;
	public static final boolean BLACK = false;
	
	
	

	public static class RBNode<E> extends Node<E> {
		public boolean color;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}

	}

}
