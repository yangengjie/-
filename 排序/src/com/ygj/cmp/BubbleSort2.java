package com.ygj.cmp;

import com.ygj.Sort;

/**
 * 从BubbleSort1可知无论给定的数据是否有序，都会进行扫描判断交换， 可以对此进行优化，当发现数据是有序的就不再跳出循环。
 *
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				// 交换位置
				if (cmp(begin - 1, begin) > 0) {
					swap(begin-1,begin);
					sorted = false;
				}
			}
			if (sorted)
				break;
		}
	}

}
