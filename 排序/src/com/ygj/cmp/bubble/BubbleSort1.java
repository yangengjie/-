package com.ygj.cmp.bubble;

import com.ygj.Sort;

/**
 * ð������(������):
 * ��ͷ��ʼ�ȶ�ÿһ�����ڵ�Ԫ�أ������һ���ȵڶ������򽻻�λ��
 * ������һ�ֽ���������ֵ������ĩβ
 */
public class BubbleSort1 extends Sort<Integer> {

	@Override
	public void sort() {
		int len = array.length;
		for (int end = len - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				// ����λ��
				if (array[begin - 1] > array[begin]) {
					int temp = array[begin - 1];
					array[begin - 1] = array[begin];
					array[begin] = temp;
				}
			}
		}
	}

}
