package com.ygj;

import java.util.Comparator;

import com.ygj.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {

		test();
	}
	
	
	private static void test1() {
		Integer[] data = { 84, 82, 4, 83, 75, 60, 33, 88, 1, 29, 41, 74, 77, 51, 42, 22, 54, 18, 52, 31, 58, 24, 94, 70,
				90, 79, 63, 36 };
		BinaryHeap<Integer> heap = new BinaryHeap<>(null, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < data.length; i++) {
			int value = data[i];
			if (heap.size() < 5)
				heap.add(value);
			else if (value > heap.get()) {
				heap.replace(value);
			}
		}
		BinaryTrees.println(heap);
	}

	private static void test() {
		int k = 5;
		Integer[] data = { 84, 82, 4, 83, 75, 60, 33, 88, 1, 29, 41, 74, 77, 51, 42, 22, 54, 18, 52, 31, 58, 24, 94, 70,
				90, 79, 63, 36 };
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
