package com.ygj.cmp;

import com.ygj.Sort;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
	private T[] leftArray;

	@Override
	protected void sort() {
		leftArray =  (T[]) new Comparable[array.length >> 1];
		sort(0, array.length);
	}

	private void sort(int begin, int end) {
		if (end - begin < 2)
			return;
		int mid = (begin + end) >> 1;
		sort(begin, mid);
		sort(mid, end);
		merge(begin, mid, end);
	}

	private void merge(int begin, int mid, int end) {
		// li le ri re ai
		int li = 0, le = mid - begin;
		int ri = mid, re = end;
		int ai = begin;
		// �����������
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}

		// ��������Ƚ������ұ߾Ͳ��ù���
		while (li < le) {
			//��������ri>re�����ұ��������������������η���array��
			if (ri < re && cmp(leftArray[li], array[ri]) > 0) {
				array[ai++] = array[ri++];
			} else { // leftArray[li]>=array[ri]
				array[ai++] = leftArray[li++];
			}
		}
	}

}
