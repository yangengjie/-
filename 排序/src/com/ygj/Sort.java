package com.ygj;

public abstract class Sort<T> {
	protected T[] array;

	public void sort(T[] array) {
		if (array == null || array.length < 2)
			return;
		this.array = array;
		sort();
	}

	protected abstract void sort();
}
