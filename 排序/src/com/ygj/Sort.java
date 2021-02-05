package com.ygj;

import java.text.DecimalFormat;

import com.ygj.cmp.BubbleSort1;
import com.ygj.cmp.BubbleSort2;
import com.ygj.cmp.BubbleSort3;
import com.ygj.cmp.HeapSort;
import com.ygj.cmp.InsertionSort1;
import com.ygj.cmp.InsertionSort2;
import com.ygj.cmp.InsertionSort3;
import com.ygj.cmp.SelectionSort;

public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort> {
	protected T[] array;
	private long time;
	private long cmpCount;
	private long swapCount;
	private DecimalFormat ft = new DecimalFormat("#.00");

	public void sort(T[] array) {
		if (array == null || array.length < 2)
			return;
		this.array = array;
		long start = System.currentTimeMillis();
		sort();
		time = System.currentTimeMillis() - start;
	}

	protected abstract void sort();

	protected int cmp(int i1, int i2) {
		return cmp(array[i1], array[i2]);
	}

	protected int cmp(T t1, T t2) {
		cmpCount++;
		return t1.compareTo(t2);
	}

	protected void swap(int i1, int i2) {
		swapCount++;
		T temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	@Override
	public String toString() {
		String timeStr = "��ʱ��" + (time / 1000.0) + "s(" + time + "ms)";
		String compareCountStr = "�Ƚϣ�" + numberString(cmpCount);
		String swapCountStr = "������" + numberString(swapCount);
		String stableStr = "�ȶ��ԣ�" + isStable();
		return "��" + getClass().getSimpleName() + "��\n" + stableStr + " \t" + timeStr + " \t" + compareCountStr + "\t "
				+ swapCountStr + "\n" + "------------------------------------------------------------------";
	}

	private String numberString(long number) {
		if (number < 10000)
			return "" + number;
		if (number < 100000000)
			return ft.format(number / 10000.0) + "��";
		return ft.format(number / 100000000.0) + "��";
	}

	@Override
	public int compareTo(Sort o) {
		if (time != o.time)
			return (int) (time - o.time);
		if (cmpCount != o.cmpCount)
			return (int) (cmpCount - o.cmpCount);
		return (int) (swapCount - o.swapCount);
	}

	private boolean isStable() {
		if (this instanceof HeapSort)
			return false;
		if (this instanceof SelectionSort)
			return false;
		if (this instanceof BubbleSort1 || this instanceof BubbleSort2 || this instanceof BubbleSort3)
			return true;
		if (this instanceof InsertionSort1 || this instanceof InsertionSort2 || this instanceof InsertionSort3)
			return true;
		else
			return false;
	}
}
