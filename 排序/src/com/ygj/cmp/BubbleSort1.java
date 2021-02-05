package com.ygj.cmp;

import com.ygj.Sort;
import com.ygj.tools.Integers;

/**
 * 冒泡排序(基础版): 从头开始比对每一对相邻的元素，如果第一个比第二个大，则交换位置 经过第一轮交换后最大的值出现在末尾
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

	@Override
	public void sort() {
		int len = array.length;
		for (int end = len - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				// 交换位置
				if (cmp(begin - 1, begin) > 0) {
					swap(begin - 1, begin);
				}
			}
		}
	}

}
