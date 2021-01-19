package com.ygj.set;

import com.ygj.list.LinkedList;

public class ListSet<E> implements Set<E> {
	private LinkedList<E> list = new LinkedList<>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		// 由于Set中元素不能重复
		int index = list.indexOf(element);
		if (index == -1) {
			list.add(element);
		} else {
			list.set(index, element);
		}
	}

	@Override
	public void remove(E element) {
		int index = list.indexOf(element);
		if (index != -1)
			list.remove(index);

	}

	@Override
	public void traversal(Vistor<E> vistor) {
		if (vistor == null)
			return;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (vistor.stop)
				break;
			vistor.stop=vistor.vistor(list.get(i));
		}
	}

}
