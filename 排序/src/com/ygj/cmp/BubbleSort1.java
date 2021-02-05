package com.ygj.cmp;

import com.ygj.Sort;
import com.ygj.tools.Integers;

/**
 * ð������(������): ��ͷ��ʼ�ȶ�ÿһ�����ڵ�Ԫ�أ������һ���ȵڶ������򽻻�λ�� ������һ�ֽ���������ֵ������ĩβ
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {

	@Override
	public void sort() {
		int len = array.length;
		for (int end = len - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
				if (cmp(begin - 1, begin) > 0) {
					swap(begin - 1, begin);
				}
			}
		}
	}

}
