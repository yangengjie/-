package com.ygj.cmp;

import com.ygj.Sort;

/**
 * ��BubbleSort1��֪���۸����������Ƿ����򣬶������ɨ���жϽ����� ���ԶԴ˽����Ż�������������������ľͲ�������ѭ����
 *
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
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
