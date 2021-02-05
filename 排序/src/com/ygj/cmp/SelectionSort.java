package com.ygj.cmp;

import com.ygj.Sort;

/**
 * ѡ�����򣺴�ͷ��βɨ�裬�ҳ����ֵ��ĩβԪ�ؽ���λ�� ��һ��ɨ����������һ��Ԫ��Ϊ���ֵ
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, maxIndex) > 0) {
					maxIndex = begin;
				}
			}
			swap(maxIndex,end);
		}
	}

}
