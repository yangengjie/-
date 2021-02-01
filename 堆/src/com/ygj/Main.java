package com.ygj;

import java.util.Comparator;
import java.util.HashMap;

import com.ygj.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		int k = 4;
		Integer[] data = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		BinaryHeap<Integer> heap = new BinaryHeap<>(null, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < data.length; i++) {
			int value = data[i];
			if (heap.size() < k)
				heap.add(value);
			else if (value > heap.get()) {
				heap.replace(value);
			}
		}
		BinaryTrees.println(heap);
	}

	private static void test() {
		int k = 2;
		Integer[] data = { 3, 2, 1, 5, 6, 4 };
		BinaryHeap<Integer> heap = new BinaryHeap<>(null, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}

		});
		for (int i = 0; i < data.length; i++) {
			int value = data[i];
			if (i < k) {
				heap.add(value);
			} else if (value < heap.get()) {
				heap.replace(value);
			}
		}
		BinaryTrees.println(heap);

	}
}
