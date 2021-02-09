package com.ygj.cmp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ygj.Sort;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		List<Integer> stepSequence = shellStepSequence();
		// 32 {16,8,4,2,1}
		for (Integer step : stepSequence) {
			sort(step);
		}
	}

	private void sort(int step) {
		for (int col = 0; col < step; col++) {
			for (int begin = col + step; begin < array.length; begin += step) {
				int cur = begin;
				while (cur > col && cmp(cur, cur - step) < 0) {
					swap(cur, cur - step);
					cur -= step;
				}
			}
		}
	}

	private List<Integer> shellStepSequence() {
		List<Integer> stepSequence = new LinkedList<>();
		int len = array.length;
		while ((len >>= 1) > 0) {
			stepSequence.add(len);
		}
		return stepSequence;
	}

}
