package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * 选择排序：从头到尾扫描，找出最大值和末尾元素交换位置 第一轮扫描结束后，最后一个元素为最大值
 */
public class SelectionSort extends Sort<Integer> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] > array[maxIndex]) {
					maxIndex = begin;
				}
			}
			int temp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = temp;
		}
	}

}
