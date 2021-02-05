package com.ygj.cmp;

import com.ygj.Sort;

/**
 *插入排序
 * @param <T>
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		//begin默认为1，index为0的认为是头部有序的，后面的元素是待排序的
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			//如果发现比前面的元素小就交换位置，直到遇到当前的元素大于前面的元素
			//最坏的情况是会和cur前面所有元素进行比较，并交换位置
			while (cur > 0 && cmp(cur, cur - 1) < 0) {
				swap(cur, cur - 1);
				cur--;
			}
		}
	}
}
