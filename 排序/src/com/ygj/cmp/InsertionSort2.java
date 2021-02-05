package com.ygj.cmp;

import com.ygj.Sort;

/**
 * 插入排序的优化：元素位置交换 改成 元素的挪动
 * 
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			T v = array[cur];
			while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
				array[cur] = array[cur - 1];
				cur--;
			}
			array[cur] = v;
		}
	}

}
