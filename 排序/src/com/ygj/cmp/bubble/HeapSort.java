package com.ygj.cmp.bubble;

import com.ygj.Sort;
import com.ygj.tools.Integers;

/**
 * ������ѡ��������Ż�
 */
public class HeapSort extends Sort<Integer> {
	private int heapSize;

	@Override
	protected void sort() {
		heapSize = array.length;
		// �������Ѻ�õ������е���Ԫ��Ϊ���
		heapify2();
		// ��һ��Ԫ�غ����һ��Ԫ�ؽ���λ�ã�Ȼ��ִ�����˲���
		while (heapSize > 1) {
			int temp = array[0];
			array[0] = array[--heapSize];
			array[heapSize] = temp;
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
		int value = array[index];
		int half = heapSize >> 1;
		while (index < half) {
			int childIndex = (index << 1) + 1;
			int childValue = array[childIndex];

			int rightIndex = childIndex + 1;
			if (rightIndex < heapSize && (array[rightIndex] > childValue)) {
				childIndex = rightIndex;
				childValue = array[rightIndex];
			}
			if (value >= childValue)
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
		int value = array[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			int parentValue = array[parentIndex];
			// ���ڸ��ڵ��Ԫ�أ�����λ��
			if (value <= parentValue)
				break;
			array[index] = parentValue;
			index = parentIndex;
		}
		array[index] = value;
	}

}
