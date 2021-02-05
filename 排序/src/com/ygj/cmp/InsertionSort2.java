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
				//头部有序数据比待插入的元素大的元素向后挪动1个位置
				array[cur] = array[cur - 1];
				cur--;
			}
			//cur就是最终要插入的位置
			array[cur] = v;
		}
	}

}
