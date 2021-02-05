package com.ygj.cmp;

import com.ygj.Sort;
import com.ygj.tools.Integers;

/**
 * ������ѡ��������Ż�
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
	private int heapSize;

	@Override
	protected void sort() {
		heapSize = array.length;
		// �������Ѻ�õ������е���Ԫ��Ϊ���
		heapify2();
		// ��һ��Ԫ�غ����һ��Ԫ�ؽ���λ�ã�Ȼ��ִ�����˲���
		while (heapSize > 1) {
			swap(0, --heapSize);
			// ��λ��0��Ԫ������ �ָ�����ѵ�����
			siftDown(0);
		}
	}

	/**
	 * �������ѵķ�ʽ�������¶��ϵ����� ʱ�临�Ӷ�Ϊ�߶�֮��=O(n)
	 */
	private void heapify2() {
		int half = heapSize >> 1;
		for (int i = half - 1; i >= 0; i--) {
			siftDown(i);
		}
	}

	/**
	 * ����
	 * 
	 * @param index
	 */
	private void siftDown(int index) {
		T value = array[index];
		int half = heapSize >> 1;
		while (index < half) {
			int childIndex = (index << 1) + 1;
			T childValue = array[childIndex];

			int rightIndex = childIndex + 1;
			if (rightIndex < heapSize && (cmp(array[rightIndex], childValue) > 0)) {
				childIndex = rightIndex;
				childValue = array[rightIndex];
			}
			if (cmp(value, childValue) >= 0)
				break;
			array[index] = childValue;
			index = childIndex;
		}
		array[index] = value;
	}

	/**
	 * ��������ʵ�ַ�ʽһ�����϶��µ����� ʱ�临�Ӷ�Ϊ���֮��=O(nlogn)
	 */
	private void heapify1() {
		for (int i = 1; i < heapSize; i++) {
			siftUp(i);
		}
	}

	/**
	 * ����
	 */
	private void siftUp(int index) {
		T value = array[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			T parentValue = array[parentIndex];
			// ���ڸ��ڵ��Ԫ�أ�����λ��
			if (cmp(value, parentValue) <= 0)
				break;
			array[index] = parentValue;
			index = parentIndex;
		}
		array[index] = value;
	}
}
