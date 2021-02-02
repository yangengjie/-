package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * 冒泡排序(基础版):
 * 从头开始比对每一对相邻的元素，如果第一个比第二个大，则交换位置
 * 经过第一轮交换后最大的值出现在末尾
 */
public class BubbleSort1 extends Sort<Integer> {

	@Override
	public void sort() {
		int len = array.length;
		for (int end = len - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				// 交换位置
				if (array[begin - 1] > array[begin]) {
					int temp = array[begin - 1];
					array[begin - 1] = array[begin];
					array[begin] = temp;
				}
			}
		}
	}

}
