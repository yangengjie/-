package com.ygj.cmp;

import com.ygj.Sort;

/**
 * Bubble2优化产生的前提是给定的数据是有序的，但是数据有序的可能性比较低， 所以优化性能并不明显，
 *
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				// 交换位置
				if (cmp(begin - 1, begin) > 0) {
					swap(begin - 1, begin);
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

}
