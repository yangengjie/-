package com.ygj.set;

import java.util.TreeMap;

import com.ygj.tree.BinaryTree.Visitor;
import com.ygj.tree.RBTree;

public class TreeSet<E> implements Set<E> {
	private RBTree<E> rbTree = new RBTree<>();

	@Override
	public int size() {
		return rbTree.size();
	}

	@Override
	public boolean isEmpty() {
		return rbTree.isEmpty();
	}

	@Override
	public void clear() {
		rbTree.clear();
	}

	@Override
	public boolean contains(E element) {
		return rbTree.contains(element);
	}

	@Override
	public void add(E element) {
		// ������д����������ʱ���ڲ��Ѿ����ظ�Ԫ�����˸��Ǵ���
		rbTree.add(element);
	}

	@Override
	public void remove(E element) {
		rbTree.remove(element);
	}

	@Override
	public void traversal(Vistor<E> vistor) {
		if (vistor == null)
			return;
		 
		rbTree.inorder(new Visitor<E>() {

			@Override
			public boolean visit(E e) {
				return vistor.vistor(e);
			}
		});
	}

}
