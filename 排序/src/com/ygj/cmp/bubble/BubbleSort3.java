package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * Bubble2�Ż�������ǰ���Ǹ���������������ģ�������������Ŀ����ԱȽϵͣ� �����Ż����ܲ������ԣ�
 *
 */
public class BubbleSort3 extends Sort<Integer> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
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
