package com.ygj.cmp;

import com.ygj.Sort;

/**
 * ����������Ż������ַ����Ҳ����λ�ã����ٱȽϴ�����
 * 
 * @param <T>
 */
public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			//�ҵ�beginԪ��Ҫ�����λ��
			int insertIndex = search(begin);
			T v = array[begin];
			//������v��Ԫ�����Ų��һλ
			for (int i = begin; i > insertIndex; i--) {
				array[i] = array[i - 1];
			}
			//��Ԫ�ز��뵽���յ�λ��
			array[insertIndex] = v;
		}
	}

	/**
	 * @param index ����end
	 * @return ���ز����λ��
	 */
	private int search(int index) {
		int begin = 0;
		int end = index;
		T v = array[index];
		while (end > begin) {
			int mid = (begin + end) >> 1;
			if (cmp(v, array[mid]) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}

}
