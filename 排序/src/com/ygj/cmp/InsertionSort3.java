package com.ygj.cmp;

import com.ygj.Sort;

/**
 * 插入排序的优化：二分法查找插入的位置（减少比较次数）
 * 
 * @param <T>
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			//找到begin元素要插入的位置
			int insertIndex = search(begin);
			T v = array[begin];
			//将大于v的元素向后挪动一位
			for (int i = begin; i > insertIndex; i--) {
				array[i] = array[i - 1];
			}
			//将元素插入到最终的位置
			array[insertIndex] = v;
		}
	}

	/**
	 * @param index 等于end
	 * @return 返回插入的位置
	 */
	private int search(int index) {
		int begin = 0;
		int end = index;
		T v = array[index];
		while (end > begin) {
			int mid = (begin + end) >> 1;
			if (cmp(v, array[mid]) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}

}
