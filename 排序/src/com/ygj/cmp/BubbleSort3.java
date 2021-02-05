package com.ygj.cmp;

import com.ygj.Sort;

/**
 * Bubble2�Ż�������ǰ���Ǹ���������������ģ�������������Ŀ����ԱȽϵͣ� �����Ż����ܲ������ԣ�
 *
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
				if (cmp(begin - 1, begin) > 0) {
					swap(begin - 1, begin);
					sortedIndex = begin;
				}
			}
			end = sortedIndex;
		}
	}

}
