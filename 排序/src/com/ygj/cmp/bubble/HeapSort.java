package com.ygj.cmp.bubble;

import com.ygj.Sort;
import com.ygj.tools.Integers;

/**
 * 堆排序：选择排序的优化
 */
public class HeapSort extends Sort<Integer> {
	private int heapSize;

	@Override
	protected void sort() {
		heapSize = array.length;
		// 批量建堆后得到数组中的首元素为最大
		heapify2();
		// 第一个元素和最后一个元素交换位置，然后执行下滤操作
		while (heapSize > 1) {
			int temp = array[0];
			array[0] = array[--heapSize];
			array[heapSize] = temp;
			// 对位置0的元素下滤 恢复二叉堆的性质
			siftDown(0);
		}
	}

	/**
	 * 批量建堆的方式二：自下而上的下滤 时间复杂度为高度之和=O(n)
	 */
	private void heapify2() {
		int half = heapSize >> 1;
		for (int i = half - 1; i >= 0; i--) {
			siftDown(i);
		}
	}

	/**
	 * 下滤
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
	 * 批量建堆实现方式一：自上而下的上滤 时间复杂度为深度之和=O(nlogn)
	 */
	private void heapify1() {
		for (int i = 1; i < heapSize; i++) {
			siftUp(i);
		}
	}

	/**
	 * 上滤
	 */
	private void siftUp(int index) {
		int value = array[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			int parentValue = array[parentIndex];
			// 大于父节点的元素，交换位置
			if (value <= parentValue)
				break;
			array[index] = parentValue;
			index = parentIndex;
		}
		array[index] = value;
	}

}
