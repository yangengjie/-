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
import com.ygj.cmp.ShellSort;

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
		String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
		String compareCountStr = "比较：" + numberString(cmpCount);
		String swapCountStr = "交换：" + numberString(swapCount);
		String stableStr = "稳定性：" + isStable();
		return "【" + getClass().getSimpleName() + "】\n" + stableStr + " \t" + timeStr + " \t" + compareCountStr + "\t "
				+ swapCountStr + "\n" + "------------------------------------------------------------------";
	}

	private String numberString(long number) {
		if (number < 10000)
			return "" + number;
		if (number < 100000000)
			return ft.format(number / 10000.0) + "万";
		return ft.format(number / 100000000.0) + "亿";
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
//		if (this instanceof RadixSort) return true;
//		if (this instanceof CountingSort) return true;
		if (this instanceof ShellSort) return false;
		if (this instanceof SelectionSort) return false;
		Student[] students = new Student[20];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(i * 10, 10);
		}
		sort((T[]) students);
		for (int i = 1; i < students.length; i++) {
			int score = students[i].score;
			int prevScore = students[i - 1].score;
			if (score != prevScore + 10) return false;
		}
		return true;
	}
}
