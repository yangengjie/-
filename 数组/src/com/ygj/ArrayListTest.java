package com.ygj;

import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList2<Integer> list = new ArrayList2<Integer>();
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		list.add(77);
		System.out.println(list);
		list.add(0,88);
		list.add(0,99);
		System.out.println(list);
		list.add(0,100);
		System.out.println(list);
		list.add(111);
		list.add(222);
		list.add(333);
		list.add(444);
		System.out.println(list);
		
		list.remove(0);
		list.remove(0);
		list.remove(0);
		System.out.println(list);
//		list.add(0,88);
//		list.add(0,99);
//		System.out.println(list);
		list.add(555);
		list.add(666);
		list.add(777);
		System.out.println(list);
//		list.set(12, null);
//		System.out.println(list.get(0));
//		System.out.println(list.indexOf(null));
		
		for(int i=0;i<14;i++) {
			System.out.println(list.remove(0));
			System.out.println(list);
		}
			
		System.out.println(list);
		
	}

}
