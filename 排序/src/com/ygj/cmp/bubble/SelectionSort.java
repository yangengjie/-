package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * ѡ�����򣺴�ͷ��βɨ�裬�ҳ����ֵ��ĩβԪ�ؽ���λ�� ��һ��ɨ����������һ��Ԫ��Ϊ���ֵ
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
