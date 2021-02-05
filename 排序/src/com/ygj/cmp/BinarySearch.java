package com.ygj.cmp;

import java.util.function.IntPredicate;

/**
 * 二分查找
 */
public class BinarySearch {

	/**
	 * @return 返回元素在数组的位置
	 */
	public int indexOf(int[] array, int v) {
		if (array == null || array.length == 0)
			return -1;
		int start = 0;
		int end = array.length;
		while (end > start) {
			int mid = (start + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else if (v > array[mid]) {
				start = mid + 1;
			} else { // 表示v==array[mid]
				return mid;
			}
		}
		return -1;
	}

	/**
	 * @return 返回元素v的插入位置
	 */
	public int search(int[] array, int v) {
		if (array == null || array.length == 0)
			return -1;
		int start = 0;
		int end = array.length;

		while (end > start) {
			int mid = (start + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else { //v >= array[mid]
				start = mid + 1;
			}
		}

		return start;
	}

}
