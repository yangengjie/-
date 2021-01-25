package com.ygj.set;

import com.ygj.map.LinkedHashMap;
import com.ygj.map.Map;

public class LinkedHashSet<E> implements Set<E> {
	private LinkedHashMap<E, Object> map = new LinkedHashMap<>();
	private static final Object PRESENT = new Object();

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean contains(E element) {
		return map.containsKey(element);
	}

	@Override
	public void add(E element) {
		map.put(element, PRESENT);
	}

	@Override
	public void remove(E element) {
		map.remove(element);
	}

	@Override
	public void traversal(Vistor<E> vistor) {
		map.traversal(new Map.Visitor<E, Object>() {

			@Override
			public boolean visitor(E key, Object value, boolean color) {
				if (vistor != null)
					vistor.vistor(key);
				return false;
			}
		});
	}

}
