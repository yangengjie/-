package com.ygj;

import com.ygj.circle.CircleLinkedList;
import com.ygj.circle.CircleLinkedList2;
import com.ygj.circle.SingleCircleLinkedList;
import com.ygj.single.SingleLinkedList2;

public class LinkedListTest {

	public static void main(String[] args) {
//		linkedListTest();
//		singleCircleLinkedListTest();
//		circleLinkedListTest();
		yuesefu();
	}

	static void yuesefu() {
		CircleLinkedList2<Integer> list = new CircleLinkedList2<Integer>();
		for (int i = 1; i < 9; i++) {
			list.add(i);
		}
		list.reset();
		while(!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}

	static void circleLinkedListTest() {
		List<Integer> list = new CircleLinkedList<Integer>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);

		list.add(0, 66);
		list.add(3, 77);
		System.out.println(list);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list);
	}

	static void singleCircleLinkedListTest() {
		List<Integer> list = new SingleCircleLinkedList<Integer>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		System.out.println(list);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list);

	}

	static void linkedListTest() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);

		list.add(0, 0);
		list.add(3, 66);

		System.out.println(list);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
		System.out.println(list);
	}
}
