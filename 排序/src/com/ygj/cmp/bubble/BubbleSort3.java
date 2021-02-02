package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * Bubble2优化产生的前提是给定的数据是有序的，但是数据有序的可能性比较低， 所以优化性能并不明显，
 *
 */
public class BubbleSort3 extends Sort<Integer> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				// 交换位置
				if (array[begin - 1] > array[begin]) {
					int temp = array[begin - 1];
					array[begin - 1] = array[begin];
					array[begin] = temp;
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

}
