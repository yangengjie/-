package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * ��BubbleSort1��֪���۸����������Ƿ����򣬶������ɨ���жϽ����� ���ԶԴ˽����Ż�������������������ľͲ�������ѭ����
 *
 */
public class BubbleSort2 extends Sort<Integer> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
				if (array[begin - 1] > array[begin]) {
					int temp = array[begin - 1];
					array[begin - 1] = array[begin];
					array[begin] = temp;
					sorted = false;
				}
			}
			if (sorted)
				break;
		}
	}

}
