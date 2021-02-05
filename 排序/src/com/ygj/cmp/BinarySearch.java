package com.ygj.cmp;

import java.util.function.IntPredicate;

/**
 * ���ֲ���
 */
public class BinarySearch {

	/**
	 * @return ����Ԫ���������λ��
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
			} else { // ��ʾv==array[mid]
				return mid;
			}
		}
		return -1;
	}

	/**
	 * @return ����Ԫ��v�Ĳ���λ��
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
