package com.ygj;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.ygj.cmp.BubbleSort1;
import com.ygj.cmp.BubbleSort2;
import com.ygj.cmp.BubbleSort3;
import com.ygj.cmp.HeapSort;
import com.ygj.cmp.InsertionSort1;
import com.ygj.cmp.InsertionSort2;
import com.ygj.cmp.SelectionSort;
import com.ygj.tools.Asserts;
import com.ygj.tools.Integers;
import com.ygj.tools.Times;

public class Main {

	public static void main(String[] args) {
//		test2();
		Integer[] array = Integers.random(10000, 1, 20000);
		testSort(array, 
//				new BubbleSort1(),
//				new BubbleSort2(), 
				new BubbleSort3(), 
				new HeapSort(), 
				new SelectionSort(),
				new InsertionSort2());
	}

	static void testSort(Integer[] array, Sort... sorts) {
		for (Sort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
		}

		Arrays.sort(sorts);
		for (Sort sort : sorts) {
			System.out.println(sort);
		}
	}

	static void test3() {
//		Integer[] array1 = Integers.tailAscOrder(1, 15, 2);
		Integer[] array1 = { 2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		Integers.println(array1);
		HeapSort heapSort = new HeapSort();
		heapSort.sort(array1);
		Integers.println(array1);
	}

	static void test1() {
		Integer[] array1 = Integers.tailAscOrder(1, 15, 2);
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.sort(array1);
		Integers.println(array1);
	}

	static void test2() {
//		Integer[] array1 = Integers.tailAscOrder(1, 20000, 4000);
		Integer[] array1 = Integers.random(20000, 1, 20000);
		Integer[] array2 = Integers.copy(array1);
		Integer[] array3 = Integers.copy(array1);
		Integer[] array4 = Integers.copy(array1);
		Integer[] array5 = Integers.copy(array1);

		BubbleSort1 bubbleSort1 = new BubbleSort1();
		Times.test("bubbleSort1", () -> {
			bubbleSort1.sort(array1);
		});
//		Integers.println(array1);

		BubbleSort2 bubbleSort2 = new BubbleSort2();
		Times.test("bubbleSort2", () -> {
			bubbleSort2.sort(array2);
		});
//		Integers.println(array2);

		BubbleSort3 bubbleSort3 = new BubbleSort3();
		Times.test("bubbleSort3", () -> {
			bubbleSort3.sort(array3);
		});
//		Integers.println(array3);

		SelectionSort selectionSort = new SelectionSort();
		Times.test("selectionSort", () -> {
			selectionSort.sort(array4);
		});

		HeapSort heapSort = new HeapSort();
		Times.test("heapSort", () -> {
			heapSort.sort(array5);
		});
	}

}
